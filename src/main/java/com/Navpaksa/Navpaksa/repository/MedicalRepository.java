package com.Navpaksa.Navpaksa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navpaksa.Navpaksa.entity.Medical;

public interface MedicalRepository extends JpaRepository<Medical, UUID>{

	Optional<Medical> findByEmail(String email);

	Medical getById(UUID id);

}
