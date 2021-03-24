package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Animal_Nutrient_Detail {
	private String Nutr_Type;
	private Double Nutr_Exact_Pct;
	private Double Nutr_Min_Pct;
	private Double Nutr_Max_Pct;


	public String getNutr_Type() {
		return Nutr_Type;
	}


	public void setNutr_Type(String nutr_Type) {
		Nutr_Type = nutr_Type;
	}


	public Double getNutr_Exact_Pct() {
		return Nutr_Exact_Pct;
	}


	public void setNutr_Exact_Pct(Double nutr_Exact_Pct) {
		Nutr_Exact_Pct = nutr_Exact_Pct;
	}


	public Double getNutr_Min_Pct() {
		return Nutr_Min_Pct;
	}


	public void setNutr_Min_Pct(Double nutr_Min_Pct) {
		Nutr_Min_Pct = nutr_Min_Pct;
	}


	public Double getNutr_Max_Pct() {
		return Nutr_Max_Pct;
	}


	public void setNutr_Max_Pct(Double nutr_Max_Pct) {
		Nutr_Max_Pct = nutr_Max_Pct;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
