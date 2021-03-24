package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemMeasurementsModule {

	private List<TradeItemMeasurements> lstTradeItemMeasurements;
	private List<TradeItemPegMeasurements> lstTradeItemPegMeasurements;
	private List<TradeItemNetContent> lstTradeItemNetContent;


	public List<TradeItemMeasurements> getLstTradeItemMeasurements() {
		return lstTradeItemMeasurements;
	}


	public void setLstTradeItemMeasurements(List<TradeItemMeasurements> lstTradeItemMeasurements) {
		this.lstTradeItemMeasurements = lstTradeItemMeasurements;
	}


	public List<TradeItemPegMeasurements> getLstTradeItemPegMeasurements() {
		return lstTradeItemPegMeasurements;
	}


	public void setLstTradeItemPegMeasurements(List<TradeItemPegMeasurements> lstTradeItemPegMeasurements) {
		this.lstTradeItemPegMeasurements = lstTradeItemPegMeasurements;
	}


	public List<TradeItemNetContent> getLstTradeItemNetContent() {
		return lstTradeItemNetContent;
	}


	public void setLstTradeItemNetContent(List<TradeItemNetContent> lstTradeItemNetContent) {
		this.lstTradeItemNetContent = lstTradeItemNetContent;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




}
