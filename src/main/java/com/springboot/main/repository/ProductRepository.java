package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.springboot.main.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.name LIKE %?1%")
	List<Product> searchProductByName(String qStr);


	

}
