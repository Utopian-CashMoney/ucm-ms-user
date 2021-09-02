package com.ucm.ms.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(	name = "account_type")
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "type")
    String type;
	
	@NotBlank
	@Column(name = "allow_credit")
	private boolean allow_credit;
	
	@NotBlank
	@Column(name = "credit_limit")
	private BigDecimal credit_limit;
	
	@NotBlank
	@Column(name = "allow_cards")
	private boolean allow_cards;
	
	@NotBlank
	@Column(name = "apr")
	private BigDecimal apr;
	
	@Column(name = "perks")
	private String perks;
	
	@OneToMany(mappedBy="account_type")
    @JsonBackReference
	private Set<UserAccount> UserAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	// IF YOU UN-COMMENT THIS GETTER/SETTER THEN CIRCULAR JACKSON MAPPING RECURSION OCCURS
	// WHICH WONT DISPLAY ALL THE CREDIT CARDS IN THE UCM-UI-USER(CLICK ON CREDIT CARDS TAB ONCE
	// LOGGED IN)
//	public Set<UserAccount> getUserAccount() {
//		return UserAccount;
//	}
//
//	public void setUserAccount(Set<UserAccount> userAccount) {
//		UserAccount = userAccount;
//	}


	public boolean isAllow_credit() {
		return allow_credit;
	}

	public void setAllow_credit(boolean allow_credit) {
		this.allow_credit = allow_credit;
	}



	public BigDecimal getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(BigDecimal credit_limit) {
		this.credit_limit = credit_limit;
	}


	

	public boolean isAllow_cards() {
		return allow_cards;
	}

	public void setAllow_cards(boolean allow_cards) {
		this.allow_cards = allow_cards;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public String getPerks() {
		return perks;
	}

	public void setPerks(String perks) {
		this.perks = perks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserAccount == null) ? 0 : UserAccount.hashCode());
		result = prime * result + (allow_cards ? 1231 : 1237);
		result = prime * result + (allow_credit ? 1231 : 1237);
		result = prime * result + ((apr == null) ? 0 : apr.hashCode());
		result = prime * result + ((credit_limit == null) ? 0 : credit_limit.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((perks == null) ? 0 : perks.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountType other = (AccountType) obj;
		if (UserAccount == null) {
			if (other.UserAccount != null)
				return false;
		} else if (!UserAccount.equals(other.UserAccount))
			return false;
		if (allow_cards != other.allow_cards)
			return false;
		if (allow_credit != other.allow_credit)
			return false;
		if (apr == null) {
			if (other.apr != null)
				return false;
		} else if (!apr.equals(other.apr))
			return false;
		if (credit_limit == null) {
			if (other.credit_limit != null)
				return false;
		} else if (!credit_limit.equals(other.credit_limit))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (perks == null) {
			if (other.perks != null)
				return false;
		} else if (!perks.equals(other.perks))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	
	
	
	
}
