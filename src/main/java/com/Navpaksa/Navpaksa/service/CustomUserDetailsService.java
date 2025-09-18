package com.Navpaksa.Navpaksa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.entity.Doctor;
import com.Navpaksa.Navpaksa.entity.Medical;
import com.Navpaksa.Navpaksa.repository.AdminRepository;
import com.Navpaksa.Navpaksa.repository.DoctorRepository;
import com.Navpaksa.Navpaksa.repository.MedicalRepository;
import com.Navpaksa.Navpaksa.userdetails.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check if doctor
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            return new CustomUserDetails(
                doctor.getId(),         // UUID
                doctor.getEmail(),
                doctor.getPassword(),
                doctor.getRole().name()
            );
        }

        // Check if medical
        Optional<Medical> medicalOpt = medicalRepository.findByEmail(email);
        if (medicalOpt.isPresent()) {
            Medical medical = medicalOpt.get();
            return new CustomUserDetails(
                medical.getId(),
                medical.getEmail(),
                medical.getPassword(),
                medical.getRole().name()
            );
        }

        // Check if admin
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new CustomUserDetails(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole().name()
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
