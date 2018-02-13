package com.rt.in_memory_pass.ctrl;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestsCtrl {

	@GetMapping(value="/")
	public String index(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		return "index";
	}
	
	@GetMapping(value="/admin")
	public String adminIndex(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "ADMIN only page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@GetMapping(value="/admin/path")
	public String adminPath(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "ADMIN only page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@GetMapping(value="/user")
	public String userIndex(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "USER only page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@GetMapping(value="/user/path")
	public String userPath(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "USER only page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@GetMapping(value="/adus")
	public String adusIndex(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "ADMIN / USER page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@GetMapping(value="/adus/path")
	public String adusPath(Model model, Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		model.addAttribute("msg", "ADMIN / USER page");
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	
	/**
	 * Login controller. 
	 * User attribute 'hide' in order to show/hide <div> in view
	 */
	@GetMapping(value="/login")
	public String login(Model model,Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		return "index";
	}
	
	/**
	 * Get user authentication (if any) 
	 * and logout properly
	 */
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if( auth != null ) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
}
