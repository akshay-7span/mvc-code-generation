package com.mvc_code_generation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mvc_code_generation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}