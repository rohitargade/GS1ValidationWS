package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AVPCustomsExcisableValue;

public interface AVPCustomsExcisableValueDAO {
	public List<AVPCustomsExcisableValue> getAllPackaging(Integer Product_Id, String strHierarchyIds, String strCOOIds, String Target_Market);

}
