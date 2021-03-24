package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PlaceOfProductActivity {
	private String Cntry_Origin_Statement;
	private String Numeric_Id;
	private String State_Origin;

	public String getCntry_Origin_Statement() {
		return Cntry_Origin_Statement;
	}

	public void setCntry_Origin_Statement(String cntry_Origin_Statement) {
		Cntry_Origin_Statement = cntry_Origin_Statement;
	}

	

	public String getState_Origin() {
		return State_Origin;
	}

	public void setState_Origin(String state_Origin) {
		State_Origin = state_Origin;
	}

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
