package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HierarchyAlcoholPercentage {
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	private Float Alcohol_By_Vol;
	private Integer Country_Origin_Id;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
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

	public Float getAlcohol_By_Vol() {
		return Alcohol_By_Vol;
	}

	public void setAlcohol_By_Vol(Float alcohol_By_Vol) {
		Alcohol_By_Vol = alcohol_By_Vol;
	}

	
}
