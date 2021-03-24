package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Serving_Size_Desc {
	private String Serving_Size_Desc;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getServing_Size_Desc() {
		return Serving_Size_Desc;
	}

	public void setServing_Size_Desc(String serving_Size_Desc) {
		Serving_Size_Desc = serving_Size_Desc;
	}



	

}
