package com.Navpaksa.Navpaksa.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Navpaksa.Navpaksa.entity.Company;
import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.service.CompanyService;
import com.Navpaksa.Navpaksa.service.MedicalService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private MedicalService medicalService;
	
	@GetMapping("/add")
	@PreAuthorize("hasRole('MEDICAL')")
	public String addCompanyPage() {
		return "addCompanyPage";
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('MEDICAL')")
	public String addCompany(ModelMap model, Company company, Authentication authentication) {
		UUID userId = UUID.fromString(authentication.getName());
	    Medical medical=medicalService.findById(userId);
	 
		Company saved=companyService.saveCompany(medical, company);
		
		List<Company> companies=medical.getCompanies();
		model.addAttribute("companies", companies);
		return "inventoryManagement";
	}
	
	@PostMapping("/delete")
	@PreAuthorize("hasRole('MEDICAL')")
	public String deleteCompany(ModelMap model, @RequestParam UUID id, Authentication authentication) {
		
		try {
			companyService.deleteCompany(id);
			model.addAttribute("deleted", "Company deleted Successfully!");
			UUID userId = UUID.fromString(authentication.getName());
		    Medical medical=medicalService.findById(userId);
		    List<Company> companies=medical.getCompanies();
			model.addAttribute("companies", companies);
			return "inventoryManagement";
		}
		catch(RuntimeException e) {
			return "redirect:/medical/inventory";
		}
	}
	
	@GetMapping("/update")
	@PreAuthorize("hasRole('MEDICAL')")
	public String updatePage(@RequestParam UUID id, ModelMap model) {
		
		//System.out.println("Received ID: " + id);
		try {
		Company company=companyService.getById(id);
		model.addAttribute("company", company);
        return "companyUpdatePage";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }

	
	@PostMapping("/update")
    @PreAuthorize("hasRole('MEDICAL')")
    public String updateCompany(@ModelAttribute Company updatedCompany, ModelMap model) {
	
		try {
        Company updated = companyService.update( updatedCompany);
        return "redirect:/medical/inventory";
		}
		catch(RuntimeException e) {
			return "redirect:/medical/inventory";
		}
    }
	
	@GetMapping("/manage")
	@PreAuthorize("hasRole('MEDICAL')")
	public String manageCompany(@RequestParam UUID id, ModelMap model) {
		
		//System.out.println("Received ID: " + id);
		try {
		Company company=companyService.getById(id);
		model.addAttribute("company", company);
        return "manageCompany";
		}
		catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "errorPage";
		}
    }
	
}
