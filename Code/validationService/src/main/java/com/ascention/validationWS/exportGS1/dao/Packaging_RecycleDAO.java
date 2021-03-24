package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle;

public interface Packaging_RecycleDAO {
	public List<Packaging_Recycle> getAllPackaging_Recycle(Integer Product_Id, String strHierarchyIds);

}
