package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Selling_Price_Comparison;

public interface Selling_Price_ComparisonDAO {
	public List<Selling_Price_Comparison> getAllSelling_Price_Comparison(Integer Product_Id, String strHierarchyIds);

}
