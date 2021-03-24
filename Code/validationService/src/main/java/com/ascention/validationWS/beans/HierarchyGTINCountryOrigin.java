package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HierarchyGTINCountryOrigin {
	private String Country_Origin;
	private String State_Origin;
	private String Gtin;
	private String Country_Origin_Id;
	private String Addl_Prod_Num_Type;
	private String Addl_Prod_Num_Value;
	private String Organisation_Id;
	private String Product_Hierarchy_Id;
	private String Product_Id;
	
	
	
	public String getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}
	public void setProduct_Hierarchy_Id(String product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}
	public String getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(String product_Id) {
		Product_Id = product_Id;
	}
	public String getCountry_Origin() {
		return Country_Origin;
	}
	public void setCountry_Origin(String country_Origin) {
		Country_Origin = country_Origin;
	}
	public String getState_Origin() {
		return State_Origin;
	}
	public void setState_Origin(String state_Origin) {
		State_Origin = state_Origin;
	}
	public String getGtin() {
		return Gtin;
	}
	public void setGtin(String gtin) {
		Gtin = gtin;
	}
	public String getCountry_Origin_Id() {
		return Country_Origin_Id;
	}
	public void setCountry_Origin_Id(String country_Origin_Id) {
		Country_Origin_Id = country_Origin_Id;
	}
	public String getAddl_Prod_Num_Type() {
		return Addl_Prod_Num_Type;
	}
	public void setAddl_Prod_Num_Type(String addl_Prod_Num_Type) {
		Addl_Prod_Num_Type = addl_Prod_Num_Type;
	}
	public String getAddl_Prod_Num_Value() {
		return Addl_Prod_Num_Value;
	}
	public void setAddl_Prod_Num_Value(String addl_Prod_Num_Value) {
		Addl_Prod_Num_Value = addl_Prod_Num_Value;
	}
	public String getOrganisation_Id() {
		return Organisation_Id;
	}
	public void setOrganisation_Id(String organisation_Id) {
		Organisation_Id = organisation_Id;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
