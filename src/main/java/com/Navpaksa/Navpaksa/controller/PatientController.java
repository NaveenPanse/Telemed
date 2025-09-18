package com.Navpaksa.Navpaksa.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.entity.Patient;
import com.Navpaksa.Navpaksa.service.DoctorService;
import com.Navpaksa.Navpaksa.service.MedicalService;
import com.Navpaksa.Navpaksa.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	
	public PatientController(PatientService patientService,  DoctorService doctorService, MedicalService medicalService) {
		super();
		this.patientService = patientService;
		this.doctorService=doctorService;
		this.medicalService = medicalService;
	}

	private PatientService patientService;
private DoctorService doctorService;
	private MedicalService medicalService;
	
	@GetMapping("/add_patient_page")
	@PreAuthorize("hasRole('MEDICAL')")
	public String getPatientAdditionPage() {
		return "addPatientPage";
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('MEDICAL')")
	public String addPatient(Authentication authentication, Patient patient,  RedirectAttributes redirectAttributes) {
	      
		try {
			UUID userId = UUID.fromString(authentication.getName());
		    Medical medical=medicalService.findById(userId);
	     Patient savedPatient=patientService.savePatient(medical, patient);
	     
	     redirectAttributes.addFlashAttribute("success", true);
	     redirectAttributes.addFlashAttribute("patient", savedPatient);
	     return "redirect:/patient/add_patient_page"; // reload form with popup
		}
	     
	    catch(RuntimeException e) {
	    	redirectAttributes.addFlashAttribute("error", "Something went wrong!");
	        return "redirect:/patient/add_patient_page";
	    }
	}
	
	@GetMapping("/update")
	@PreAuthorize("hasRole('MEDICAL')")
	public String updatePage(@RequestParam UUID id, ModelMap model) {
		//System.out.println("Received ID: " + id);
		try {
		Patient patient=patientService.getPatientById(id);
		model.addAttribute("patient", patient);
        return "patientUpdatePage";
        }
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "medicalDashboard";
		}
    }
	
	@PostMapping("/update")
    @PreAuthorize("hasRole('MEDICAL')")
    public String updatePatient(@ModelAttribute Patient updatedPatient, ModelMap model) {
		try {
        Patient patient = patientService.updatePatient( updatedPatient);
        model.addAttribute("patient", patient);
        
        List<Doctor> doctors= doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/medical/patients";
		}
    }
	
	
	@PostMapping("/delete")
	@PreAuthorize("hasRole('MEDICAL')")
	public String deletePatient(@RequestParam UUID id, ModelMap model, Authentication authentication) {
		try {
		patientService.deletePatient(id);
		model.addAttribute("deleted", "Patient deleted Successfully!");
		UUID userId = UUID.fromString(authentication.getName());
	    Medical medical=medicalService.findById(userId);
		List<Patient> patients=patientService.get_patients_of_a_medical(medical.getId());
		
		model.addAttribute("patients", patients);
		return "medicalPatients";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/medical/patients";
		}
	}
	
	@GetMapping("/get")
	@ResponseBody
	public ResponseEntity<Patient> getPatient(@RequestParam UUID id) {
	    Patient patient = patientService.getPatientById(id);
	    if (patient != null) {
	        return ResponseEntity.ok(patient);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
