package com.gl.employeeMgMtApplication.service;

import org.springframework.stereotype.Component;

import com.gl.employeeMgMtApplication.entity.Role;

@Component
public interface RoleServiceInterface {

	public Role addRole(Role role);
}
