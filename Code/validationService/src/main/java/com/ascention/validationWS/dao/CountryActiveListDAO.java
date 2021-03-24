package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.CountryActiveList;

public interface CountryActiveListDAO {
	public List<CountryActiveList> getAllActiveCountriesList(String Country_Origin_Id);

}
