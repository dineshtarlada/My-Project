package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.model.Manager;



public interface ManagerRepository extends JpaRepository<Manager, Integer>{

	@Query("select m from Manager m join User u on m.user.id=u.id where u.id=?1")
	Manager getManagerByUserId(int uid);

	
}
