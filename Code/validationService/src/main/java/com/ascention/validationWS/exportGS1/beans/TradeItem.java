package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItem {

	private List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails;
	private List<TradeItemTargetMarket> lstTradeItemTargetMarket;
	private List<TradeItemHierarchy> lstTradeItemHierarchy;
	private List<TradeItemGPCAttributes> lstTradeItemGPCAttributes;
	private List<TradeItemUNSPSCAttributes> lstTradeItemUNSPSCAttributes;
	private List<TradeItemChildProduct> lstTradeItemChildProduct;



	public List<TradeItemInformationProviderDetails> getLstTradeItemInformationProviderDetails() {
		return lstTradeItemInformationProviderDetails;
	}



	public void setLstTradeItemInformationProviderDetails(
			List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails) {
		this.lstTradeItemInformationProviderDetails = lstTradeItemInformationProviderDetails;
	}



	public List<TradeItemTargetMarket> getLstTradeItemTargetMarket() {
		return lstTradeItemTargetMarket;
	}



	public void setLstTradeItemTargetMarket(List<TradeItemTargetMarket> lstTradeItemTargetMarket) {
		this.lstTradeItemTargetMarket = lstTradeItemTargetMarket;
	}



	public List<TradeItemHierarchy> getLstTradeItemHierarchy() {
		return lstTradeItemHierarchy;
	}



	public void setLstTradeItemHierarchy(List<TradeItemHierarchy> lstTradeItemHierarchy) {
		this.lstTradeItemHierarchy = lstTradeItemHierarchy;
	}



	public List<TradeItemGPCAttributes> getLstTradeItemGPCAttributes() {
		return lstTradeItemGPCAttributes;
	}



	public void setLstTradeItemGPCAttributes(List<TradeItemGPCAttributes> lstTradeItemGPCAttributes) {
		this.lstTradeItemGPCAttributes = lstTradeItemGPCAttributes;
	}



	public List<TradeItemUNSPSCAttributes> getLstTradeItemUNSPSCAttributes() {
		return lstTradeItemUNSPSCAttributes;
	}



	public void setLstTradeItemUNSPSCAttributes(List<TradeItemUNSPSCAttributes> lstTradeItemUNSPSCAttributes) {
		this.lstTradeItemUNSPSCAttributes = lstTradeItemUNSPSCAttributes;
	}



	public List<TradeItemChildProduct> getLstTradeItemChildProduct() {
		return lstTradeItemChildProduct;
	}



	public void setLstTradeItemChildProduct(List<TradeItemChildProduct> lstTradeItemChildProduct) {
		this.lstTradeItemChildProduct = lstTradeItemChildProduct;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




	



}
