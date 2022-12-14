package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeInput;
import com.example.demo.service.EmployeeService;



/**
 * @author n.sai.surya.yalla
 * Controller class for Employee API's
 */

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	
	@QueryMapping
	@PostMapping
	public List<Employee>  allEmployees() {
		return empService.getEmployees();
		
	}
	
	@QueryMapping
	public Employee getEmpById(@Argument Integer id) {
		return empService.getEmployeeById(id);
	} 
	
	
	@MutationMapping
	public Employee addEmployee(@Argument String empName, @Argument Integer empExp ) {
		Employee emp = new Employee();
		emp.setEmpExp(empExp);
		emp.setEmpName(empName);
		return empService.createEmployee(emp);
		
	}
	
	@MutationMapping
	public Employee deleteEmpById(@Argument Integer id) {
		return empService.deleteEmployeeById(id);
	}
	
	@MutationMapping
	public Employee updateEmployee(@Argument EmployeeInput input) {
		Employee emp = new Employee(input.getEmpId(),input.getEmpName(),input.getEmpExp());
		return empService.updateEmployeeById(emp);
	}
	
	
}
