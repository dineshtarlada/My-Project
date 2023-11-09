package com.springboot.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double pointsGiven;
	private double oldPoints;
	private double newPoints;
	private String comments;
	private LocalDate dateOfPurchase;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPointsGiven() {
		return pointsGiven;
	}

	public void setPointsGiven(double pointsGiven) {
		this.pointsGiven = pointsGiven;
	}

	public double getOldPoints() {
		return oldPoints;
	}

	public void setOldPoints(double oldPoints) {
		this.oldPoints = oldPoints;
	}

	public double getNewPoints() {
		return newPoints;
	}

	public void setNewPoints(double newPoints) {
		this.newPoints = newPoints;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
