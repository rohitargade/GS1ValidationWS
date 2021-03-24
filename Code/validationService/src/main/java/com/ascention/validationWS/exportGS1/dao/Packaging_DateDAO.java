package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Packaging_Date;
import com.ascention.validationWS.exportGS1.beans.Packaging_Marking;
import com.ascention.validationWS.exportGS1.beans.Packaging_Returnable;

public interface Packaging_DateDAO {
	public List<Packaging_Date> getAllPackaging_Date(Integer Product_Id, String strHierarchyIds);

}
