package com.Navpaksa.Navpaksa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.repository.DoctorRepository;

@Service
public class DoctorService {

	public DoctorService(CometChatService cometChatService, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
		super();
		this.cometChatService=cometChatService;
		this.doctorRepository = doctorRepository;
		this.passwordEncoder=passwordEncoder;
	}
	 
	    private CometChatService cometChatService;
	private DoctorRepository doctorRepository;
	private PasswordEncoder passwordEncoder;
	

	public Doctor findByEmail(String email) {
		Doctor doctor=doctorRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Doctor not found!"));
		return doctor;
	}
	
	public boolean existsByEmail(String email) {
	    return doctorRepository.findByEmail(email).isPresent();
	}

	public Doctor saveDoctor(Doctor doctor) {
		
		 
		doctor.setName("Dr."+doctor.getName());
		doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		Doctor saved=doctorRepository.save(doctor);
		
		// Create user in CometChat
		String uid="doctor_" + saved.getId().toString();
		cometChatService.createCometChatUser(uid, saved.getName());

		return saved;
	}

	public List<Doctor> getAllDoctors() {
		
		return doctorRepository.findAll();
	}
	
public Doctor getDoctorById(UUID id) {
		
		return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("No Doctor with given Id!"));
	}

public Doctor updateDoctor(Doctor doctor) {
    Doctor existing = getDoctorById(doctor.getId());
    existing.setName(doctor.getName());
    existing.setEmail(doctor.getEmail());
    existing.setNumber(doctor.getNumber());
    existing.setAddress(doctor.getAddress());
    return doctorRepository.save(existing);
}

public void deleteDoctor(UUID id) {
	
	doctorRepository.delete(getDoctorById(id));
	
}

public void save(Doctor doctor) {
	doctorRepository.save(doctor);
	
}

public Doctor findById(UUID userId) {
	System.out.println(userId);
	return doctorRepository.findById(userId).orElseThrow(()-> new RuntimeException("Doctor not founddddd!"));
}
}
