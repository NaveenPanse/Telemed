package com.Navpaksa.Navpaksa.initializer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.repository.AdminRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.findById(UUID.fromString("00000000-0000-0000-0000-000000000001")).isEmpty()) {
            Admin admin = new Admin();
            admin.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
            admin.setName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNumber("9999999999");
            adminRepository.save(admin);
            System.out.println("Default admin created: admin@gmail.com / admin123");
        }
    }
}
