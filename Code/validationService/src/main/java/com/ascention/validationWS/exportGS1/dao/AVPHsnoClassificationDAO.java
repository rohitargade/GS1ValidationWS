package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AVPHsnoClassification;

public interface AVPHsnoClassificationDAO {
	public List<AVPHsnoClassification> getAllPackaging(Integer Product_Id);

}
