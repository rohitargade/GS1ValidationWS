package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.ValidationRule;

public interface ValidationRuleDAO {
	public List<ValidationRule> findAll(String Prod_Group, String Product_Sector, Integer Client_Id, Integer Validation_Domain_Id, Integer Hierarchy_Rule);

	public ValidationRule getProductGroupSectorValuesById(String Prod_Group, Integer Client_Id);
	
	public List<ValidationRule> findAllPricingRules(String Prod_Group, String Product_Sector, Integer Client_Id);

}
