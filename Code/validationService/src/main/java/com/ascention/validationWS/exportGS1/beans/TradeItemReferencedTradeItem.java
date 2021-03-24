package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemReferencedTradeItem {
	private String Gtin;
	private String Reference_Type_Code;
	
	
	public String getGtin() {
		return Gtin;
	}


	public void setGtin(String gtin) {
		Gtin = gtin;
	}


	public String getReference_Type_Code() {
		return Reference_Type_Code;
	}


	public void setReference_Type_Code(String reference_Type_Code) {
		Reference_Type_Code = reference_Type_Code;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	

	

}
