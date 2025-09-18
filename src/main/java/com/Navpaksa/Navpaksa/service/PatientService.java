package com.Navpaksa.Navpaksa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.entity.Patient;
import com.Navpaksa.Navpaksa.repository.PatientRepository;

@Service
public class PatientService {

	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	private PatientRepository patientRepository;

	public Patient savePatient(Medical medical, Patient patient) {
		
		patient.setName(patient.getFirst_name().trim()+" " + patient.getLast_name().trim());
		patient.setMedical_name(medical.getName());
		patient.setMedical_id(medical.getId());
		patient.setVisits(1);
		patient.setReg_date(LocalDate.now());
		List<LocalDate> dates=patient.getVisit_dates();
		dates.add(LocalDate.now());
		patient.setSugar(patient.getSugar()+" mg/dL");
		List<String> prev_bp=patient.getPrevious_bp();
		prev_bp.add(patient.getBp());
		
		List<String> prev_sugar=patient.getPrevious_sugar();
		prev_sugar.add(patient.getSugar());
		
		List<String> prev_problem=patient.getPrevious_problem();
		prev_problem.add(patient.getProblem());
		
		patient.setSpo2(patient.getSpo2()+"%");
		patient.setVisit_dates(dates);
       return patientRepository.save(patient);
	}

	public Patient getPatient(UUID patientId) {
		
		return patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("Patient Not Found!"));
		
		}

	public List<Patient> get_patients_of_a_medical(UUID id) {
		List<Patient> patients=patientRepository.findAll();
		List<Patient> ans=new ArrayList<>();
		for(Patient patient:patients) {
			if(patient.getMedical_id()==null) continue;
			if(patient.getMedical_id().equals(id)) {
				ans.add(patient);
			}
		}
		return ans;
	}

	public void deletePatient(UUID id) {
		
		Patient patient=patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient Not Found!"));
		
		patientRepository.delete(patient);
		
	}

	public Patient getPatientById(UUID id) {
		return patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient Not Found!"));
	}

	public Patient updatePatient(Patient patient) {
		
		Patient existing=patientRepository.findById(patient.getId()).orElseThrow(()-> new RuntimeException ("Patient Not Found!"));
		
		existing.setEmail(patient.getEmail());
		existing.setBp(patient.getBp());
		existing.setSpo2(patient.getSpo2());
		existing.setWeight(patient.getWeight());
		existing.setSugar(patient.getSugar());
		existing.setProblem(patient.getProblem());
		
		
		List<LocalDate> prev_dates=existing.getVisit_dates();
		if(prev_dates.size()>9) {
			prev_dates.remove(0);
		}
		if(!prev_dates.get(prev_dates.size() - 1).equals(LocalDate.now())){
			prev_dates.add(LocalDate.now());
			
			int visits=existing.getVisits();
			existing.setVisits(visits +1);
		}
		
		
		List<String> prev_bp=existing.getPrevious_bp();
		if(prev_bp.size()>9) {
			prev_bp.remove(0);
		}
		prev_bp.add(patient.getBp());
		
		List<String> prev_sugar=existing.getPrevious_sugar();
		if(prev_sugar.size()>9) {
			prev_sugar.remove(0);
		}
		prev_sugar.add(patient.getSugar());
		
		List<String> prev_problem=existing.getPrevious_problem();
		if(prev_problem.size()>9) {
			prev_problem.remove(0);
		}
		prev_problem.add(patient.getProblem());
		
		
		return patientRepository.save(existing);
	}

	
}
