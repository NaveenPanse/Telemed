package com.Navpaksa.Navpaksa.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medicine;
import com.Navpaksa.Navpaksa.entity.Prescription;
import com.Navpaksa.Navpaksa.repository.PatientRepository;
import com.Navpaksa.Navpaksa.service.DoctorService;
import com.Navpaksa.Navpaksa.service.PrescriptionService;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/prescribe")
    public String showForm(@RequestParam UUID patientId, ModelMap model) {
        Prescription prescription = new Prescription();
        prescription.setPatient(patientRepository.findById(patientId).orElse(null));
        prescription.setMedicines(List.of(new Medicine())); // start with 1 row
        model.addAttribute("prescription", prescription);
        return "prescription-form"; // returns Thymeleaf HTML
    }

    @PostMapping("/save")
    public String savePrescription(@ModelAttribute Prescription prescription, Authentication authentication) {
    	UUID userId = UUID.fromString(authentication.getName());
	    Doctor doctor=doctorService.findById(userId);
	    prescription.setDoctorName(doctor.getName());
        prescriptionService.savePrescription(prescription);
        return "redirect:/doctor/dashboard";
    }
}