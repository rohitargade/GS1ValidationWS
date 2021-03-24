package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemDescriptionModule {

	private List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation;
	private List<TradeItemVariant_Desc> lstTradeItemVariant_Desc;


	public List<TradeItemDescriptionInformation> getLstTradeItemDescriptionInformation() {
		return lstTradeItemDescriptionInformation;
	}


	public void setLstTradeItemDescriptionInformation(
			List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation) {
		this.lstTradeItemDescriptionInformation = lstTradeItemDescriptionInformation;
	}


	public List<TradeItemVariant_Desc> getLstTradeItemVariant_Desc() {
		return lstTradeItemVariant_Desc;
	}


	public void setLstTradeItemVariant_Desc(List<TradeItemVariant_Desc> lstTradeItemVariant_Desc) {
		this.lstTradeItemVariant_Desc = lstTradeItemVariant_Desc;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




	



}
