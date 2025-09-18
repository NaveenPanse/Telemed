package com.Navpaksa.Navpaksa.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.service.DoctorService;
import com.Navpaksa.Navpaksa.service.MedicalService;


@Controller
public class VideoCallController {
	
	@Autowired
	private MedicalService medicalService;
	@Autowired 
	private DoctorService doctorService;
	
	 @GetMapping("/video-call")
	    public String videoCallPage(@RequestParam UUID id, ModelMap model, Authentication authentication) {
		 
		 String role = ""; // fetch from security context or DB

	        // You can also fetch role from UserDetails if needed
	        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("DOCTOR")) {
	            role = "DOCTOR";
	        } else {
	            role = "MEDICAL";
	        }
	        
	        String cometchatUid="";
	        // Assume the username = CometChat UID
	        if(role.equals("DOCTOR")) {
	        	
	        	UUID userId = UUID.fromString(authentication.getName());
	    	    Doctor doctor=doctorService.findById(userId);
	        	
	        	model.addAttribute("name", doctor.getName());
	        	cometchatUid="doctor_" + doctor.getId().toString();
	        }
	        else {
	        	UUID userId = UUID.fromString(authentication.getName());
	    	    Medical medical=medicalService.findById(userId);
	    	    model.addAttribute("name", medical.getName());
	         cometchatUid = "medical_" + medical.getId().toString(); // e.g., "john123"
	        
	        }
	        String recieverUid="doctor_" +id.toString();
	        model.addAttribute("recieverUid", recieverUid);
	        model.addAttribute("cometchatUid", cometchatUid);
	        model.addAttribute("role", role);
	        return "video-call"; // resolves to video-call.html
	    }
	 
	 @GetMapping("/medical-call")
	    public String medicalVideoCallPage(@RequestParam("receiverUid") String receiverUid,
                @RequestParam("patientId") UUID patientId,
                ModelMap model,
                Authentication authentication) {
		 
		 String role = ""; // fetch from security context or DB
System.out.println("receiverUid=" + receiverUid);
	        
	        
	        String cometchatUid="";
	        
	        	UUID userId = UUID.fromString(authentication.getName());
	    	    Medical medical=medicalService.findById(userId);
	    	    model.addAttribute("name", medical.getName());
	         cometchatUid = "medical_" + medical.getId().toString(); // e.g., "john123"
	         String recieverUid="doctor_" +receiverUid.toString();
		       model.addAttribute("patientId", patientId);
	        model.addAttribute("receiverUid", recieverUid);
	        model.addAttribute("cometchatUid", cometchatUid);
	        model.addAttribute("role", role);
	        return "medical-call"; // resolves to video-call.html
	    }
	 
	 @GetMapping("/doctor-call")
	    public String doctorVideoCallPage(@RequestParam UUID id, ModelMap model, Authentication authentication) {
		 
		 String role = ""; // fetch from security context or DB

	        
	        
	        String cometchatUid="";
	        // Assume the username = CometChat UID
	       
	        	
	        	UUID userId = UUID.fromString(authentication.getName());
	    	    Doctor doctor=doctorService.findById(userId);
	        	
	        	model.addAttribute("name", doctor.getName());
	        	cometchatUid="doctor_" + doctor.getId().toString();
	        
	       
	        String recieverUid="doctor_" +id.toString();
	        model.addAttribute("recieverUid", recieverUid);
	        model.addAttribute("cometchatUid", cometchatUid);
	        model.addAttribute("role", role);
	        return "doctor-call"; // resolves to video-call.html
	    }
	 
}
