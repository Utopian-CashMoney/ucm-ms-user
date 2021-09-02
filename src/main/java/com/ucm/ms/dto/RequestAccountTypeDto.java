package com.ucm.ms.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class RequestAccountTypeDto {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String type;

	@NotBlank
	private Boolean allow_credit;
	
	@NotBlank
	private BigDecimal credit_limit;
	
	@NotBlank
	private Boolean allowCards;
	
	@NotBlank
	private BigDecimal apr;

	@NotBlank
	private String perks;

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

	

	

	public Boolean getAllow_credit() {
		return allow_credit;
	}

	public void setAllow_credit(Boolean allow_credit) {
		this.allow_credit = allow_credit;
	}

	public BigDecimal getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(BigDecimal credit_limit) {
		this.credit_limit = credit_limit;
	}

	public Boolean getAllowCards() {
		return allowCards;
	}

	public void setAllowCards(Boolean allowCards) {
		this.allowCards = allowCards;
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
	
	
	
	
	

}
