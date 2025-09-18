package com.Navpaksa.Navpaksa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navpaksa.Navpaksa.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

	Optional<Patient> findById(UUID patientId);

}
