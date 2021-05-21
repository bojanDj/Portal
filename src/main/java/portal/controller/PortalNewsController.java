/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import portal.dto.Comment;
import portal.dto.Story;
import portal.service.StoryService;

/**
 *
 * @author Bojan
 */
@Controller
@RequestMapping(value = "/portal/new")
public class PortalNewsController {
    private StoryService newService;

    @Autowired
    public PortalNewsController(StoryService newService) {
        this.newService = newService;
    }
    
    @GetMapping(value = "/{ID}")
    public ModelAndView profile(@PathVariable(name = "ID") int newID, Principal principal,RedirectAttributes redirectAttrs) {
        ModelAndView modelAndView = new ModelAndView("portal/sidebar");
        Story story = newService.getNewByID(newID);
        modelAndView.addObject("story", story);
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        
        boolean premium = false;
            try {
                premium = isUserPremium(principal.getName());
            } catch (Exception e) {}
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            premium = true;
        }
            
        if (!premium && story.isPremium()) {
            modelAndView = new ModelAndView("redirect:/portal/home");
            redirectAttrs.addFlashAttribute("message","Nije Vam omogucen pristup premium pricama.");
        }
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/story{ID}/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute Comment comment,
                                    @PathVariable(name = "ID") int newID,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/portal/new/"+newID);
        Story vest = newService.getNewByID(newID);
        comment.setVest(vest);
        vest.getKomentari().add(comment);
        modelAndView.addObject("story", vest);
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
//        newService.addComment(comment);
        newService.updateStory(vest);
        return modelAndView;
    }
    
    @ModelAttribute(name = "comment")
    private Comment getComment(Model model) {
        return new Comment();
    }
    
    private boolean isUserPremium(String username) {
        java.sql.Date lastPay = newService.getLastPayDateByUsername(username);
        //java.util.Date utilDate = new java.util.Date(lastPay.getTime());
        boolean premium = false;
        if (lastPay != null) {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(lastPay);
            cal2.setTime(new Date());
            cal.add(Calendar.MONTH, -1);
            if (cal.before(cal2)) premium = true;
        }
        return premium;
    }
}
