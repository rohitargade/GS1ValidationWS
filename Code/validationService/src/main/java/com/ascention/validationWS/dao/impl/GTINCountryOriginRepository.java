package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;

@Repository
public class GTINCountryOriginRepository implements GTINCountryOriginDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
		public List<GTINCountryOrigin> getAllGTINCountryOriginAttributesForProduct(Integer Product_Id,
				Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Product_Hierarchy_Id", Product_Hierarchy_Id);
			parameters.addValue("Org_Id", Org_Id);
			
			String sql = "-- ARRAY - GTIN & Country Origin\r\n" + 
					"SELECT		:Product_Id AS [Product_Id]\r\n" + 
					"			,:Product_Hierarchy_Id AS [Product_Hierarchy_Id]\r\n" + 
					"			,PHCO.[Country_Origin]\r\n" + 
					"			,PHCO.[State_Origin]\r\n" + 
					"			,PHCO.[Gtin]\r\n" + 
					"			,PHCO.[Country_Origin_Id]\r\n" + 
					"-- BEGIN ARRAY\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Addl_Prod_Num_Type]\r\n" + 
					"			,PHAPN.[Addl_Prod_Num_Value]\r\n" + 
					"			,PHAPN.[Organisation_Id]\r\n" + 
					"-- END ARRAY\r\n" + 
					"FROM		[pm].[P_H_Country_Origin] PHCO\r\n" + 
					"LEFT JOIN	[pm].[P_H_Additional_Prod_Num] PHAPN			ON	PHAPN.[Country_Origin_Id] = PHCO.[Country_Origin_Id]\r\n" + 
					"															AND	PHAPN.[Is_Active] = 1\r\n" + 
					"															AND (\r\n" + 
					"																	(PHAPN.[Organisation_Id] = :Org_Id		AND PHAPN.[Addl_Prod_Num_Type] = 51)				-- TRADING_PARTNER_ASSIGNED\r\n" + 
					"																	OR\r\n" + 
					"																	(PHAPN.[Addl_Prod_Num_Type] = 14646)\r\n" + 
					"																)\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHAPN.[Addl_Prod_Num_Type]\r\n" + 
					"WHERE		PHCO.[Product_Hierarchy_Id] = :Product_Hierarchy_Id \r\n" + 
					"AND			PHCO.[Is_Active] = 1;";

			List<GTINCountryOrigin> lstGTINCountryOrigin = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(GTINCountryOrigin.class));

		    return lstGTINCountryOrigin;
		}
	
	
}

