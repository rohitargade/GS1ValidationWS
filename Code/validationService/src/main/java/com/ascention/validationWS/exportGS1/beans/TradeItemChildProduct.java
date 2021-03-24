package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemChildProduct {
	private String Child_Gtin;
	private Integer Child_Unit_Qty;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getChild_Gtin() {
		return Child_Gtin;
	}

	public void setChild_Gtin(String child_Gtin) {
		Child_Gtin = child_Gtin;
	}

	public Integer getChild_Unit_Qty() {
		return Child_Unit_Qty;
	}

	public void setChild_Unit_Qty(Integer child_Unit_Qty) {
		Child_Unit_Qty = child_Unit_Qty;
	}

	

}
