package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemVariant_Desc {
	private String Variant_Desc;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getVariant_Desc() {
		return Variant_Desc;
	}

	public void setVariant_Desc(String variant_Desc) {
		Variant_Desc = variant_Desc;
	}

}
