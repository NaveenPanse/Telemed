package com.Navpaksa.Navpaksa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navpaksa.Navpaksa.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

	Optional<Doctor> findByEmail(String email);

	Optional<Doctor> findById(UUID id);

}
