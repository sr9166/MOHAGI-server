package com.mohagi.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohagi.server.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
}