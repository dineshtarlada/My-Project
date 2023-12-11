package com.springboot.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double transferredpoints;
	
	@ManyToOne
	private Employee employee;
	
	private LocalDate dateoftransfer;

	private String EmployeeName;

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDateoftransfer() {
		return dateoftransfer;
	}

	public void setDateoftransfer(LocalDate dateoftransfer) {
		this.dateoftransfer = dateoftransfer;
	}

	public double getTransferredpoints() {
		return transferredpoints;
	}

	public void setTransferredpoints(double transferredpoints) {
		this.transferredpoints = transferredpoints;
	}

}
