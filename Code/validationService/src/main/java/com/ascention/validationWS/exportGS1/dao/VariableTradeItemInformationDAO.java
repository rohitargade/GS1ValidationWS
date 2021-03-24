package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.VariableTradeItemInformation;

public interface VariableTradeItemInformationDAO {
	public List<VariableTradeItemInformation> getAllVariableTradeItemInformation(Integer Product_Id);

}
