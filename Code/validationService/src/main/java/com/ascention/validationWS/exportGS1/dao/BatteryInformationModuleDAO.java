package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.beans.BatteryInformationModule;

public interface BatteryInformationModuleDAO {
	public BatteryInformationModule getAllBatteryInformationModule(Integer Product_Id);

}
