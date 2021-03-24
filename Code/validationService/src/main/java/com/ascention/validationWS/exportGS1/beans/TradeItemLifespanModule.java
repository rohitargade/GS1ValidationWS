package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemLifespanModule {

	private List<TradeItemLifespan> lstTradeItemLifespan;
	


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public List<TradeItemLifespan> getLstTradeItemLifespan() {
		return lstTradeItemLifespan;
	}



	public void setLstTradeItemLifespan(List<TradeItemLifespan> lstTradeItemLifespan) {
		this.lstTradeItemLifespan = lstTradeItemLifespan;
	}



}
