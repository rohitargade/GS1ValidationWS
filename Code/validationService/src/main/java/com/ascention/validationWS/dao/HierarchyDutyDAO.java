package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.HierarchyDuty;

public interface HierarchyDutyDAO {
	public List<HierarchyDuty> getAllHierarchyDutyForProduct(Integer Product_Id, String strHierarchyIds, Integer Client_Id, Integer Org_Id);

}
