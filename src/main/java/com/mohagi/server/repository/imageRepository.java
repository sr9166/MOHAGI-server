package com.mohagi.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohagi.server.model.Image;

public interface imageRepository extends JpaRepository<Image, String> {
	
}
