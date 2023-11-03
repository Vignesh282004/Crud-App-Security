package com.Demo.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Demo.Repositories.UserRepo;
import com.Demo.models.User;

@Controller
public class AdminController {

	@Autowired
	private UserRepo userRepo;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/adminScreen")
	public String getUser(Model model,Principal principal)
	{
		User user = userRepo.findByEmail(principal.getName());
		model.addAttribute("userDetails",user.getName());
		return "adminScreen";
	}
	
	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@GetMapping("/dashboard")
	public String getUsers(Model model,Principal principal)
	{
		User user = userRepo.findByEmail(principal.getName());	
		model.addAttribute("userDetails",user.getName());
		return "dashboard";
	}
	
}
