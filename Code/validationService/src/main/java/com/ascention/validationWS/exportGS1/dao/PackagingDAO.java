package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;

public interface PackagingDAO {
	public List<Packaging> getAllPackaging(Integer Product_Id, String strHierarchyIds);

}
