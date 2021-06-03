/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.controller;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import portal.dto.Comment;
import portal.dto.Image;
import portal.dto.Story;
import portal.service.StoryService;
import portal.validator.StoryValidator;
import java.util.Locale;

/**
 *
 * @author Bojan
 */
@Controller
@RequestMapping(value = "/admin")
public class PortalAdminController {
    private StoryService newService;
    private StoryValidator storyValidator;

    @Autowired
    public PortalAdminController(StoryService newService, StoryValidator storyValidator) {
        this.newService = newService;
        this.storyValidator = storyValidator;
    }
    
    @Autowired
    ServletContext context; 
    
    @RequestMapping(value = "/addStory", method = RequestMethod.GET)
    public ModelAndView addStory(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/addStory");
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        List<Story> lista = newService.getAllNews();
        modelAndView.addObject("price", lista);
//        Collections.sort(list, (x, y) -> x.getDrzava().compareTo(y.getDrzava()));
//        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
//        List<Navigation> lista = new ArrayList(Navigation);
//        for (int i = 0; i < list.size(); i++) {
//            Story s = list.get(i);
//            cal.setTime(s.getDatum());
//            Navigation n = new Navigation();
//            n.setId(s.getId);
//            n.setNaslov(s.getNaslov());
//            n.setYear(cal.get(Calendar.YEAR));
//            n.setMonth(cal.get(Calendar.MONTH));
//            lista.add(n);
//        }
        String[] countryCodes = Locale.getISOCountries();
        List<String> count = new ArrayList<String>();
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();
            count.add(name);
        }
        modelAndView.addObject("locale", count);

        List<String> listDrzava = new ArrayList<>();
        for (Story story: lista) {
            if (!listDrzava.contains(story.getDrzava())) listDrzava.add(story.getDrzava());
        }
        modelAndView.addObject("countries", listDrzava);
        
       
//        List<Integer> years = new ArrayList<Integer>();
//        for (int i = 0; i < list.size(); i++) {
//            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
//            cal.setTime(list.get(i).getDatum());
//            if (!years.contains(cal.get(Calendar.YEAR))) years.add(cal.get(Calendar.YEAR));
//        }
//        List<Integer> sortedList = years.stream().sorted().collect(Collectors.toList());
//        modelAndView.addObject("years", sortedList);
        return modelAndView;
    }
    
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView comments(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/comments");
        modelAndView.addObject("comments", newService.getAllNotVerifiedComments());
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        return modelAndView;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/home");
        modelAndView.addObject("news", newService.getAllNews());
        modelAndView.addObject("premium", true);
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        return modelAndView;
    }
    
    @InitBinder("story")
    private void initOcenaBinder(WebDataBinder binder) {
        binder.setValidator(storyValidator);
    }
    
    @RequestMapping(value = "/addStory", method = RequestMethod.POST)
    public ModelAndView addStoryP(@ModelAttribute @Validated Story story,
                                    Principal principal,
                                    RedirectAttributes redirectAttrs) {
        ModelAndView modelAndView = new ModelAndView("redirect:addStory");
        for (int i = 0; i < story.getSlike().size(); i++) {
            story.getSlike().get(i).setVest(story);
        }
        
        try {
            newService.addStory(story);
            redirectAttrs.addFlashAttribute("message", "Nova prica je uspesno uneta!");
        } catch (Exception e){
            redirectAttrs.addFlashAttribute("message", "Doslo je do greske, pokusajte ponovo.");
        }
        return modelAndView;
    }
    
    @ModelAttribute(name = "story")
    private Story getNew(Model model) {
        return new Story();
    }
    
    @RequestMapping(value = "/acceptComment/{storyID}/{comID}", method = RequestMethod.GET)
    public ModelAndView acceptComment(@PathVariable(name = "storyID") int storyID,
                                        @PathVariable(name = "comID") int comID,
                                        Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:../../../admin/comments");
        Story n = newService.getNewByID(storyID);
        for (Comment kom : n.getKomentari()) {
            if (kom.getId() == comID) {
                kom.setPrihvacen(1);
                break;
            }
        }
        newService.updateStory(n);
        return modelAndView;
    }
    
    @RequestMapping(value = "/declineComment/{storyID}/{comID}", method = RequestMethod.GET)
    public ModelAndView declineComment(@PathVariable(name = "storyID") int storyID,
                                        @PathVariable(name = "comID") int comID,
                                        Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:../../../admin/comments");
        Story n = newService.getNewByID(storyID);
        for (Comment kom : n.getKomentari()) {
            if (kom.getId() == comID) {
                kom.setPrihvacen(2);
                break;
            }
        }
        newService.updateStory(n);
        return modelAndView;
    }
    
     @RequestMapping(value = "/UploadServlet",method = RequestMethod.POST)
    public ModelAndView upload(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        String fileSavePath = context.getRealPath("/") + File.separator + "Upload";/*save uploaded files to a 'Upload' directory in the web app*/
        if (!(new File(fileSavePath)).exists()) {
            (new File(fileSavePath)).mkdir();    // creates the directory if it does not exist        
        }
        String resp = "";
        int i = 1;
        resp += "<br>Ucitane slike:<br>";
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/addStory");
        try {
            MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 1024);  /* file limit size of 1GB*/
            Part _part;
            List<Image> list = new LinkedList<>();
            while ((_part = parser.readNextPart()) != null) {
                if (_part.isFile()) {
                    FilePart fPart = (FilePart) _part;  // get some info about the file
                    String name = fPart.getFileName();
                    if (name != null) {
                        long fileSize = fPart.writeTo(new File(fileSavePath));
                        resp += i++ + ". " + fPart.getFilePath() + "[" + fileSize / 1024 + " KB]<br>";
                        Image img = new Image();
                        img.setURL(fileSavePath + name);
                        list.add(img);
                        System.err.println(fileSavePath + name+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    } else {
                        resp = "<br>The user did not upload a file for this part.";
                    }
                }
                
            }// end while 
            redirectAttrs.addFlashAttribute("list", list);
        } catch (java.io.IOException ioe) {
            resp = ioe.getMessage();
        }
        redirectAttrs.addFlashAttribute("message", resp);
       return modelAndView;
    }
}
