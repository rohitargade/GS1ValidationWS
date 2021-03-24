package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemTargetMarket {
	private String Numeric_Id;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getNumeric_Id() {
		return Numeric_Id;
	}

	public void setNumeric_Id(String numeric_Id) {
		Numeric_Id = numeric_Id;
	}

	

}
