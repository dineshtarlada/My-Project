package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.model.Employee;
import com.springboot.main.model.Hr;



public interface HrRepository extends JpaRepository<Hr, Integer>{

	@Query("select h from Hr h join User u on h.user.id=u.id where u.id=?1")
	Hr getHrByUserId(int uid);

}
