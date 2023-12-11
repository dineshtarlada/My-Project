package com.springboot.main.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeHistory;
import com.springboot.main.model.EmployeeProduct;
import com.springboot.main.model.Product;
import com.springboot.main.repository.EmployeeProductRepository;

@Service
public class EmployeeProductService {

	@Autowired
	private EmployeeProductRepository employeeProductRepository;

	public EmployeeProduct insert(EmployeeProduct employeeProduct) {

		return employeeProductRepository.save(employeeProduct);
	}
	public EmployeeProduct getById(int id) throws InvalidIdException {
		Optional<EmployeeProduct> optional = employeeProductRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("EmployeeProduct  ID Invalid");
		}
		return optional.get();
	}
	public void DeleteCartById(EmployeeProduct employeeProduct) {
		employeeProductRepository.delete(employeeProduct);
		
	}


}
