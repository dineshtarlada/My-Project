package com.springboot.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.repository.EmployeeRepository;
import com.springboot.main.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void testGetOneValidEmployeeId() throws InvalidIdException {

		int empId = 303;
		Employee employee = new Employee();
		when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));

		Employee result = employeeService.getById(empId);

		assertEquals(employee, result);
	}

}
