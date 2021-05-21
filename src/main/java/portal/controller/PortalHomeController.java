/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import portal.dto.Comment;
import portal.paypal.Order;
import portal.paypal.PaypalService;
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
    
    private boolean isUserPremium(String username) {
        java.sql.Date lastPay = newService.getLastPayDateByUsername(username);
        //java.util.Date utilDate = new java.util.Date(lastPay.getTime());
        boolean premium = false;
        if (lastPay != null) {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(lastPay);
            cal2.setTime(new Date());
            cal2.add(Calendar.MONTH, -1);
            if (cal.after(cal2)) premium = true;
        }
        return premium;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("portal/home");
        modelAndView.addObject("news", newService.getAllNews());
        boolean premium = false;
        try {
                premium = isUserPremium(principal.getName());
            } catch (Exception e) {}
        modelAndView.addObject("premium", premium);
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        return modelAndView;
    }
    
    @RequestMapping(value = "/sidebar", method = RequestMethod.GET)
    public ModelAndView sidebar(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("portal/sidebar");
        if (principal != null)
            modelAndView.addObject("user", principal.getName());
        //modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }
    
    @Autowired
	PaypalService service;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

        @RequestMapping(value = "/payhome", method = RequestMethod.GET)
	public ModelAndView home2(Principal principal) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER")))) {
                modelAndView = new ModelAndView("paypal/home");
                if (principal != null)
                    modelAndView.addObject("user", principal.getName());
            }
		return modelAndView;
	}

	@PostMapping("/pay")
	public ModelAndView payment(@ModelAttribute("order") Order order) {
            ModelAndView modelAndView;
		try {
//			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
//					order.getIntent(), order.getDescription(), "http://localhost:8082/Portal/portal/" + CANCEL_URL,
//					"http://localhost:8082/Portal/portal/" + SUCCESS_URL);
                        Payment payment = service.createPayment(10.0, "USD", "paypal",
					"sale", "Uplata za premium na portalu.", "http://localhost:8082/Portal/portal/" + CANCEL_URL,
					"http://localhost:8082/Portal/portal/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					modelAndView = new ModelAndView("redirect:"+link.getHref());
                                        return modelAndView;
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
                modelAndView = new ModelAndView("redirect:/portal/payhome");
                modelAndView.addObject("o", order);
                return modelAndView;
	}
        
        @ModelAttribute(name = "order")
        private Order getOrder(Model model) {
            return new Order();
        }
	
	 @GetMapping(value = CANCEL_URL)
	    public ModelAndView cancelPay(Principal principal) {
                ModelAndView modelAndView = new ModelAndView("portal/payhome");
                modelAndView.addObject("message", "Doslo je do greske prilikom obrade uplate. Molimo pokusajte ponovo.");
                if (principal != null)
                    modelAndView.addObject("user", principal.getName());
                return modelAndView;
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Principal principal, RedirectAttributes redirectAttrs) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
                        newService.updatePayment(principal.getName());
                        ModelAndView modelAndView = new ModelAndView("redirect:/portal/home");
                        redirectAttrs.addFlashAttribute("message", "Uplata je uspesno izvrsena. Vas nalog je sada premium nalog.");
                        return modelAndView;
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
                return new ModelAndView("redirect:/portal/payhome");
	    }
}
