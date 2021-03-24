package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyAlcoholPercentage;


public interface HierarchyAlcoholPercentageDAO {
	public List<HierarchyAlcoholPercentage> getAllAlcoholPercentageForProduct(Integer Product_Id, String strCOOIds, Integer Client_Id, Integer Org_Id);

}
