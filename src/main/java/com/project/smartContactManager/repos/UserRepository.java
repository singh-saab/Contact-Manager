package com.project.smartContactManager.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.smartContactManager.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.email= :email")
	User findUserByUserName(@Param("email")String email);

}
