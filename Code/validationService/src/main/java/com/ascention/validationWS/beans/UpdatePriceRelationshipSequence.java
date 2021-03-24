package com.ascention.validationWS.beans;


import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class UpdatePriceRelationshipSequence {
	
	private Integer Price_Relationship_Id;


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }


	public Integer getPrice_Relationship_Id() {
		return Price_Relationship_Id;
	}


	public void setPrice_Relationship_Id(Integer price_Relationship_Id) {
		Price_Relationship_Id = price_Relationship_Id;
	}





}
