package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;

public interface GrowMethodDAO {
	public List<Grow_Method> getAllGrow_Method(Integer Product_Id);

}
