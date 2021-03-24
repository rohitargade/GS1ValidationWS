package com.ascention.validationWS.beans;


import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ValidationFailure {
	private Integer Client_Id;
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	private Integer Country_Origin_Id;
	private Integer Prod_Group;
	private Integer Validation_Rule_Id;
	private Date Create_Date;
	private Integer Hierarchy_Validation;
	
	
	public Integer getClient_Id() {
		return Client_Id;
	}



	public void setClient_Id(Integer client_Id) {
		Client_Id = client_Id;
	}



	public Integer getProduct_Id() {
		return Product_Id;
	}



	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}



	public Integer getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}



	public void setProduct_Hierarchy_Id(Integer product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}



	public Integer getCountry_Origin_Id() {
		return Country_Origin_Id;
	}



	public void setCountry_Origin_Id(Integer country_Origin_Id) {
		Country_Origin_Id = country_Origin_Id;
	}



	public Integer getProd_Group() {
		return Prod_Group;
	}



	public void setProd_Group(Integer prod_Group) {
		Prod_Group = prod_Group;
	}



	public Integer getValidation_Rule_Id() {
		return Validation_Rule_Id;
	}



	public void setValidation_Rule_Id(Integer validation_Rule_Id) {
		Validation_Rule_Id = validation_Rule_Id;
	}



	public Date getCreate_Date() {
		return Create_Date;
	}



	public void setCreate_Date(Date create_Date) {
		Create_Date = create_Date;
	}



	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	public Integer getHierarchy_Validation() {
		return Hierarchy_Validation;
	}



	public void setHierarchy_Validation(Integer hierarchy_Validation) {
		Hierarchy_Validation = hierarchy_Validation;
	}
}
