package com.ucm.ms.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucm.ms.entity.AccountType;
import com.ucm.ms.entity.User;
import com.ucm.ms.entity.UserAccount;


@Repository
public interface UserAccountDAO extends JpaRepository<UserAccount, String> {

	public UserAccount getUserIDByaccountNumber(String account_number);

//	public UserAccount getOne(int userId);
	
	//public UserAccount findByUser(User userId);
	
	@Query(value = "SELECT * FROM user_account WHERE user_id = :userId", nativeQuery = true)
	public Collection<UserAccount> getUserAccounts(int userId);
	

}
