package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class DietInformationModule {
	private String Diet_Type_Desc;
	private String Diet_Type_Code;
	private String Cert_Agency;
	private String Diet_Cert_Value;


	public String getDiet_Type_Desc() {
		return Diet_Type_Desc;
	}


	public void setDiet_Type_Desc(String diet_Type_Desc) {
		Diet_Type_Desc = diet_Type_Desc;
	}


	public String getDiet_Type_Code() {
		return Diet_Type_Code;
	}


	public void setDiet_Type_Code(String diet_Type_Code) {
		Diet_Type_Code = diet_Type_Code;
	}


	public String getCert_Agency() {
		return Cert_Agency;
	}


	public void setCert_Agency(String cert_Agency) {
		Cert_Agency = cert_Agency;
	}


	public String getDiet_Cert_Value() {
		return Diet_Cert_Value;
	}


	public void setDiet_Cert_Value(String diet_Cert_Value) {
		Diet_Cert_Value = diet_Cert_Value;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
