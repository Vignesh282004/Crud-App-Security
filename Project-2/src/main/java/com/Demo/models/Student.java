package com.Demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Give a Name")
	@Column(name = "name")
	private String name;
	
	@Email
	@Column(name = "email")
	@NotBlank(message = "give an email")
	private String email;
	
	@Column(name = "phone")
	@NotNull(message = "give a phone number")
	private long phone;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(@NotBlank(message = "Give a Name") String name, @Email String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	
	
	

	
	
	
}

