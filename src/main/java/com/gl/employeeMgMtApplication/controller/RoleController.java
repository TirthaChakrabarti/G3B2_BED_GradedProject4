package com.gl.employeeMgMtApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeeMgMtApplication.entity.Role;
import com.gl.employeeMgMtApplication.serviceImplementation.RoleServiceImplementation;

@RestController
@RequestMapping("/employees")
public class RoleController {

	@Autowired
	RoleServiceImplementation roleService;
	
	@PostMapping("/addRole")
	private void saveUser(@RequestBody Role role) {
		roleService.addRole(role);
	}
}
