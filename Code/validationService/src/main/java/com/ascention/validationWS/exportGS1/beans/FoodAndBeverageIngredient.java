package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FoodAndBeverageIngredient {
	
	private String Ingredient_Name;
	private String Ingredient_Seq;
		
	

	public String getIngredient_Name() {
		return Ingredient_Name;
	}



	public void setIngredient_Name(String ingredient_Name) {
		Ingredient_Name = ingredient_Name;
	}



	public String getIngredient_Seq() {
		return Ingredient_Seq;
	}



	public void setIngredient_Seq(String ingredient_Seq) {
		Ingredient_Seq = ingredient_Seq;
	}



	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	
	
}
