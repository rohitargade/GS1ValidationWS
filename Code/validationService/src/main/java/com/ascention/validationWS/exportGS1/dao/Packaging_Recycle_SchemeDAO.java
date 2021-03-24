package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle_Scheme;

public interface Packaging_Recycle_SchemeDAO {
	public List<Packaging_Recycle_Scheme> getAllPackaging_Recycle_Scheme(Integer Product_Id, String strHierarchyIds);

}
