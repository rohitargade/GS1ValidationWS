package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AVPEnvironmental;

public interface AVPEnvironmentalDAO {
	public List<AVPEnvironmental> getAllPackaging(Integer Product_Id);

}
