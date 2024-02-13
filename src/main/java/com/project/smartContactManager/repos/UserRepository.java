package com.project.smartContactManager.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.smartContactManager.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
