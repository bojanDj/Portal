/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import portal.dto.Comment;
import portal.dto.Story;
import portal.service.StoryService;
import portal.validator.StoryValidator;

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
    
    @RequestMapping(value = "/addStory", method = RequestMethod.GET)
    public ModelAndView addStory(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/addStory");
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
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
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:admin/addStory");
        story.getSlike().get(0).setVest(story);
        newService.addStory(story);
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
}
