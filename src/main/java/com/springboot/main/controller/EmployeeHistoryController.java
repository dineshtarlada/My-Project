package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.EmployeeHistory;
import com.springboot.main.service.EmployeeHistoryService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/history")
public class EmployeeHistoryController {
	
	@Autowired
	private EmployeeHistoryService employeeHistoryService;

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {

		try {
			EmployeeHistory employeeHistory = employeeHistoryService.getById(id);
			return ResponseEntity.ok().body(employeeHistory);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	@GetMapping("/all")
	public List<EmployeeHistory> getAllHistory(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return employeeHistoryService.getAllHistory(pageable);
	}
}
