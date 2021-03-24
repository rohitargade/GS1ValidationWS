package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.GPCAttributes;

public interface GPCAttributesDAO {
	public List<GPCAttributes> getAllGPCAttributesForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
