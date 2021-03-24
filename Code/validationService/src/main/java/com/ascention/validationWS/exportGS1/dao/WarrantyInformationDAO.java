package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.WarrantyInformation;

public interface WarrantyInformationDAO {
	public List<WarrantyInformation> getAllWarrantyInformation(Integer Product_Id);

}
