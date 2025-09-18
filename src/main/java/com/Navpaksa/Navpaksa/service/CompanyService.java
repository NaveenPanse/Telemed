package com.Navpaksa.Navpaksa.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Company;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.repository.CompanyRepository;
import com.Navpaksa.Navpaksa.repository.MedicalRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private MedicalService medicalService;
	@Autowired
	private MedicalRepository medicalRepo;
	
	public Company saveCompany(Medical medical, Company company) {
		company.setMedical(medical);
		medicalRepo.save(medical);
		
		return companyRepo.save(company);
	}

	public List<Company> findByMedicalId(UUID id) {
		List<Company> companies=companyRepo.findByMedicalId(id);
		return companies;
	}

	public void deleteCompany(UUID id) {
		Company company=companyRepo.findById(id).orElseThrow(()-> new RuntimeException("Company not found!"));
		Medical medical = company.getMedical();
		medical.getCompanies().remove(company); // 
		medicalRepo.save(medical); 
		companyRepo.delete(company);
	}

	public Company getById(UUID id) {
		
		return companyRepo.findById(id).orElseThrow(()-> new RuntimeException("Company not found!"));
	}

	public Company update(Company updated) {
		Company existing =companyRepo.findById(updated.getId()).orElseThrow(()-> new RuntimeException("Company not found!"));
		existing.setName(updated.getName());
		existing.setNumber(updated.getNumber());
		existing.setAddress(updated.getAddress());
		
		return companyRepo.save(existing);
	}

}
