package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class DurableGoodsCharacteristicsModule {
	private String Prod_Finish_Desc;
	

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public String getProd_Finish_Desc() {
		return Prod_Finish_Desc;
	}



	public void setProd_Finish_Desc(String prod_Finish_Desc) {
		Prod_Finish_Desc = prod_Finish_Desc;
	}
}
