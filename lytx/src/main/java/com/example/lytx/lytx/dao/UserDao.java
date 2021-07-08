package com.example.lytx.lytx.dao;

import com.example.lytx.lytx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
