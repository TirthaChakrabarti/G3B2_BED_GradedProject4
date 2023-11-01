package com.gl.employeeMgMtApplication.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.employeeMgMtApplication.entity.Employee;

@Component
public interface EmployeeServiceInterface {

	public List<Employee> listAllEmployees();
	
	public List<Employee> listAllEmployeesSortedByFirstName(String direction);
 
	public Employee fetchEmployeeById(int id);

	public Employee saveEmployee(Employee emp);

	public Employee updateEmployee(Employee emp, int id);

	public void deleteEmployee(int id);

	public List<Employee> searchEmployeeByFirstName(String firstName);
}
