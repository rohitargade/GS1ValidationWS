package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Selling_Uom {

	private String Selling_Uom;



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}





	public String getSelling_Uom() {
		return Selling_Uom;
	}



	public void setSelling_Uom(String selling_Uom) {
		Selling_Uom = selling_Uom;
	}


	



}
