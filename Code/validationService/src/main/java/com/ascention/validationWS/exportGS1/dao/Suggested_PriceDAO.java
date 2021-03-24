package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Selling_Price_Comparison;
import com.ascention.validationWS.exportGS1.beans.Selling_Uom;
import com.ascention.validationWS.exportGS1.beans.Suggested_Price;

public interface Suggested_PriceDAO {
	public List<Suggested_Price> getAllSuggested_Price(Integer Product_Id, String strHierarchyIds, Integer Client_Id, Integer Product_Export_Hdr_Id);

}
