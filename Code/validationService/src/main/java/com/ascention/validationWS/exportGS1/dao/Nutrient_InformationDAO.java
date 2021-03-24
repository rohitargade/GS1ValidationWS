package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Information;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;

public interface Nutrient_InformationDAO {
	public List<Nutrient_Information> getAllNutrient_Information(Integer Product_Id);

}
