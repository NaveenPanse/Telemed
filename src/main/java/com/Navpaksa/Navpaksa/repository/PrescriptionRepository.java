package com.Navpaksa.Navpaksa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navpaksa.Navpaksa.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
	
}
