package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HierarchyOrderQty {
	private Integer Recipient_Id;
	private Integer Ord_Qty_Min;
	private Integer Ord_Qty_Multiple;
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

	public Integer getRecipient_Id() {
		return Recipient_Id;
	}

	public void setRecipient_Id(Integer recipient_Id) {
		Recipient_Id = recipient_Id;
	}

	public Integer getOrd_Qty_Min() {
		return Ord_Qty_Min;
	}

	public void setOrd_Qty_Min(Integer ord_Qty_Min) {
		Ord_Qty_Min = ord_Qty_Min;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Integer getOrd_Qty_Multiple() {
		return Ord_Qty_Multiple;
	}

	public void setOrd_Qty_Multiple(Integer ord_Qty_Multiple) {
		Ord_Qty_Multiple = ord_Qty_Multiple;
	}
}
