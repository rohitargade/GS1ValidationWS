package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Function;

public interface Packaging_FunctionDAO {
	public List<Packaging_Function> getAllPackaging_Function(Integer Product_Id, String strHierarchyIds);

}
