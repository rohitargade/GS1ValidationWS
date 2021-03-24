package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Header;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;

public interface Nutrient_HeaderDAO {
	public List<Nutrient_Header> getAllNutrient_Header(Integer Product_Id);

}
