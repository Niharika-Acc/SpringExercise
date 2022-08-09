package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeInput;
import com.example.demo.repository.EmployeeRepository;


/**
 * @author n.sai.surya.yalla
 * Test class for Employee Controller
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	
	
	@Autowired
	private EmployeeController employeeController;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testAllEmployees() {
		when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(1, "Niharika", 2),new Employee(2,"Sai", 2)).collect(Collectors.toList()));
		assertEquals(2, employeeController.allEmployees().size());
		
	}
	
	@Test
	public void testGetEmployeeById() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		assertEquals(new Employee(1,"Niharika",2),employeeController.getEmpById(1));
	}
	
	@Test
	public void testAddEmployee() {
		when(employeeRepository.save(new Employee("Niharika",2))).thenReturn(new Employee(1,"Niharika",2));
		assertEquals(new Employee(1,"Niharika",2),employeeController.addEmployee("Niharika", 2));
	}
	
	@Test
	public void testDeleteEmployeeById() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		assertEquals(new Employee(1,"Niharika",2),employeeController.deleteEmpById(1));
	}
	
	@Test
	public void testUpdateEmployee() {
		when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(1,"Niharika",2)));
		when(employeeRepository.save(new Employee(1,"Niharika",2))).thenReturn(new Employee(1,"Niharika",2));
		assertEquals(new Employee(1,"Niharika",2),employeeController.updateEmployee(new EmployeeInput(1,"Niharika",2)));
	}
	

}
