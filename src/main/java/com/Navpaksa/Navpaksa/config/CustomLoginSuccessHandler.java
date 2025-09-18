package com.Navpaksa.Navpaksa.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.repository.DoctorRepository;
import com.Navpaksa.Navpaksa.repository.MedicalRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private MedicalRepository medicalRepository;
	
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, 
	                                        HttpServletResponse response,
	                                        Authentication authentication)
	            throws IOException, ServletException {
	        
	    	String email=authentication.getName();
	    	request.getSession().setAttribute("email", email); //  store in session
	    	
	        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MEDICAL"))) {
	        
	            response.sendRedirect("/medical/dashboard");
	        }
	        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DOCTOR"))) {
	        	
	        	response.sendRedirect("/doctor/dashboard");
	        	
	        }
	        else {
	            response.sendRedirect("/admin/dashboard");
	        }
	    }
}
