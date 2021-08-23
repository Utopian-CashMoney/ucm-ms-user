package com.ucm.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucm.ms.entity.UserAccount;


@Repository
public interface UserAccountDAO extends JpaRepository<UserAccount, String> {

	public UserAccount getUserIDByaccountNumber(String account_number);

}
