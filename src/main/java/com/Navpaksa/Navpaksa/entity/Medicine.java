package com.Navpaksa.Navpaksa.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Medicine {
    public Medicine() {
		super();
	}

	public Medicine(UUID id, String name, String dose, int duration, String type,
			Prescription prescription) {
		super();
		this.id = id;
		this.name = name;
		this.dose = dose;
		this.duration = duration;
		this.type = type;
		this.prescription = prescription;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String dose;        // e.g., "Twice a day", "Once before sleep"
        
    private int duration;     // e.g., "5" days, "1" days
    private String type;        // e.g., "Oral", "Injection"

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

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

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

    
}