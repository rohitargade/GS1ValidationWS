package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyPalletAttributes;

public interface HierarchyPalletAttributesDAO {
	public List<HierarchyPalletAttributes> getAllHierarchyPalletAttributesForProduct(Integer Product_Id, String strHierarchyIds, Integer Client_Id, Integer Org_Id);

}
