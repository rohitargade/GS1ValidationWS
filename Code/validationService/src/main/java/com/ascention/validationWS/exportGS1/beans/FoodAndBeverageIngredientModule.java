package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FoodAndBeverageIngredientModule {
	private String Ingr_Statement;
	private List<FoodAndBeverageIngredient> lstFoodAndBeverageIngredient;
	private List<AdditiveInformation> lstAdditiveInformation;
	
	
	
	

	public String getIngr_Statement() {
		return Ingr_Statement;
	}





	public void setIngr_Statement(String ingr_Statement) {
		Ingr_Statement = ingr_Statement;
	}





	public List<FoodAndBeverageIngredient> getLstFoodAndBeverageIngredient() {
		return lstFoodAndBeverageIngredient;
	}





	public void setLstFoodAndBeverageIngredient(List<FoodAndBeverageIngredient> lstFoodAndBeverageIngredient) {
		this.lstFoodAndBeverageIngredient = lstFoodAndBeverageIngredient;
	}





	public List<AdditiveInformation> getLstAdditiveInformation() {
		return lstAdditiveInformation;
	}





	public void setLstAdditiveInformation(List<AdditiveInformation> lstAdditiveInformation) {
		this.lstAdditiveInformation = lstAdditiveInformation;
	}





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


}
