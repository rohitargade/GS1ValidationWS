package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Battery_Type;

public interface BatteryTypeDAO {
	public List<Battery_Type> getAllBatteryTypes(Integer Product_Id);

}
