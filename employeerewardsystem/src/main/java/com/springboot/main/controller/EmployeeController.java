package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Employee;
import com.springboot.main.model.User;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public Employee insertEmployee(@RequestBody Employee employee) {
		// save user info in db
		User user = employee.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole("EMPLOYEE");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		employee.setUser(user);

		return employeeService.insert(employee);
	}

}
