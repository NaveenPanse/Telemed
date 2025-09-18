package com.Navpaksa.Navpaksa.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navpaksa.Navpaksa.entity.Company;
import com.Navpaksa.Navpaksa.entity.Doctor;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

	List<Company> findByMedicalId(UUID id);

}
