package com.Navpaksa.Navpaksa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TemporalType;

@Entity
public class Prescription {
    public Prescription() {
		super();
	}

	public Prescription(UUID id, Patient patient, String doctorName,  LocalDateTime consultationDate, String diagnosis,
			String instructions, LocalDate nextVisitDate, List<Medicine> medicines) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctorName = doctorName;
		this.consultationDate = consultationDate;
		this.diagnosis = diagnosis;
		this.instructions = instructions;
		this.nextVisitDate = nextVisitDate;
		this.medicines = medicines;
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Patient patient;

    
    private String  doctorName;

    
    private LocalDateTime consultationDate;

    private String diagnosis;

    private String instructions;

    private LocalDate nextVisitDate;

    @Column(nullable = true) 
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();

 

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	

	public LocalDateTime getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(LocalDateTime consultationDate) {
		this.consultationDate = consultationDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public LocalDate getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(LocalDate nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

    
}