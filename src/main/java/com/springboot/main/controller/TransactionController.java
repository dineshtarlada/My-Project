package com.springboot.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeHistory;
import com.springboot.main.model.Manager;
import com.springboot.main.model.Transaction;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.TransactionService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private Logger logger;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ManagerService managerService;

	@Autowired
	private EmployeeService employeeService;

	// inserting transaction details....

	// fetching all transactions using pagination...

	@GetMapping("/all")
	public List<Transaction> getAllManagers(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return transactionService.getAlltransactions(pageable);
	}

	// fetching single transaction using by id...

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Transaction transaction = transactionService.getById(id);
			logger.info("transaction is retreived and transaction id:"+transaction.getId() );
			return ResponseEntity.ok().body(transaction);
		} catch (InvalidIdException e) {
			 logger.error("issue in retrieving the transaction id"  );
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("transaction error..");
		}

	}

	// deleting single transaction....

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable("id") int id) {

		try {
			// validate id

			Transaction transaction = transactionService.getById(id);

			// delete
			transactionService.deleteTransaction(transaction);
			return ResponseEntity.ok().body("Transaction deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	// getting all transactions of employee 
	@GetMapping("/manager/all/{mid}")
	public List<Transaction> getTransactionsByManager(@PathVariable("mid") int mid) {
		/* Fetch employee object using given eid */
		
			
			 return transactionService.getTransactionsByManager(mid);
			
	}
	
	@GetMapping("/employee/all/{eid}")
	public List<Transaction> getTransactionsByEmployee(@PathVariable("eid") int eid) {
		/* Fetch employee object using given eid */
		
			
			 return transactionService.getTransactionsByEmployee(eid);
			
	}
	

}
