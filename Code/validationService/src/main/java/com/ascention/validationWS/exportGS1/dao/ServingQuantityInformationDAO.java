package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;
import com.ascention.validationWS.exportGS1.beans.ServingQuantityInformation;

public interface ServingQuantityInformationDAO {
	public List<ServingQuantityInformation> getAllServingQuantityInformation(Integer Product_Id);

}
