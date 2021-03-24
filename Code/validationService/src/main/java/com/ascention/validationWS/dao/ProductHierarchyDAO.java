package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.ProductHierarchy;

public interface ProductHierarchyDAO {
	public List<ProductHierarchy> getAllProductHierachyAttributes(Integer Product_Id, String strProduct_Hierarchy_Ids, Integer Client_Id, Integer Org_Id);

}
