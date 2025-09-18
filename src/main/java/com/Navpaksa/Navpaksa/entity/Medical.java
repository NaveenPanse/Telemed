package com.Navpaksa.Navpaksa.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Medical {
    
	public Medical() {
		super();
	}
	public Medical(UUID id, String number,  String email, String name, String password, String address, String license_no, Role role,
			List<String> patients, List<Company> companies) {
		super();
		this.number=number;
		this.id=id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.license_no = license_no;
		this.role=role;
		this.patients = patients;
		this.companies=companies;
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
	@Enumerated(EnumType.STRING)
	private Role role=Role.ROLE_MEDICAL;
	@Column(nullable = true) // list of patients can be null
	private List<String> patients;
	@Column(nullable = true) // list of patients can be null
	 @OneToMany(mappedBy = "medical", cascade = CascadeType.ALL)
    private List<Company> companies = new ArrayList<>();
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id= id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
}
