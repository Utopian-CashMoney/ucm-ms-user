package com.ucm.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucm.ms.entity.AccountType;
import com.ucm.ms.entity.CreditCard;

@Repository
public interface CreditCardDAO extends JpaRepository<CreditCard, String> {

	
	
	
}
