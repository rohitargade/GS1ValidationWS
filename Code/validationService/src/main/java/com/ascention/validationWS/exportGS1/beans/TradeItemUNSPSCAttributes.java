package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemUNSPSCAttributes {
	private Integer Product_ID;
	private String additionalTradeItemClassificationSystemCode;
	private String Unspsc_Version;
	private String Commodity_Id;
	private String Description;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Integer getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(Integer product_ID) {
		Product_ID = product_ID;
	}

	public String getAdditionalTradeItemClassificationSystemCode() {
		return additionalTradeItemClassificationSystemCode;
	}

	public void setAdditionalTradeItemClassificationSystemCode(String additionalTradeItemClassificationSystemCode) {
		this.additionalTradeItemClassificationSystemCode = additionalTradeItemClassificationSystemCode;
	}

	public String getUnspsc_Version() {
		return Unspsc_Version;
	}

	public void setUnspsc_Version(String unspsc_Version) {
		Unspsc_Version = unspsc_Version;
	}

	public String getCommodity_Id() {
		return Commodity_Id;
	}

	public void setCommodity_Id(String commodity_Id) {
		Commodity_Id = commodity_Id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	

}
