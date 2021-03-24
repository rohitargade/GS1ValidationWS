package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.SecurityTagInformationModule;

public interface SecurityTagInformationModuleDAO {
	public List<SecurityTagInformationModule> getAllSecurityTagInformationModule(Integer Product_Id, String strHierarchyIds);

}
