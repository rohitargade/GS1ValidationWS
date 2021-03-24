package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemSizeModule {

	private List<TradeItemSize> lstTradeItemSize;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public List<TradeItemSize> getLstTradeItemSize() {
		return lstTradeItemSize;
	}

	public void setLstTradeItemSize(List<TradeItemSize> lstTradeItemSize) {
		this.lstTradeItemSize = lstTradeItemSize;
	}

	


}
