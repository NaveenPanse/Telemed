package com.Navpaksa.Navpaksa.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	public Company() {
		super();
	}
	public Company(UUID id, Medical medical, String name, String address, String number, String gstNo, String drug_lisc_no,
			LocalDate validity_drug, String food_lisc_no, LocalDate validity_food, List<Product> products) {
		super();
		this.id = id;
		this.medical=medical;
		this.name = name;
		this.address = address;
		this.number = number;
		this.gstNo = gstNo;
		this.drug_lisc_no = drug_lisc_no;
		this.validity_drug = validity_drug;
		this.food_lisc_no = food_lisc_no;
		this.validity_food = validity_food;
		this.products=products;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne
    @JoinColumn(name = "medical_id", nullable=false) // Foreign key column
    private Medical medical;
	private String name;
	private String address;
	private String number;
	private String gstNo;
	private String drug_lisc_no;
	private LocalDate validity_drug;
	private String food_lisc_no;
	private LocalDate validity_food;
	@Column(nullable = true)
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Product> products;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getDrug_lisc_no() {
		return drug_lisc_no;
	}
	public void setDrug_lisc_no(String drug_lisc_no) {
		this.drug_lisc_no = drug_lisc_no;
	}
	public LocalDate getValidity_drug() {
		return validity_drug;
	}
	public void setValidity_drug(LocalDate validity_drug) {
		this.validity_drug = validity_drug;
	}
	public String getFood_lisc_no() {
		return food_lisc_no;
	}
	public void setFood_lisc_no(String food_lisc_no) {
		this.food_lisc_no = food_lisc_no;
	}
	public LocalDate getValidity_food() {
		return validity_food;
	}
	public void setValidity_food(LocalDate validity_food) {
		this.validity_food = validity_food;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Medical getMedical() {
		return medical;
	}
	public void setMedical(Medical medical) {
		this.medical = medical;
	}
	
}
