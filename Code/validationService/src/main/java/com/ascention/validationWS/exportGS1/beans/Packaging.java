package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging {
	private String RfId_On_Packaging;
	private String Packaging_Type_Code;
	private String Platform_Type;
	private String Shipping_Container_Qty_Desc;
	private String Packaging_Type_Desc;

	public String getRfId_On_Packaging() {
		return RfId_On_Packaging;
	}

	public void setRfId_On_Packaging(String rfId_On_Packaging) {
		RfId_On_Packaging = rfId_On_Packaging;
	}

	public String getPackaging_Type_Code() {
		return Packaging_Type_Code;
	}

	public void setPackaging_Type_Code(String packaging_Type_Code) {
		Packaging_Type_Code = packaging_Type_Code;
	}

	public String getPlatform_Type() {
		return Platform_Type;
	}

	public void setPlatform_Type(String platform_Type) {
		Platform_Type = platform_Type;
	}

	public String getShipping_Container_Qty_Desc() {
		return Shipping_Container_Qty_Desc;
	}

	public void setShipping_Container_Qty_Desc(String shipping_Container_Qty_Desc) {
		Shipping_Container_Qty_Desc = shipping_Container_Qty_Desc;
	}

	public String getPackaging_Type_Desc() {
		return Packaging_Type_Desc;
	}

	public void setPackaging_Type_Desc(String packaging_Type_Desc) {
		Packaging_Type_Desc = packaging_Type_Desc;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
