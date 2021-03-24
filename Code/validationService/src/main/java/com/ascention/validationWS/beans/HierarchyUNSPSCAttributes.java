package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HierarchyUNSPSCAttributes {
	private String Unspsc_Version;
	private String Family_Id;
	private String Segment_Id;
	private String Class_Id;
	private String Commodity_Id;
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	
	
	public Integer getProduct_Id() {
		return Product_Id;
	}


	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}


	public Integer getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}


	public void setProduct_Hierarchy_Id(Integer product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}


	public String getUnspsc_Version() {
		return Unspsc_Version;
	}


	public void setUnspsc_Version(String unspsc_Version) {
		Unspsc_Version = unspsc_Version;
	}


	public String getFamily_Id() {
		return Family_Id;
	}


	public void setFamily_Id(String family_Id) {
		Family_Id = family_Id;
	}


	public String getSegment_Id() {
		return Segment_Id;
	}


	public void setSegment_Id(String segment_Id) {
		Segment_Id = segment_Id;
	}


	public String getClass_Id() {
		return Class_Id;
	}


	public void setClass_Id(String class_Id) {
		Class_Id = class_Id;
	}


	public String getCommodity_Id() {
		return Commodity_Id;
	}


	public void setCommodity_Id(String commodity_Id) {
		Commodity_Id = commodity_Id;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
