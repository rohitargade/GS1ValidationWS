package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class DutyFeeTaxInformationModule {
	private String Tax_Agency_Code;
	private String Tax_Type;
	private String Tax_Type_Desc;
	private String Exempt_Party_Code;
	private Float Tax_Rate;


	public String getTax_Agency_Code() {
		return Tax_Agency_Code;
	}


	public void setTax_Agency_Code(String tax_Agency_Code) {
		Tax_Agency_Code = tax_Agency_Code;
	}


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


	public String getExempt_Party_Code() {
		return Exempt_Party_Code;
	}


	public void setExempt_Party_Code(String exempt_Party_Code) {
		Exempt_Party_Code = exempt_Party_Code;
	}


	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public Float getTax_Rate() {
		return Tax_Rate;
	}


	public void setTax_Rate(Float tax_Rate) {
		Tax_Rate = tax_Rate;
	}
}
