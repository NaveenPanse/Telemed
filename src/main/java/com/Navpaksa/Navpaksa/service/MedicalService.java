package com.Navpaksa.Navpaksa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Company;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.repository.DoctorRepository;
import com.Navpaksa.Navpaksa.repository.MedicalRepository;

@Service
public class MedicalService {

	public MedicalService(MedicalRepository medicalRepository, PasswordEncoder passwordEncoder, CometChatService cometChatService) {
		super();
		this.medicalRepository = medicalRepository;
		this.passwordEncoder=passwordEncoder;
		this.cometChatService=cometChatService;
	}

	
	private MedicalRepository medicalRepository;
	
	private PasswordEncoder passwordEncoder;
	private CometChatService cometChatService;
	//add new medical
	public Medical saveMedical(Medical medical) {
		
		
		medical.setPassword(passwordEncoder.encode(medical.getPassword()));
		Medical saved= medicalRepository.save(medical);
		// Create user in CometChat
		String uid="medical_" + saved.getId().toString();
		
        cometChatService.createCometChatUser(uid, saved.getName());

		return saved;
	}

	//get list of all medicals
	public List<Medical> getAllMedicals() {
		
		return medicalRepository.findAll();
	}

	public Medical findByEmail(String email) {
		return medicalRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No Medical with given email!"));
		
	}

	public Medical updateMedical(Medical medical) {
        Medical existing = getMedicalById(medical.getId());
        existing.setName(medical.getName());
        existing.setEmail(medical.getEmail());
        existing.setNumber(medical.getNumber());
        existing.setAddress(medical.getAddress());
        return medicalRepository.save(existing);
    }

	public Medical getMedicalById(UUID id) {
		
		Medical medical= medicalRepository.getById(id);
		if(medical==null) {
			throw new RuntimeException("No Medical with given Id!");
		}
		return medical;
	}

	public void deleteMedical(UUID id) {
		Medical medical=getMedicalById(id);
		medicalRepository.delete(medical);
	}
	
	public boolean existsByEmail(String email) {
	    return medicalRepository.findByEmail(email).isPresent();
	}

	public Medical getMedicalByEmail(String email) {
		
		Medical medical= medicalRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Medical not found!"));
		return medical;
	}

	public Medical findById(UUID userId) {
		
		return medicalRepository.findById(userId).orElseThrow(()-> new RuntimeException("Medical not found!"));
	}

	
}
