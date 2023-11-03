package com.Demo.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Demo.Services.UserRegServImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
private UserRegServImpl userRegServImpl;
	
	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userRegServImpl);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	protected void configure(AuthenticationManagerBuilder auth) throws 
                                                Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	 @SuppressWarnings("removal")
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .authorizeHttpRequests((authorize) ->
	                        authorize.requestMatchers("/registration").permitAll()
	                        .anyRequest().authenticated()
	                ).formLogin(
	                        form -> form
	                                .loginPage("/login")
	                               .successHandler(successHandler)
	                                .permitAll()
	                ).logout(
	                        logout -> logout
	                        .invalidateHttpSession(true).clearAuthentication(true)
	                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                                .permitAll()
	                );
	        return http.build();
	    }
	
}

