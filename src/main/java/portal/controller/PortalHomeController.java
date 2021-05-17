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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import portal.service.StoryService;

/**
 *
 * @author Bojan
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalHomeController {
    private StoryService newService;

    @Autowired
    public PortalHomeController(StoryService newService) {
        this.newService = newService;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("portal/home");
        modelAndView.addObject("news", newService.getAllNews());
        return modelAndView;
    }
    
    @RequestMapping(value = "/sidebar", method = RequestMethod.GET)
    public ModelAndView sidebar(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("portal/sidebar");
        //modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }
}
