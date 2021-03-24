package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemTemperatureInformationModule {

	private List<TradeItemTemperatureInformation> lstTradeItemTemperatureInformation;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public List<TradeItemTemperatureInformation> getLstTradeItemTemperatureInformation() {
		return lstTradeItemTemperatureInformation;
	}

	public void setLstTradeItemTemperatureInformation(List<TradeItemTemperatureInformation> lstTradeItemTemperatureInformation) {
		this.lstTradeItemTemperatureInformation = lstTradeItemTemperatureInformation;
	}




}
