package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Selling_Price_Comparison;
import com.ascention.validationWS.exportGS1.beans.Selling_Uom;

public interface Selling_UomDAO {
	public List<Selling_Uom> getAllSelling_Uom(Integer Product_Id);

}
