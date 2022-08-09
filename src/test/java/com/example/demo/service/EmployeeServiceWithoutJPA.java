package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

/**
 * @author n.sai.surya.yalla
 * Employee Service unit test cases mocking Persistence layer
 */
@SpringBootTest
public class EmployeeServiceWithoutJPA {
	@MockBean
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	void testGetAllEmployees() {
		when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(1, "Niharika", 2),new Employee(2,"Sai", 2)).collect(Collectors.toList()));
		assertEquals(2,employeeService.getEmployees().size());
	}
	
	@Test
	void testAddEmployee() {
		when(employeeRepository.save(new Employee(1,"Niharika",2))).thenReturn(new Employee(1,"Niharika",2));
		assertEquals(new Employee(1,"Niharika",2),employeeService.createEmployee(new Employee(1,"Niharika",2)));
	}
	
	@Test
	void testGetEmployeeById_IdExists() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		assertEquals(new Employee(1,"Niharika",2),employeeService.getEmployeeById(1));
	}
	
	@Test
	void testGetEmployeeById_IdDoesnotExist() {
		when(employeeRepository.findById(11)).thenReturn(java.util.Optional.empty());
		assertEquals(null,employeeService.getEmployeeById(12));
	}
	
	@Test
	void testUpdateEmployeeById_IdExists() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		when(employeeRepository.save(new Employee(1,"Niharika",2))).thenReturn(new Employee(1,"Niharika",2));
		assertEquals(new Employee(1,"Niharika",2),employeeService.updateEmployeeById(new Employee(1,"Niharika",2)));
	}
	
	@Test
	void testUpdateEmployeeById_IdDoesnotExist() {
		when(employeeRepository.findById(11)).thenReturn(java.util.Optional.empty());
		assertEquals(null,employeeService.updateEmployeeById(new Employee(11,"test",4)));
	}
	
	@Test
	void testDeleteEmployee_IdExists() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		assertEquals(new Employee(1,"Niharika",2),employeeService.deleteEmployeeById(1));
		
	}
	
	@Test
	void testDeleteEmployeeq_IdDoesnotExist() {
		when(employeeRepository.findById(11)).thenReturn(java.util.Optional.empty());
		assertEquals(null,employeeService.deleteEmployeeById(11));
		
	}

}
