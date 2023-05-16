package com.radsouza.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radsouza.fullstackbackend.model.User1;

public interface UserRepository extends JpaRepository<User1, Long> {

}

