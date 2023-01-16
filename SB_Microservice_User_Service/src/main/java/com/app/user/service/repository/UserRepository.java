package com.app.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user.service.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
