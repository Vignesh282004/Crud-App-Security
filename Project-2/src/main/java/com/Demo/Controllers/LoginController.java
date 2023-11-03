package com.Demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Demo.Dto.LoginDto;
import com.Demo.Services.UserRegServImpl;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserRegServImpl userRegServImpl;

	@ModelAttribute("user")
	public LoginDto loginDto()
	{return new LoginDto();}
		
	@GetMapping
	public String log()
	{
		return "login";
	}
	
	@PostMapping
	public void login(@ModelAttribute("user")LoginDto loginDto)
	{userRegServImpl.loadUserByUsername(loginDto.getUsername());}
}
