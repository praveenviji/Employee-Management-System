package com.example.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService es;

	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = es.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
		EmployeeDto employeeDto = es.getEmployeeById(employeeId);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
		List<EmployeeDto> employees = es.getAllEmployee();
		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable("id") Long EmployeeId,
			@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = es.updateEmployeeById(EmployeeId, employeeDto);
		return new ResponseEntity<EmployeeDto>(employee, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
		es.deleteEmployeeById(employeeId);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}
}
