package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.MicrobiologicalInformation;

public interface MicrobiologicalInformationDAO {
	public List<MicrobiologicalInformation> getAllMicrobiologicalInformation(Integer Product_Id);

}
