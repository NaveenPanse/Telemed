package com.Navpaksa.Navpaksa.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.entity.Patient;
import com.Navpaksa.Navpaksa.service.AdminService;
import com.Navpaksa.Navpaksa.service.MedicalService;
import com.Navpaksa.Navpaksa.service.PatientService;

@Controller
@RequestMapping("/medical")
public class MedicalController {

	@Autowired
	private MedicalService medicalService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/add_medical_page")
	@PreAuthorize("hasRole('ADMIN')")
	public String getMedicalAdditionPage() {
		return "addMedicalPage";
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public String addMedical(Medical medical, ModelMap model) {
	    // Checks if medical with email already exists
	    if (medicalService.existsByEmail(medical.getEmail())) {
	        model.addAttribute("error", "Email already exists! Please use a different email.");
	        return "addMedicalPage";  
	    }
	    
	    try {
	    
	    Medical saved = medicalService.saveMedical(medical);
	    
	    model.addAttribute("added", "Medical Added Successfully!");
	    Admin admin=adminService.getAdmin();
	    model.addAttribute("admin", admin);
	    return "adminDashboard";
	    }
	    catch(RuntimeException e) {
	    	model.addAttribute("error", e.getMessage());
	    	return "errorPage";
	    }
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAllMedicals(ModelMap model) {
		try {
		List<Medical> medicals=medicalService.getAllMedicals();
		model.addAttribute("medicals", medicals);
        return "viewMedicals";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }
	
	@GetMapping("/getByEmail")
	@PreAuthorize("hasRole('ADMIN')")
	public String getByEmail(@RequestParam String email, ModelMap model) {
		try {
		Medical medical=medicalService.findByEmail(email);
        model.addAttribute("medicals", medical);
        return "viewMedicals";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }
	
	@GetMapping("/getById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Medical> getById(@PathVariable UUID id) {
		
		Medical medical=medicalService.getMedicalById(id);
        return ResponseEntity.ok(medical);
    }
	
	@GetMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public String updatePage(@RequestParam UUID id, ModelMap model) {
		//System.out.println("Received ID: " + id);
		try {
		Medical medical=medicalService.getMedicalById(id);
		model.addAttribute("medical", medical);
        return "medicalUpdatePage";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }

	
	@PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateMedical(@ModelAttribute Medical updatedMedical, ModelMap model) {
		try {
		//System.out.println("Received ID: " + updatedMedical.getId());
        Medical updated = medicalService.updateMedical( updatedMedical);
        return "redirect:/medical/all";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }
	
	@PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMedical(@RequestParam UUID id, ModelMap model) {
		try {
        medicalService.deleteMedical(id);
        model.addAttribute("deleted", "Medical Deleted Successfully!");
        List<Medical> medicals=medicalService.getAllMedicals();
		model.addAttribute("medicals", medicals);
        return "viewMedicals";
		}
		catch(RuntimeException e) {
			
			return "redirect:/medical/all";
		}
    }
	
	@GetMapping("/patients")
	@PreAuthorize("hasRole('MEDICAL')")
	public String medicalPatients(Authentication authentication, ModelMap model) {
		try {
			UUID userId = UUID.fromString(authentication.getName());
		    Medical medical=medicalService.findById(userId);
		
		List<Patient> patients=patientService.get_patients_of_a_medical(medical.getId());
		model.addAttribute("patients", patients);
		return "medicalPatients";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "medicalDashboard";
		}
	}
	
	@GetMapping("/inventory")
	@PreAuthorize("hasRole('MEDICAL')")
	public String inventoryManagement(Authentication authentication, ModelMap model) {
		UUID userId = UUID.fromString(authentication.getName());
	    Medical medical=medicalService.findById(userId);
	    model.addAttribute("companies", medical.getCompanies());
		return "inventoryManagement";
	}
}
