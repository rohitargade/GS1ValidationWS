package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemHierarchyQuantity {

	private Integer quantityOfTradeItemsPerPallet;
	private Integer Carton_Layer_Per_Pallet;
	private Integer quantityOfTradeItemsPerPalletLayer;
	private Integer Child_Unit_Qty;
	private Integer quantityOfCompleteLayersContainedInATradeItem;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	

	public Integer getCarton_Layer_Per_Pallet() {
		return Carton_Layer_Per_Pallet;
	}

	public void setCarton_Layer_Per_Pallet(Integer carton_Layer_Per_Pallet) {
		Carton_Layer_Per_Pallet = carton_Layer_Per_Pallet;
	}

	

	public Integer getChild_Unit_Qty() {
		return Child_Unit_Qty;
	}

	public void setChild_Unit_Qty(Integer child_Unit_Qty) {
		Child_Unit_Qty = child_Unit_Qty;
	}

	public Integer getQuantityOfCompleteLayersContainedInATradeItem() {
		return quantityOfCompleteLayersContainedInATradeItem;
	}

	public void setQuantityOfCompleteLayersContainedInATradeItem(Integer quantityOfCompleteLayersContainedInATradeItem) {
		this.quantityOfCompleteLayersContainedInATradeItem = quantityOfCompleteLayersContainedInATradeItem;
	}



	public Integer getQuantityOfTradeItemsPerPallet() {
		return quantityOfTradeItemsPerPallet;
	}



	public void setQuantityOfTradeItemsPerPallet(Integer quantityOfTradeItemsPerPallet) {
		this.quantityOfTradeItemsPerPallet = quantityOfTradeItemsPerPallet;
	}



	public Integer getQuantityOfTradeItemsPerPalletLayer() {
		return quantityOfTradeItemsPerPalletLayer;
	}



	public void setQuantityOfTradeItemsPerPalletLayer(Integer quantityOfTradeItemsPerPalletLayer) {
		this.quantityOfTradeItemsPerPalletLayer = quantityOfTradeItemsPerPalletLayer;
	}






	



}
