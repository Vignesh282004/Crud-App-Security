package com.Demo.Services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Demo.Dto.RegistationDto;
import com.Demo.Repositories.RoleRepo;
import com.Demo.Repositories.UserRepo;
import com.Demo.models.Role;
import com.Demo.models.User;

@Service
@Component
public class UserRegServImpl implements UserRegService{

	@Autowired(required = true)
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null)
		{
			throw new  UsernameNotFoundException("Wrong Username or Password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public User save(RegistationDto registationDto) {
		Role role = new Role();
		if(registationDto.getRole().equals("USER"))
			role = roleRepo.findByRole("USER");
		else if(registationDto.getRole().equals("ADMIN"))
			role = roleRepo.findByRole("ADMIN");
		User user = new User();
		user.setName(registationDto.getName());
		user.setEmail(registationDto.getEmail_id());
		user.setPassword(passwordEncoder.encode(registationDto.getPassword()));
		user.setRoles(role);
		return userRepo.save(user);
	}

	
}
