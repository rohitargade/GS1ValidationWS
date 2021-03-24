package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;

public interface AlcoholInformationModuleDAO {
	public AlcoholInformationModule getAllAlcoholInformationModule(Integer Product_Id, String strHierarchyIds, String strCOOIds, Integer Client_Id, Integer Org_Id);

}
