package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Packaging_Material;

public interface Packaging_MaterialDAO {
	public List<Packaging_Material> getAllPackaging_Material(Integer Product_Id, String strHierarchyIds);

}
