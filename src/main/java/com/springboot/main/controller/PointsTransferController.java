package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.TransactionDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ManagerService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/transferpoints")
public class PointsTransferController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ManagerService managerService;
	


	// transfer points from one employee to another employee
	@PostMapping("/employeetoemployee/{eid1}/{eid2}/{points}")
	public ResponseEntity<?> employeeTransferPoints(@PathVariable("eid1") int fromEmployeeId,
			@PathVariable("eid2") int toEmployeeId, @PathVariable("points") double pointsToTransfer) {

		try {
			employeeService.employeeTransferPoints(fromEmployeeId, toEmployeeId, pointsToTransfer);
			return ResponseEntity.ok("Points transferred successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	// manager will assign the points to employee
	@PostMapping("/managertoemployee/{mid}/{eid}/{points}")
	public ResponseEntity<?> transferPoints(@PathVariable("mid") int mid, @PathVariable("eid") int eid,
			@PathVariable("points") double pointsToTransfer,@RequestBody TransactionDto request) {

		try {
			managerService.managerTransferPoints(mid, eid, pointsToTransfer,request.getComments());
			return ResponseEntity.ok("Points transferred successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}