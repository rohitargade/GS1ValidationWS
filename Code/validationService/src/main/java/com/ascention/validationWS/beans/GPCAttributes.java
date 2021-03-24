package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GPCAttributes {
	private String Gpc_Attr_Type_Code;
	private String Gpc_Attr_Value_Code;
	
	public String getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}
	public void setProduct_Hierarchy_Id(String product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}
	public String getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(String product_Id) {
		Product_Id = product_Id;
	}

	private String Product_Hierarchy_Id;
	private String Product_Id;
	public String getGpc_Attr_Type_Code() {
		return Gpc_Attr_Type_Code;
	}
	public void setGpc_Attr_Type_Code(String gpc_Attr_Type_Code) {
		Gpc_Attr_Type_Code = gpc_Attr_Type_Code;
	}
	public String getGpc_Attr_Value_Code() {
		return Gpc_Attr_Value_Code;
	}
	public void setGpc_Attr_Value_Code(String gpc_Attr_Value_Code) {
		Gpc_Attr_Value_Code = gpc_Attr_Value_Code;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
