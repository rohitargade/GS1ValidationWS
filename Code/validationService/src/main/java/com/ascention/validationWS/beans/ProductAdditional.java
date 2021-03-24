package com.ascention.validationWS.beans;

public class ProductAdditional {
	String dg_class;
	String dg_subsidiary_class ;
	String dangerous_good;
	boolean eligibility;
	
	public boolean isEligibility() {
		return eligibility;
	}

	public void setEligibility(boolean eligibility) {
		this.eligibility = eligibility;
	}

	public ProductAdditional() {
		super();
	}
	
	public String getDg_class() {
		return dg_class;
	}
	public void setDg_class(String dg_class) {
		this.dg_class = dg_class;
	}
	public String getDg_subsidiary_class() {
		return dg_subsidiary_class;
	}
	public void setDg_subsidiary_class(String dg_subsidiary_class) {
		this.dg_subsidiary_class = dg_subsidiary_class;
	}
	public String getDangerous_good() {
		return dangerous_good;
	}
	public void setDangerous_good(String dangerous_good) {
		this.dangerous_good = dangerous_good;
	}
	
	
	
	
}
