package com.gl.employeeMgMtApplication.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.employeeMgMtApplication.entity.Role;
import com.gl.employeeMgMtApplication.repository.RoleRepository;
import com.gl.employeeMgMtApplication.service.RoleServiceInterface;

@Service
public class RoleServiceImplementation implements RoleServiceInterface{

	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Role addRole(Role role) {
		return roleRepo.save(role); 
	}
}
