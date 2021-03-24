package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.FarmingAndProcessingInformationModule;
import com.ascention.validationWS.exportGS1.beans.FoodAndBeverageIngredientModule;

public interface FoodAndBeverageIngredientModuleDAO {
	public FoodAndBeverageIngredientModule getAllFoodAndBeverageIngredientModule(Integer Product_Id);

}
