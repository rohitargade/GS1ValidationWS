package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.MarketingInformationModule;

public interface MarketingInformationModuleDAO {
	public MarketingInformationModule getAllMarketingInformationModule(Integer Product_Id, String strHierarchyIds);

}
