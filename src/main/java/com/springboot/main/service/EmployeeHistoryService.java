package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeHistory;
import com.springboot.main.repository.EmployeeHistoryRepository;

@Service
public class EmployeeHistoryService {
	
	@Autowired
	private EmployeeHistoryRepository employeeHistoryRepository;

	
	public EmployeeHistory getById(int id) throws InvalidIdException {
		Optional<EmployeeHistory> optional = employeeHistoryRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("EmployeeHistory  ID Invalid");
		}
		return optional.get();
	}


	public void DeleteHistoryById(EmployeeHistory employeeHistory) {
		employeeHistoryRepository.delete(employeeHistory);
		
	}


	public List<EmployeeHistory> getAllHistory(Pageable pageable) {
		// TODO Auto-generated method stub
		 return employeeHistoryRepository.findAll(pageable).getContent();
	}

}
