package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Serving_Size;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;

public interface Nutrient_Serving_SizeDAO {
	public List<Nutrient_Serving_Size> getAllNutrient_Serving_Size(Integer Product_Id);

}
