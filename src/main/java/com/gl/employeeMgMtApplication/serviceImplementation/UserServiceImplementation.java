package com.gl.employeeMgMtApplication.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.employeeMgMtApplication.entity.User;
import com.gl.employeeMgMtApplication.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public User addUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword); 
		return userRepo.save(user); 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		User user = userRepo.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		return new MyUserDetails(user);
	}

}
