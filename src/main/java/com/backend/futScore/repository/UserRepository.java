package com.backend.futScore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.futScore.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
