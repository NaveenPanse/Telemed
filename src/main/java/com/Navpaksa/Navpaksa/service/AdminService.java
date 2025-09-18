package com.Navpaksa.Navpaksa.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Navpaksa.Navpaksa.entity.Admin;
import com.Navpaksa.Navpaksa.repository.AdminRepository;

@Service
public class AdminService {

	public AdminService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	private AdminRepository adminRepository;

	public Admin getAdmin() {
		Admin admin=adminRepository.findById(UUID.fromString("00000000-0000-0000-0000-000000000001")).orElseThrow(() -> new RuntimeException(" Admin not found!"));
		return admin;
	}

	public Admin findById(UUID l) {
		
		Admin admin=adminRepository.findById(l).orElseThrow(() -> new RuntimeException(" Admin not found!"));
		return admin;
	}
	
	
}
