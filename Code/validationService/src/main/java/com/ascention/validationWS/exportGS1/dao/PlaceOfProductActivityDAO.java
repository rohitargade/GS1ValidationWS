package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PlaceOfProductActivity;

public interface PlaceOfProductActivityDAO {
	public List<PlaceOfProductActivity> getAllPlaceOfProductActivity(Integer Product_Id, String strHierarchyIds, String strCOOIds);

}
