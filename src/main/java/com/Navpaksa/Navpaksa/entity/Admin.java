package com.Navpaksa.Navpaksa.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Admin {
    public Admin() {
		super();
	}



	public Admin(UUID id, String name, String number, String email, String password, Role role) {
		super();
		this.id = id;
		this.name=name;
		this.number=number;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Id
	private UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
	private String name;
    private String number;
    private String email ; // hard coded email of admin

    private String password; // Store hashed password

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_ADMIN;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id=UUID.fromString("00000000-0000-0000-0000-000000000001");
	}

	public String getEmail() {
		return email;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	

	
}