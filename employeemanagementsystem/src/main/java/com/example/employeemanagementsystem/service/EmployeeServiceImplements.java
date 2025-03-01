package com.example.employeemanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagementsystem.dao.EmployeeRepository;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.exception.ResourceNotFoundException;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.model.Employee;

@Service
public class EmployeeServiceImplements implements EmployeeService {

	@Autowired
	private EmployeeRepository er;

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee createEmployee = er.save(employee);
		return EmployeeMapper.mapToEmployeeDto(createEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = er.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee does not exists with ID:" + employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employees = er.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee = er.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee does not exists with ID:" + employeeId));
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());

		Employee updatedEmployee = er.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployeeById(Long employeeId) {

		Employee employee = er.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee does not exists with ID:" + employeeId));

		er.deleteById(employeeId);
	}

}
