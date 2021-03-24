package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CountryActiveList {
	private Integer Country_Origin_Id;
	private String Is_Active;
	private String Country_Origin;
	
	public Integer getCountry_Origin_Id() {
		return Country_Origin_Id;
	}

	public void setCountry_Origin_Id(Integer country_Origin_Id) {
		Country_Origin_Id = country_Origin_Id;
	}

	public String getIs_Active() {
		return Is_Active;
	}

	public void setIs_Active(String is_Active) {
		Is_Active = is_Active;
	}

	public String getCountry_Origin() {
		return Country_Origin;
	}

	public void setCountry_Origin(String country_Origin) {
		Country_Origin = country_Origin;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
