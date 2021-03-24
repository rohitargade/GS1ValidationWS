package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.AlcoholPercentage;


public interface AlcoholPercentageDAO {
	public List<AlcoholPercentage> getAllAlcoholPercentageForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
