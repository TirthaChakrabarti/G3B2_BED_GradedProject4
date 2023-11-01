package com.gl.employeeMgMtApplication.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.employeeMgMtApplication.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{ 
	
	List<Employee> findAll(Sort sort);
	
	@Query("SELECT e from Employee e WHERE e.firstName LIKE CONCAT ('%', :firstName, '%')")
	List<Employee> searchEmployees(String firstName);
}
