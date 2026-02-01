package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.ForgotPassword;
import com.example.demo.entities.User;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
        
        @Query("SELECT fp FROM ForgotPassword fp WHERE fp.otp = ?1 AND fp.user = ?2")
        Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
        
        @Query("SELECT fp FROM ForgotPassword fp WHERE fp.user = ?1")
        Optional<ForgotPassword> findByUser(User user);
}