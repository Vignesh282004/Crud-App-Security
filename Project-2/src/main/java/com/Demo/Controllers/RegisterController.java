package com.Demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Demo.Dto.RegistationDto;
import com.Demo.Services.UserRegServImpl;

@Controller
@RequestMapping("/registration")
public class RegisterController {

	private UserRegServImpl userRegServImpl;
	public RegisterController(UserRegServImpl userRegServImpl) {
		super();
		this.userRegServImpl = userRegServImpl;
	}
	@ModelAttribute("user")
	public RegistationDto registationDto()
	{
		return new RegistationDto();
	}
	@GetMapping
	public String getform()
	{
		return "register";
	}
	@PostMapping
	public String addreg(@ModelAttribute("user") RegistationDto registationDto)
	{
		userRegServImpl.save(registationDto);
		return "redirect:/login";
	}
	
	
	
	
	
	
}
