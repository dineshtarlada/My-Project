package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.EmployeeDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Address;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeProduct;
import com.springboot.main.model.Manager;
import com.springboot.main.model.Product;
import com.springboot.main.model.User;
import com.springboot.main.service.AddressService;
import com.springboot.main.service.EmployeeProductService;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.ProductService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ProductService productService;

	@Autowired
	private EmployeeProductService employeeProductService;

	@PostMapping("/address/add/{mid}")
	public ResponseEntity<?> insertEmployee(@PathVariable("mid") int mid, @RequestBody Employee employee) {
		// save user info in db

		try {

			Manager manager = managerService.getById(mid);
			employee.setManager(manager);

			User user = employee.getUser();
			// i am encrypting the password
			String passwordPlain = user.getPassword();

			String encodedPassword = passwordEncoder.encode(passwordPlain);
			user.setPassword(encodedPassword);

			user.setRole("EMPLOYEE");
			user = userService.insert(user);
			// attach the saved user(in step 1)
			employee.setUser(user);

			// add the address and attach to employee
			Address address = employee.getAddress();

			address = addressService.insert(address);

			employee.setAddress(address);

			employee = employeeService.insert(employee);
			return ResponseEntity.ok().body(employee);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}
	// fetching all employees details

	@GetMapping("/all")
	public List<Employee> getAllEmployees(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return employeeService.getAllEmployees(pageable);
	}

	// fetching employee details using by id

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Employee employee = employeeService.getById(id);
			return ResponseEntity.ok().body(employee);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// deleting single employee by using id...

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {

		try {
			// validate id

			Employee employee = employeeService.getById(id);

			// delete
			employeeService.deleteEmployee(employee);
			return ResponseEntity.ok().body("Employee deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")

	// :update: which record to update? give me new value for update
	public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDto newEmployee) {
		try {
			// validate id
			Employee oldEmployee = employeeService.getById(id);
			if (newEmployee.getName() != null)
				oldEmployee.setName(newEmployee.getName());
			if (newEmployee.getPhone() != null)
				oldEmployee.setPhone(newEmployee.getPhone());

			oldEmployee = employeeService.insert(oldEmployee);
			return ResponseEntity.ok().body(oldEmployee);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/update/address/{id}")
	public ResponseEntity<?> updateEmployeeAddress(@PathVariable("id") int id, @RequestBody Employee newEmployee) {
		try {
			// validate id
			Employee oldEmployee = employeeService.getById(id);

			Address newAddress = newEmployee.getAddress();

			if (newAddress != null) {
				// Update address fields
				Address oldAddress = oldEmployee.getAddress();
				oldAddress.setCity(newAddress.getCity());
				oldAddress.setState(newAddress.getState());
				oldAddress.setCountry(newAddress.getCountry());

				// Save the updated employee
				oldEmployee = employeeService.insert(oldEmployee);

				return ResponseEntity.ok().body(oldEmployee);
			} else {
				return ResponseEntity.badRequest().body("New address cannot be null");
			}

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/all/{mid}")
	public ResponseEntity<?> getEmployeesByManager(@PathVariable("mid") int mid) {
		/* Fetch manager object using given mid */
		try {
			Manager manager = managerService.getById(mid);
			List<Employee> list = employeeService.getEmployeesByManager(mid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@PostMapping("/project/add/{eid}/{pid}")
	public ResponseEntity<?> PurchasedProducts(@PathVariable("eid") int eid, @PathVariable("pid") int pid,
			@RequestBody EmployeeProduct employeeProduct) {

		try {

			// step-1:
			Employee employee = employeeService.getById(eid);

			// step-2:
			Product product = productService.getById(pid);

			// step-3:
			employeeProduct.setEmployee(employee);

			employeeProduct.setProduct(product);

			// step:4
			employeeProduct = employeeProductService.insert(employeeProduct);
			return ResponseEntity.ok().body(employeeProduct);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}