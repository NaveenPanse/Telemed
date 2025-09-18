package com.Navpaksa.Navpaksa.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {

	public Doctor() {
		super();
	}
	public Doctor(UUID id, String number, String email, String name, String password, String address, String license_no, LocalDateTime lastActiveAt, Role role, String specialization, String fees,
			List<String> patients) {
		super();
		this.id=id;
		this.number=number;
		this.email = email;
		this.name = name;
		this.password = password;
		this.address=address;
		this.license_no = license_no;
		
		this.lastActiveAt=lastActiveAt;
		this.role=role;
		this.specialization = specialization;
		this.fees = fees;
		this.patients = patients;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String number;
	private String email;
	private String name;
	private String password;
	private String address;
	private String license_no;
	
	private LocalDateTime lastActiveAt;
	@Enumerated(EnumType.STRING)
	private Role role=Role.ROLE_DOCTOR;
	private String specialization;
	private String fees;
	@Column(nullable = true) // list of patients can be null
	private List<String> patients;
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLicense_no() {
		return license_no;
	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public List<String> getPatients() {
		return patients;
	}
	public void setPatients(List<String> patients) {
		this.patients = patients;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LocalDateTime getLastActiveAt() {
		return lastActiveAt;
	}
	public void setLastActiveAt(LocalDateTime lastActiveAt) {
		this.lastActiveAt = lastActiveAt;
	} 
}
