package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author n.sai.surya.yalla
 * Entity representation for Employee Table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
	
	public Employee(String empName, Integer empExp){
		this.empName = empName;
		this.empExp = empExp;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	private String empName;
	private Integer empExp;
		
}
