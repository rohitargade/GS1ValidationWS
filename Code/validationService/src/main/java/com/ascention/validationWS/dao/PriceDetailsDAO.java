package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.PriceDetails;

public interface PriceDetailsDAO {
	public List<PriceDetails> findAll();

	public PriceDetails getPriceGroupSectorValuesById(Integer Price_Export_Id);
}
