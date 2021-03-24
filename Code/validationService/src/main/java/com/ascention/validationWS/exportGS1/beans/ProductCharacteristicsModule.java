package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProductCharacteristicsModule {
	private String Prod_Characteristic_Code;
	private String Prod_Characteristic_Desc;
	private Boolean Prod_Characteristic_Value_Boolean;
	

	public String getProd_Characteristic_Code() {
		return Prod_Characteristic_Code;
	}


	public void setProd_Characteristic_Code(String prod_Characteristic_Code) {
		Prod_Characteristic_Code = prod_Characteristic_Code;
	}


	public String getProd_Characteristic_Desc() {
		return Prod_Characteristic_Desc;
	}


	public void setProd_Characteristic_Desc(String prod_Characteristic_Desc) {
		Prod_Characteristic_Desc = prod_Characteristic_Desc;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public Boolean getProd_Characteristic_Value_Boolean() {
		return Prod_Characteristic_Value_Boolean;
	}


	public void setProd_Characteristic_Value_Boolean(Boolean prod_Characteristic_Value_Boolean) {
		Prod_Characteristic_Value_Boolean = prod_Characteristic_Value_Boolean;
	}


	

}
