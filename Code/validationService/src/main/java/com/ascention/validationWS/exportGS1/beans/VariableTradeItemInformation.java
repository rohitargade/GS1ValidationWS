package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class VariableTradeItemInformation {
	private String Is_Variable_Unit;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getIs_Variable_Unit() {
		return Is_Variable_Unit;
	}

	public void setIs_Variable_Unit(String is_Variable_Unit) {
		Is_Variable_Unit = is_Variable_Unit;
	}

}
