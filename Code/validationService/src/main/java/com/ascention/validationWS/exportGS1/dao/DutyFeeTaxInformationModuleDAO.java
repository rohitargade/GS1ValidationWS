package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.DutyFeeTaxInformationModule;

public interface DutyFeeTaxInformationModuleDAO {
	public List<DutyFeeTaxInformationModule> getAllDutyFeeTaxInformationModule(Integer Product_Id,  String Target_Market);

}
