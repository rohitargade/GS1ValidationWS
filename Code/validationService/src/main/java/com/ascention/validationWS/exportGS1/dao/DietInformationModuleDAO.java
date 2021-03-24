package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.DietInformationModule;

public interface DietInformationModuleDAO {
	public List<DietInformationModule> getAllDietInformationModule(Integer Product_Id);

}
