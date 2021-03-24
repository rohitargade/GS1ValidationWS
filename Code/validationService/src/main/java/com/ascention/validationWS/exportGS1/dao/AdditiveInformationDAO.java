package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AdditiveInformation;

public interface AdditiveInformationDAO {
	public List<AdditiveInformation> getAllAdditiveInformation(Integer Product_Id);

}
