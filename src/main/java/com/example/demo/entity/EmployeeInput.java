package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author n.sai.surya.yalla
 * Model class for Employee Input in GraphQL Schema
 */
/**
 * @author n.sai.surya.yalla
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {
	private Integer empId;
	private String empName;
	private Integer empExp;
}
