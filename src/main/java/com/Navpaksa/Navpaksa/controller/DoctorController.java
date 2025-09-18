package com.Navpaksa.Navpaksa.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Patient;
import com.Navpaksa.Navpaksa.service.AdminService;
import com.Navpaksa.Navpaksa.service.DoctorService;
import com.Navpaksa.Navpaksa.service.MedicalService;
import com.Navpaksa.Navpaksa.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MedicalService medicalService;
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/add_doctor_page")
	@PreAuthorize("hasRole('ADMIN')")
	public String getDoctorAdditionPage() {
		try {
		return "addDoctorPage";
		}
		catch(RuntimeException e) {
		return	"redirect:/admin/dashboard";
		}
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public String addDoctor(Doctor doctor, ModelMap model) {
		
	    // Checks if doctor with email already exists
	    if (doctorService.existsByEmail(doctor.getEmail())) {
	        model.addAttribute("error", "Email already exists! Please use a different email.");
	        return "addDoctorPage";  
	    }
	    
	    try {
	    Doctor saved = doctorService.saveDoctor(doctor);
	    model.addAttribute("added", "Doctor Added Successfully!");
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
	@PreAuthorize("hasAnyRole('ADMIN','MEDICAL')")
	public String getAllDoctors(ModelMap model) {
		try {
		List<Doctor> doctors=doctorService.getAllDoctors();
		model.addAttribute("doctors", doctors);
        return "viewDoctors";
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
		Doctor doctor=doctorService.findByEmail(email);
        model.addAttribute("doctors", doctor);
        return "viewDoctors";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "viewDoctors";
		}
    }
	
	
	@GetMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public String updatePage(@RequestParam UUID id, ModelMap model) {
		
		//System.out.println("Received ID: " + id);
		try {
		Doctor doctor=doctorService.getDoctorById(id);
		model.addAttribute("doctor", doctor);
        return "doctorUpdatePage";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }

	
	@PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateDoctor(@ModelAttribute Doctor updatedDoctor, ModelMap model) {
	//	System.out.println("Received ID: " + updatedDoctor.getId());
		try {
        Doctor updated = doctorService.updateDoctor( updatedDoctor);
        return "redirect:/doctor/all";
		}
		catch(RuntimeException e) {
			return "redirect:/doctor/all";
		}
    }
	
	@PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDoctor(@RequestParam UUID id, ModelMap model) {
		try {
        doctorService.deleteDoctor(id);
        model.addAttribute("deleted", "Doctor Deleted Successfully!");
        List<Doctor> doctors=doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "viewDoctors";
		}
		catch(RuntimeException e) {
		return "redirect:/doctor/all";
		}
    }
	
	@GetMapping("/doctors_for_patient")
	@PreAuthorize("hasRole('MEDICAL')")
	public String getDoctors(@RequestParam("patientId") UUID patientId, ModelMap model) {
		try {
		List<Doctor> doctors=doctorService.getAllDoctors();
		//System.out.println("Doctors count: " + doctors.size());  // Debug print
		model.addAttribute("doctors", doctors);
		
	Patient patient=patientService.getPatient(patientId);
	model.addAttribute("patient", patient);
		return "doctors";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
	}
	
	// AJAX filter endpoint
    @GetMapping("/filter")
    public String filterDoctors(@RequestParam(required = false) String search,
                                @RequestParam(required = false) String specialization,
                                @RequestParam(required = false) String availability,
                                ModelMap model) {
        List<Doctor> doctors = doctorService.filterDoctors(search, specialization, availability);
        model.addAttribute("doctors", doctors);
        return "fragments/doctorCards :: doctorList"; // Thymeleaf fragment
    }
    
	@PostMapping("/heartbeat")
	@PreAuthorize("hasRole('DOCTOR')")
	@ResponseBody
	public ResponseEntity<?> heartbeat( Authentication authentication) {
		
		
		UUID userId = UUID.fromString(authentication.getName());
		System.out.println(userId);
	    Doctor doctor=doctorService.findById(userId);
	    if (doctor != null) {
	        doctor.setLastActiveAt(LocalDateTime.now());
	        
	        doctorService.save(doctor);
	    }
	   
	    return ResponseEntity.ok().build();
	}
}
