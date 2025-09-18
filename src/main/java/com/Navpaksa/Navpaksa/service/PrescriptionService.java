package com.Navpaksa.Navpaksa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Medicine;
import com.Navpaksa.Navpaksa.entity.Prescription;
import com.Navpaksa.Navpaksa.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	 @Autowired
	    private PrescriptionRepository prescriptionRepository;

	
	    public void savePrescription(Prescription prescription) {
	        for (Medicine med : prescription.getMedicines()) {
	            med.setPrescription(prescription);
	        }
	        
	        prescription.setConsultationDate(LocalDateTime.now());
	        prescriptionRepository.save(prescription);
	    }

	    
	    public List<Prescription> getPrescriptionsByPatient(UUID patientId) {
	        return prescriptionRepository.findAll().stream()
	                .filter(p -> p.getPatient().getId().equals(patientId))
	                .collect(Collectors.toList());
	    }
}
