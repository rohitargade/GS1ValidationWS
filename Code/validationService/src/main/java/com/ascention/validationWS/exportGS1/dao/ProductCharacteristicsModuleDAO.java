package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.MicrobiologicalInformation;
import com.ascention.validationWS.exportGS1.beans.ProductCharacteristicsModule;

public interface ProductCharacteristicsModuleDAO {
	public List<ProductCharacteristicsModule> getAllProductCharacteristicsModule(Integer Product_Id);

}
