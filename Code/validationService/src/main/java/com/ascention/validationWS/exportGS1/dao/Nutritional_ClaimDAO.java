package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim;

public interface Nutritional_ClaimDAO {
	public List<Nutritional_Claim> getAllNutritional_Claim(Integer Product_Id);

}
