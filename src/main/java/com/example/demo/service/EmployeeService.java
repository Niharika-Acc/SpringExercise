package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

/**
 * @author n.sai.surya.yalla
 * Service class for Employee API's to perform operations in Database
 */
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Integer id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployeeById(Employee emp) {
		Optional<Employee> optEmp = employeeRepository.findById(emp.getEmpId());
		if(optEmp.isPresent()) {
			return employeeRepository.save(emp);
		}
		else {
			return null;
		}
	}
	
	public Employee deleteEmployeeById(Integer id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent()) {
			employeeRepository.deleteById(id);
			return emp.get();
		}
		else {
			return null;
		}		
	}

}
