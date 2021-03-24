package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.beans.AllergenInformationModule;

public interface AllergenInformationModuleDAO {
	public List<AllergenInformationModule> getAllAllergenInformationModule(Integer Product_Id);

}
