package com.mohagi.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohagi.server.model.bookmarklist;

public interface bookmarklistRepository extends JpaRepository<bookmarklist, String> {

}
