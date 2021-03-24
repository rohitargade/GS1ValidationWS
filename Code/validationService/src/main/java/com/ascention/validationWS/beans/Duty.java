package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Duty {
	private String Tax_Type;
	private String Tax_Type_Desc;
	private String Value_Type;
	private String Tax_Agency_Code;
	private String Exempt_Party_Code;
	private String Country;
	private String Tax_Rate;
	
	
	public String getTax_Type() {
		return Tax_Type;
	}
	public void setTax_Type(String tax_Type) {
		Tax_Type = tax_Type;
	}
	public String getTax_Type_Desc() {
		return Tax_Type_Desc;
	}
	public void setTax_Type_Desc(String tax_Type_Desc) {
		Tax_Type_Desc = tax_Type_Desc;
	}
	public String getValue_Type() {
		return Value_Type;
	}
	public void setValue_Type(String value_Type) {
		Value_Type = value_Type;
	}
	public String getTax_Agency_Code() {
		return Tax_Agency_Code;
	}
	public void setTax_Agency_Code(String tax_Agency_Code) {
		Tax_Agency_Code = tax_Agency_Code;
	}
	public String getExempt_Party_Code() {
		return Exempt_Party_Code;
	}
	public void setExempt_Party_Code(String exempt_Party_Code) {
		Exempt_Party_Code = exempt_Party_Code;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getTax_Rate() {
		return Tax_Rate;
	}
	public void setTax_Rate(String tax_Rate) {
		Tax_Rate = tax_Rate;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
