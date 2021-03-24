package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.PharmaceuticalItemInformationModule;

public interface PharmaceuticalItemInformationModuleDAO {
	public List<PharmaceuticalItemInformationModule> getAllPharmaceuticalItemInformationModule(Integer Product_Id, String strHierarchyIds);

}
