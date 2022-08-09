package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

/**
 * @author n.sai.surya.yalla
 * Repository interface for Employee Entity
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
