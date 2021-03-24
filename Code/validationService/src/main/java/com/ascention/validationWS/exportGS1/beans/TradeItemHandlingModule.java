package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemHandlingModule {

	private List<TradeItemHandlingInformation> lstTradeItemHandlingInformation;
	


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public List<TradeItemHandlingInformation> getLstTradeItemHandlingInformation() {
		return lstTradeItemHandlingInformation;
	}



	public void setLstTradeItemHandlingInformation(List<TradeItemHandlingInformation> lstTradeItemHandlingInformation) {
		this.lstTradeItemHandlingInformation = lstTradeItemHandlingInformation;
	}




	



}
