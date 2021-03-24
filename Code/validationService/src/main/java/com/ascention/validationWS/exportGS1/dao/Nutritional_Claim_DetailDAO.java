package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim_Detail;

public interface Nutritional_Claim_DetailDAO {
	public List<Nutritional_Claim_Detail> getAllNutritional_Claim_Detail(Integer Product_Id, String Product_Hierarchy_id);

}
