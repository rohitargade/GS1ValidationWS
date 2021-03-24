package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PhysiochemicalCharacteristic {
	private String Characteristic_Code;
	private Float Characteristic_Value;
	private String Characteristic_Value_Uom;


	public String getCharacteristic_Code() {
		return Characteristic_Code;
	}


	public void setCharacteristic_Code(String characteristic_Code) {
		Characteristic_Code = characteristic_Code;
	}


	

	public String getCharacteristic_Value_Uom() {
		return Characteristic_Value_Uom;
	}


	public void setCharacteristic_Value_Uom(String characteristic_Value_Uom) {
		Characteristic_Value_Uom = characteristic_Value_Uom;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public Float getCharacteristic_Value() {
		return Characteristic_Value;
	}


	public void setCharacteristic_Value(Float characteristic_Value) {
		Characteristic_Value = characteristic_Value;
	}

}
