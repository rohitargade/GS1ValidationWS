package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemTemperatureInformation {
	private Float Max_Temp;
	private String Max_Temp_Uom;
	private Float Min_Temp;
	private String Min_Temp_Uom;
	private String Temp_Qualifier_Code;

	public Float getMax_Temp() {
		return Max_Temp;
	}

	public void setMax_Temp(Float max_Temp) {
		Max_Temp = max_Temp;
	}

	public String getMax_Temp_Uom() {
		return Max_Temp_Uom;
	}

	public void setMax_Temp_Uom(String max_Temp_Uom) {
		Max_Temp_Uom = max_Temp_Uom;
	}

	public Float getMin_Temp() {
		return Min_Temp;
	}

	public void setMin_Temp(Float min_Temp) {
		Min_Temp = min_Temp;
	}

	public String getMin_Temp_Uom() {
		return Min_Temp_Uom;
	}

	public void setMin_Temp_Uom(String min_Temp_Uom) {
		Min_Temp_Uom = min_Temp_Uom;
	}

	public String getTemp_Qualifier_Code() {
		return Temp_Qualifier_Code;
	}

	public void setTemp_Qualifier_Code(String temp_Qualifier_Code) {
		Temp_Qualifier_Code = temp_Qualifier_Code;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
