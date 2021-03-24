package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PreparationServing;

public interface PreparationServingDAO {
	public List<PreparationServing> getAllPreparationServing(Integer Product_Id);

}
