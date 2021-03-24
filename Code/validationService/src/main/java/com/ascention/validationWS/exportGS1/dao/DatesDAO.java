package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Dates;

public interface DatesDAO {
	public List<Dates> getAllDates(String strHierarchyIds, Integer Product_Export_Hdr_Id, Integer Client_Id);

}
