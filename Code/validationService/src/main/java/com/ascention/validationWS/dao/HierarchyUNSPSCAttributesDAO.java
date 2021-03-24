package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyUNSPSCAttributes;

public interface HierarchyUNSPSCAttributesDAO {
	public List<HierarchyUNSPSCAttributes> getAllHierarchyUNSPSCAttributesForProduct(Integer Product_Id, Integer Client_Id, Integer Org_Id);

}
