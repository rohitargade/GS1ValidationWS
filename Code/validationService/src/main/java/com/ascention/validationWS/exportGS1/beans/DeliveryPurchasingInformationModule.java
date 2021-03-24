package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class DeliveryPurchasingInformationModule {
	private String Order_Uom;
	private String Is_Non_Sold_Prod_Returnable;
	
	

	public String getOrder_Uom() {
		return Order_Uom;
	}



	public void setOrder_Uom(String order_Uom) {
		Order_Uom = order_Uom;
	}



	public String getIs_Non_Sold_Prod_Returnable() {
		return Is_Non_Sold_Prod_Returnable;
	}



	public void setIs_Non_Sold_Prod_Returnable(String is_Non_Sold_Prod_Returnable) {
		Is_Non_Sold_Prod_Returnable = is_Non_Sold_Prod_Returnable;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
