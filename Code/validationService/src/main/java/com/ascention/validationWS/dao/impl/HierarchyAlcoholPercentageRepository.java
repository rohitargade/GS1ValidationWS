package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.HierarchyAlcoholPercentage;
import com.ascention.validationWS.dao.HierarchyAlcoholPercentageDAO;

@Repository
public class HierarchyAlcoholPercentageRepository implements HierarchyAlcoholPercentageDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	
		public List<HierarchyAlcoholPercentage> getAllAlcoholPercentageForProduct(Integer Product_Id, String strCOOIds, Integer Client_Id,
				Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Org_Id", Org_Id);
			
			String sql = "\r\n" + 
					"-- ARRAY - Alcohol Percentage\r\n" + 
					"SELECT		:Product_Id AS [Product_Id]\r\n" + 
					"			,PH.Product_Hierarchy_Id AS [Product_Hierarchy_Id]\r\n" + 
					"-- BEGIN ARRAY\r\n" + 
					"			,PHAPN.[Country_Origin_Id]\r\n" + 
					"			,PHAPN.[Addl_Prod_Num_Value] AS [Alcohol_By_Vol]\r\n" + 
					"-- END ARRAY\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PH.[Is_Base_Unit] = 1\r\n" + 
					"															AND	PH.Is_Active = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.Is_Active = 1\r\n" + 
					"															AND (PHCO.[Country_Origin_Id] IN ("+strCOOIds+"))\r\n" + 
					"LEFT JOIN	[pm].[P_H_Additional_Prod_Num] PHAPN			ON	PHAPN.[Country_Origin_Id] = PHCO.[Country_Origin_Id]\r\n" + 
					"															AND	PHAPN.[Is_Active] = 1\r\n" + 
					"															AND PHAPN.[Addl_Prod_Num_Type] = 14646\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id \r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<HierarchyAlcoholPercentage> lstHierarchyAlcoholPercentage = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(HierarchyAlcoholPercentage.class));

		    return lstHierarchyAlcoholPercentage;
		}
	
	
}

