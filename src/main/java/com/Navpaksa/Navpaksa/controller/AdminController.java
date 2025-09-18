package com.Navpaksa.Navpaksa.controller;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.repository.AdminRepository;
import com.Navpaksa.Navpaksa.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	public AdminController(AdminService adminService, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
		super();
		this.adminService = adminService;
		this.adminRepository=adminRepository;
		this.passwordEncoder=passwordEncoder;
	}

	private AdminService adminService;
	private AdminRepository adminRepository;
	private PasswordEncoder passwordEncoder;
	
	// Show edit form
    @GetMapping("/admin_update_page")
    public String editAdmin(ModelMap model) {
    	try {
        Admin admin = adminService.findById(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        model.addAttribute("admin", admin);
        return "adminUpdatePage";
    	}
    	catch(RuntimeException e) {
    		model.addAttribute("error", e.getMessage());
    		return "errorPage";
    	}
    }

    // Save changes
    @PostMapping("/update")
    public String updateAdmin(@ModelAttribute("admin") Admin updatedAdmin, ModelMap model) {
    	try {
        Admin admin = adminService.findById(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        
        admin.setName(updatedAdmin.getName());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setNumber(updatedAdmin.getNumber());

        if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        }

        adminRepository.save(admin);
        return "redirect:/admin/dashboard";
    	}
    	catch(RuntimeException e) {
    		model.addAttribute("error", e.getMessage());
    		return "errorPage";
    	}
    }
}


