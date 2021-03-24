package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle;
import com.ascention.validationWS.exportGS1.beans.Packaging_Sustainability;

public interface Packaging_SustainabilityDAO {
	public List<Packaging_Sustainability> getAllPackaging_Sustainability(Integer Product_Id, String strHierarchyIds);

}
