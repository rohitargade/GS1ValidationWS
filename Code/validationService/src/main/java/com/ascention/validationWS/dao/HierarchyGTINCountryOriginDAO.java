package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyGTINCountryOrigin;

public interface HierarchyGTINCountryOriginDAO {
	public List<HierarchyGTINCountryOrigin> getAllGTINCountryOriginAttributesForProduct(Integer Product_Id, String strHierarchyIds, String strCOOIds, Integer Client_Id, Integer Org_Id);

}
