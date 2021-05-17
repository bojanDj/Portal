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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView profile(@PathVariable(name = "ID") int newID) {
        ModelAndView modelAndView = new ModelAndView("portal/sidebar");
        modelAndView.addObject("story", newService.getNewByID(newID));
        return modelAndView;
    }
    
    @RequestMapping(value = "/story{ID}/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute Comment comment,
                                    @PathVariable(name = "ID") int newID) {
        ModelAndView modelAndView = new ModelAndView("redirect:/portal/new/"+newID);
        Story vest = newService.getNewByID(newID);
        comment.setVest(vest);
        vest.getKomentari().add(comment);
        modelAndView.addObject("story", vest);
//        newService.addComment(comment);
        newService.updateStory(vest);
        return modelAndView;
    }
    
    @ModelAttribute(name = "comment")
    private Comment getComment(Model model) {
        return new Comment();
    }
}
