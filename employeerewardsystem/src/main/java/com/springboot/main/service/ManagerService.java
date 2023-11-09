package com.springboot.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.main.model.Manager;
import com.springboot.main.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;

	public Manager insert(Manager manager) {
		// TODO Auto-generated method stub
		return managerRepository.save(manager);
	}

}
