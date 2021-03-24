package com.ascention.validationWS.exportGS1.beans;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class NonfoodIngredientModule {
	private String Non_Food_Ingredient_Statement;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




	public String getNon_Food_Ingredient_Statement() {
		return Non_Food_Ingredient_Statement;
	}




	public void setNon_Food_Ingredient_Statement(String non_Food_Ingredient_Statement) {
		Non_Food_Ingredient_Statement = non_Food_Ingredient_Statement;
	}


}
