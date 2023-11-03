package com.Demo.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Demo.Repositories.StudentRepo;
import com.Demo.models.Student;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students/")
@EnableMethodSecurity
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;

	   @GetMapping("signup")
	    public String showSignUpForm(Student student) {
	        return "add-student";
	    }
		
	   @GetMapping("list")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("students", studentRepo.findAll());
	        return "index";
	    }

	    @PostMapping("add")
	    public String addStudent(@Valid Student student, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-student";
	        }
	        studentRepo.save(student);
	        return "redirect:list";
}
	    @PreAuthorize("hasAuthority('ADMIN')")
	    @GetMapping("delete/{id}")
	    public String deletestudent(@PathVariable("id")long id,Model model,Principal principal)
	    {
	    	System.out.println(principal.getName());
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println(authentication.getAuthorities());
	    	boolean hasRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
	    	System.out.println(hasRole);
	    	Student student = studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student Not Found" + id));
	    	studentRepo.delete(student);
	    	model.addAttribute("students",studentRepo.findAll());
	    	return "index";
	    }

	    @GetMapping("edit/{id}")
	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
	        Student student = studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
	        model.addAttribute("student", student);
	        return "update-student";
	    }

	    @PostMapping("update/{id}")
	    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            student.setId(id);
	            return "update-student";
	        }

	        studentRepo.save(student);
	        model.addAttribute("students", studentRepo.findAll());
	        return "index";
	    }
}
