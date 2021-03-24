package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemHierarchyModule {

	private List<TradeItemHierarchyQuantity> lstTradeItemHierarchyQuantity;
	


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public List<TradeItemHierarchyQuantity> getLstTradeItemHierarchyQuantity() {
		return lstTradeItemHierarchyQuantity;
	}



	public void setLstTradeItemHierarchyQuantity(List<TradeItemHierarchyQuantity> lstTradeItemHierarchyQuantity) {
		this.lstTradeItemHierarchyQuantity = lstTradeItemHierarchyQuantity;
	}




}
