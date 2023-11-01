package com.gl.employeeMgMtApplication.security;

import org.springframework.context.annotation.Bean;			
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.employeeMgMtApplication.serviceImplementation.UserServiceImplementation;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImplementation();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider MyAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(MyAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/employees", "/employees/list", "/employees/list/{id}", "employees/list/search", "employees/list/sort", "/403").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/employees/save", "/employees/update/{id}", "/employees/delete/{id}", "/employees/addUser", "/employees/addRole").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated() 
			.and().httpBasic()
			.and()
			.formLogin().loginProcessingUrl("/login").permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll()
//			.and()
//			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.cors().and().csrf().disable(); 
	}
}
