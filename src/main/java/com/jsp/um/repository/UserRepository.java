package com.jsp.um.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.um.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
