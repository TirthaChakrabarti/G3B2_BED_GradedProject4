package com.gl.employeeMgMtApplication.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.employeeMgMtApplication.entity.Employee;
import com.gl.employeeMgMtApplication.repository.EmployeeRepository;
import com.gl.employeeMgMtApplication.service.EmployeeServiceInterface;

@Service
public class EmployeeServiceImplementation implements EmployeeServiceInterface {

	@Autowired
	EmployeeRepository empRepo;

	@Override
	public List<Employee> listAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public List<Employee> listAllEmployeesSortedByFirstName(String direction) {
		Sort sort = Sort.by(Sort.Direction.fromString(direction), "firstName");
        return empRepo.findAll(sort);
	}

	@Override
	public Employee fetchEmployeeById(int id) {
//		return empRepo.findById(id).get();
		
		Optional<Employee> result = empRepo.findById(id);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		}else {
			throw new RuntimeException("Did not find employee id - " + id);
		}
		return theEmployee;
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp, int id) {
		Employee employee2 = new Employee();
		employee2.setFirstName(emp.getFirstName());
		employee2.setLastName(emp.getFirstName());
		employee2.setEmail(emp.getEmail());
		return empRepo.save(emp);
	}

	@Override
	public void deleteEmployee(int id) {
		empRepo.deleteById(id);
	}

	@Override
	public List<Employee> searchEmployeeByFirstName(String firstName) {
		return empRepo.searchEmployees(firstName);
	}

}
