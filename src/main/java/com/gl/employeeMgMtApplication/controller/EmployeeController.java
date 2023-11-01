package com.gl.employeeMgMtApplication.controller;

import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeeMgMtApplication.entity.Employee;
import com.gl.employeeMgMtApplication.serviceImplementation.EmployeeServiceImplementation;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeServiceImplementation empService;

	@GetMapping("/list")
	private List<Employee> listEmployees() {
		return empService.listAllEmployees();
	}

	@GetMapping("/list/sort")
	private List<Employee> listEmployeesSortedByName(@RequestParam(defaultValue = "asc") String order) {
		return empService.listAllEmployeesSortedByFirstName(order);
	}

	@GetMapping("/list/{id}")
	private Employee fetchEmployeeById(@PathVariable int id) {
		Employee theEmployee = empService.fetchEmployeeById(id);
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + id);
		}
		return theEmployee;
	}
	
	@PostMapping("/save")
	private void saveEmployee(@RequestBody Employee employee) {
		empService.saveEmployee(employee);
	}

	@PutMapping("/update/{id}")
	private Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		employee.setId(id);
		empService.updateEmployee(employee, id);
		return fetchEmployeeById(id);
	}

	@DeleteMapping("/delete/{id}")
	private void deleteEmployee(@PathVariable int id) {
		Employee tempEmployee = empService.fetchEmployeeById(id);
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + id);
		}
		empService.deleteEmployee(id);
		System.out.println("Deleted employee id - "+id);
	}

	@GetMapping("/list/search/{firstName}")
	private List<Employee> searchEmployees(@PathVariable String firstName) {
		return empService.searchEmployeeByFirstName(firstName);
	}
}
