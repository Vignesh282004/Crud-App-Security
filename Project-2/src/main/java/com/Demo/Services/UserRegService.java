package com.Demo.Services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Demo.Dto.RegistationDto;
import com.Demo.models.User;

public interface UserRegService  extends UserDetailsService{

	User save(RegistationDto registationDto);
}
