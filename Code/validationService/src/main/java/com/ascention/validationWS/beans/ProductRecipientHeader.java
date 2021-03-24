package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProductRecipientHeader {

	private Integer Product_Export_Hdr_Id;
	private Integer Client_Id;
	private Integer Org_Id;
	private String Target_Market;
	private Integer Product_Id;
	private String strHierarchyIds;
	private String strCOOIds;

	public String getStrHierarchyIds() {
		return strHierarchyIds;
	}


	public void setStrHierarchyIds(String strHierarchyIds) {
		this.strHierarchyIds = strHierarchyIds;
	}


	public String getStrCOOIds() {
		return strCOOIds;
	}


	public void setStrCOOIds(String strCOOIds) {
		this.strCOOIds = strCOOIds;
	}


	public Integer getProduct_Export_Hdr_Id() {
		return Product_Export_Hdr_Id;
	}


	public void setProduct_Export_Hdr_Id(Integer product_Export_Hdr_Id) {
		Product_Export_Hdr_Id = product_Export_Hdr_Id;
	}


	public Integer getClient_Id() {
		return Client_Id;
	}


	public void setClient_Id(Integer client_Id) {
		Client_Id = client_Id;
	}


	public Integer getOrg_Id() {
		return Org_Id;
	}


	public void setOrg_Id(Integer org_Id) {
		Org_Id = org_Id;
	}


	

	public Integer getProduct_Id() {
		return Product_Id;
	}


	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }


	public String getTarget_Market() {
		return Target_Market;
	}


	public void setTarget_Market(String target_Market) {
		Target_Market = target_Market;
	}
	
}
