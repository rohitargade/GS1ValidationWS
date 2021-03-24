package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyOrderQty;

public interface HierarchyOrderQtyDAO {
	public List<HierarchyOrderQty> getAllHierarchyOrderQtyForProduct(Integer Product_Id, String strHierarchyIds, Integer Client_Id, Integer Org_Id);

}
