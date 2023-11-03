package com.Demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.Demo.models.User;

public interface UserRepo  extends JpaRepository<User,Integer>{
 User findByEmail(String emialId);
}
