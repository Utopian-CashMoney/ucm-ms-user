package com.ucm.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucm.ms.entity.ConfirmToken;

/**
 * Confirmation Token Repository/ DAO Layer to interact
 * to interact with the database
 * 
 * @author Charvin Patel
 */


public interface ConfirmationTokenRepository extends JpaRepository<ConfirmToken, String> {
    ConfirmToken findByConfirmationToken(String confirmationToken);
}