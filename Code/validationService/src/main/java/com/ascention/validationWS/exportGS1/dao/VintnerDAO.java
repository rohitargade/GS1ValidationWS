package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Vintner;

public interface VintnerDAO {
	public List<Vintner> getAllVintnersForProduct(Integer Product_Id, String strHierarchyIds, Integer Client_Id, Integer Org_Id);

}
