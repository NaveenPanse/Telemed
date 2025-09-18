package com.Navpaksa.Navpaksa.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.service.AdminService;
import com.Navpaksa.Navpaksa.service.CustomUserDetailsService;
import com.Navpaksa.Navpaksa.service.DoctorService;
import com.Navpaksa.Navpaksa.service.MedicalService;

@Controller
public class AuthController {
	
	public AuthController(CustomUserDetailsService userDetailService, DoctorService doctorService,
			AdminService adminService, MedicalService medicalService) {
		super();
		this.userDetailService = userDetailService;
		this.doctorService = doctorService;
		this.adminService = adminService;
		this.medicalService=medicalService;
	}

	private CustomUserDetailsService userDetailService;
	private DoctorService doctorService;
	private AdminService adminService;
	private MedicalService medicalService;
	@GetMapping("/index")
	public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap model) {
		
		if(error!=null) {
			model.addAttribute("error", "Invalid Creadentials! Or Session Already Active!");
		}
		return "index";
	}
	
	@GetMapping("/doctor/dashboard")
	public String doctorDashboard(Authentication authentication, ModelMap model) {
		try {
			UUID userId = UUID.fromString(authentication.getName());
	    Doctor doctor=doctorService.findById(userId);
	    System.out.println(authentication.getName());
		model.addAttribute("doctor", doctor);
		return "doctorDashboard";
	}
	catch(RuntimeException e) {
		model.addAttribute("error", e.getMessage());
		return "index";
	}
	}
	
	@GetMapping("/medical/dashboard")
	public String medicalDashboard(Authentication authentication, ModelMap model) {
		try {
			UUID userId = UUID.fromString(authentication.getName());
	    Medical medical=medicalService.findById(userId);
		model.addAttribute("medical", medical);
		
		return "medicalDashboard";
	}
	catch(RuntimeException e) {
		model.addAttribute("error", e.getMessage());
		return "index";
	}
	}
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard(ModelMap model) {
		try {
		Admin admin=adminService.getAdmin();
		model.addAttribute("admin", admin);
		return "adminDashboard";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "index";
		}
	}
}
