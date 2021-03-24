package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AdditiveInformation;
import com.ascention.validationWS.exportGS1.beans.FoodAndBeverageIngredient;

public interface FoodAndBeverageIngredientDAO {
	public List<FoodAndBeverageIngredient> getAllFoodAndBeverageIngredient(Integer Product_Id);

}
