package com.Demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.models.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
	Role findByRole(String name);
}
