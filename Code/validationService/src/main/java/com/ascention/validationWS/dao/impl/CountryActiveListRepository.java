package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.CountryActiveList;
import com.ascention.validationWS.dao.CountryActiveListDAO;

@Repository
public class CountryActiveListRepository implements CountryActiveListDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		@Override
		public List<CountryActiveList> getAllActiveCountriesList(String Country_Origin_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();

			parameters.addValue("Country_Origin_Id", Country_Origin_Id);

			 String sql = "SELECT  [Country_Origin_Id]\r\n" + 
			 		"     ,[Is_Active]\r\n" + 
			 		"      ,[Country_Origin]\r\n" + 
			 		"  FROM [pm].[P_H_Country_Origin]\r\n" + 
			 		"  where Country_Origin_Id in ("+Country_Origin_Id+") and Is_Active =1";

				List<CountryActiveList> lstCountryActiveList = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(CountryActiveList.class));


		        return lstCountryActiveList;
		}
	
	
}

