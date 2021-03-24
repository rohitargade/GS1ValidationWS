package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.GPCAttributes;


public interface HierarchyGPCAttributesDAO {
	public List<GPCAttributes> getAllGPCAttributesForHierarchy(Integer Product_Id,  Integer Client_Id, Integer Org_Id);

}
