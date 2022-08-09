package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeInput;


/**
 * @author n.sai.surya.yalla
 *	Employee Service unit test cases including Persistence layer
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceWithJPA {
	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void testAllEmployees() {
		Employee e1 = new Employee("allemp1",1);
		Employee e2 = new Employee("allemp2",2);
		e1.toString();
		e1.hashCode();
		
		entityManager.persistAndFlush(e1);
		entityManager.persistAndFlush(e2);
		assertEquals(2,employeeService.getEmployees().size());	
	}
	
	@Test
	public void testGetEmployeeById_IdDoesnotExists() {
		assertEquals(null,employeeService.getEmployeeById(1));
	}
	
	@Test
	public void testGetEmployeeById_IdExists() {
		Employee e1 = new Employee("getbyid",1);
		entityManager.persist(e1);
		entityManager.flush();
		assertEquals(new Employee(6,"getbyid",1),employeeService.getEmployeeById(6));
	}
	
	@Test
	public void testUpdateEmployee_IdExists() {
		Employee e1 = new Employee("updatebyid",1);
		entityManager.persist(e1);
		entityManager.flush();
		Employee emp = new Employee(1,"updatebyid",1);
		assertEquals(new Employee(1,"updatebyid",1),employeeService.updateEmployeeById(emp));
		
	}
	
	@Test
	public void testUpdateEmployee_IdDoesnotExist() {
		Employee emp = new Employee(5,"updatebyid2",1);
		assertEquals(null,employeeService.updateEmployeeById(emp));
	}
	
	@Test
	public void testCreateEmployee() {
		Employee emp = new Employee(5,"create",1);
		assertEquals(new Employee(5,"create",1), employeeService.createEmployee(emp));
	}
	
	@Test
	public void testDeleteById_IdExists() {
		Employee emp = new Employee("delete",1);
		entityManager.persistAndFlush(emp);
		assertEquals(new Employee(4, "delete",1),employeeService.deleteEmployeeById(4));
	}
	
	@Test 
	public void testDeleteById_IdDoesnotExist() {
		assertEquals(null, employeeService.deleteEmployeeById(10));
		
	}
	
	@Test
	public void testEmployeeInput() {
		EmployeeInput e = new EmployeeInput(1,"test",2);
		EmployeeInput e2 = new EmployeeInput();
		e2.setEmpId(1);
		e2.setEmpName("test");
		e2.setEmpExp(2);
		e2.toString();
		e2.hashCode();
		assertEquals(e2,e);
		
	}

}
