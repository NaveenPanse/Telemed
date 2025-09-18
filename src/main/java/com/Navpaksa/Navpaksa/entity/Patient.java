package com.Navpaksa.Navpaksa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {

	public Patient(UUID id, String email, String first_name, String last_name, String name, LocalDate dob,
			String gender, float height, float weight, String marital_status, List<String> previous_bp, List<String> previous_sugar,
			int age, String number, String address, String medical_name, int visits, String problem,
			List<Prescription> prescriptions, LocalDate reg_date, List<LocalDate> visit_dates, String bp, String sugar, String spo2, List<String> previous_problem, UUID medical_id) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.marital_status = marital_status;
		this.previous_bp = previous_bp;
		this.previous_sugar = previous_sugar;
		this.age = age;
		this.number = number;
		this.address = address;
		this.medical_name = medical_name;
		this.visits = visits;
		this.problem = problem;
		this.prescriptions = prescriptions;
		this.reg_date = reg_date;
		this.visit_dates = visit_dates;
		this.bp=bp;
		this.sugar=sugar;
		this.spo2=spo2;
		this.previous_problem=previous_problem;
		this.medical_id=medical_id;
	}

	public Patient() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String email;
	private String first_name;
	private String last_name;
	private String name="";
	private LocalDate dob;
	private String gender;
	private float height;
	private float weight;
	private String marital_status;
	private String bp;
	private String sugar;
	private String spo2;
	private List<String> previous_bp=new ArrayList<>(); // e.g., "120/80"
	private List<String> previous_sugar=new ArrayList<>();
	private int age;
	private String number;
	private String address;
	private String medical_name;
	private UUID medical_id;
	private int visits=0;
	private String problem; // category of problem the patient has
	private List<String> previous_problem=new ArrayList<>(); // list of all previous problems
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	@Column(nullable = true)
	@JsonIgnore
	private List<Prescription> prescriptions = new ArrayList<>();
	@Column(nullable = true) // can be null
	private LocalDate reg_date;
	List<LocalDate> visit_dates = new ArrayList<>();
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id=id;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	
	public String getMedical_name() {
		return medical_name;
	}
	public void setMedical_name(String medical_name) {
		this.medical_name = medical_name;
	}
	public List<LocalDate> getVisit_dates() {
		return visit_dates;
	}
	public void setVisit_dates(List<LocalDate> visit_dates) {
		this.visit_dates = visit_dates;
	}
	public LocalDate getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDate reg_date) {
		this.reg_date = reg_date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public List<String> getPrevious_bp() {
		return previous_bp;
	}

	public void setPrevious_bp(List<String> previous_bp) {
		this.previous_bp = previous_bp;
	}

	public List<String> getPrevious_sugar() {
		return previous_sugar;
	}

	public void setPrevious_sugar(List<String> previous_sugar) {
		this.previous_sugar = previous_sugar;
	}

	public String getSpo2() {
		return spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}

	public List<String> getPrevious_problem() {
		return previous_problem;
	}

	public void setPrevious_problem(List<String> previous_problem) {
		this.previous_problem = previous_problem;
	}

	public UUID getMedical_id() {
		return medical_id;
	}

	public void setMedical_id(UUID medical_id) {
		this.medical_id = medical_id;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	
	
}
