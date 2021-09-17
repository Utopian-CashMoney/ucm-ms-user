package com.ucm.ms.dao;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucm.ms.entity.AccountType;
import com.ucm.ms.entity.User;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountType, Integer> {

	@Query(value = "SELECT * FROM account_type WHERE type = :type", nativeQuery = true)
	public Collection<AccountType> getLoans(String type);

	@Query(value = "SELECT * FROM account_type WHERE type = 'CREDIT'", nativeQuery = true)
	public Collection<AccountType> getCreditCards();

	
	public AccountType getIdByName(String name);
	
	public AccountType getAprByName(String name);

}
