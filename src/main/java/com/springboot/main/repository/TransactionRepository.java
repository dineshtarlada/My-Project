package com.springboot.main.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.springboot.main.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("select t from Transaction t join Employee e on t.employee.id=e.id join User u on e.user.id=u.id where u.id =?1")
	List<Transaction> getTransactionsByEmployee(int eid);

	@Query("select t from Transaction t join Manager m on t.manager.id=m.id join User u on m.user.id=u.id where u.id =?1")
	List<Transaction> getTransactionsByManager(int mid);

}
