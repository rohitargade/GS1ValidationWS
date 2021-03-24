package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.MarketingInformationModule;
import com.ascention.validationWS.exportGS1.dao.MarketingInformationModuleDAO;

@Repository
public class MarketingInformationModuleRepository implements MarketingInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 


		public MarketingInformationModule getAllMarketingInformationModule(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			String sql = "-- ************************************************************************************\r\n" + 
					"-- MarketingInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		RL002.Reference_List_Name AS [Feature_Code_Ref]	\r\n" + 
					"			,PA.[Prod_Marketing_Msg]\r\n" + 
					"			,RL003.Reference_List_Name AS [Target_Consumer_Age_Group_Code]\r\n" + 
					"			,RL001.Reference_List_Name AS [Target_Consumer_Gender]\r\n" + 
					"			,PH.[Season_Name]\r\n" + 
					"			,PH.[Seasonal_Availability_Start_Date]\r\n" + 
					"			,PH.[Seasonal_Availability_End_Date]\r\n" + 
					"\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PA.[Product_Id]\r\n" + 
					"															AND 	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Target_Consumer_Gender]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PA.[Feature_Code_Ref]	\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PA.[Target_Consumer_Age_Group_Code]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id \r\n" + 
					"AND			PA.[Is_Active] = 1;";

			MarketingInformationModule marketingInformationModule = null;
			try {
				marketingInformationModule = (MarketingInformationModule) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(MarketingInformationModule.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return marketingInformationModule;
		}
	
	
}

