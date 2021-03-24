package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemAdditionalTradeItem {
	private String ARTG_ID;
	public String getARTG_ID() {
		return ARTG_ID;
	}
	public void setARTG_ID(String aRTG_ID) {
		ARTG_ID = aRTG_ID;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	

}
