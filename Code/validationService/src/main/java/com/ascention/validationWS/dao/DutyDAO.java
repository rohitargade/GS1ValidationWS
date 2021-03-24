package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.Duty;

public interface DutyDAO {
	public List<Duty> getAllDutyForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
