package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DutyFeeTaxInformationModule;
import com.ascention.validationWS.exportGS1.beans.FarmingAndProcessingInformationModule;
import com.ascention.validationWS.exportGS1.beans.OrderQuantity;
import com.ascention.validationWS.exportGS1.dao.DutyFeeTaxInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.FarmingAndProcessingInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.OrderQuantityDAO;

@Repository
public class FarmingAndProcessingInformationModuleRepository implements FarmingAndProcessingInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		public FarmingAndProcessingInformationModule getAllFarmingAndProcessingInformationModule(
				Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- FarmingAndProcessingInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		RL001.[Reference_List_Name] AS [Gm_Declaration_Code]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Irradiated_Code]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PC.[Gm_Declaration_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PC.[Irradiated_Code]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;\r\n" + 
					"";

			FarmingAndProcessingInformationModule farmingAndProcessingInformationModule = null;
			try {
				farmingAndProcessingInformationModule = (FarmingAndProcessingInformationModule) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(FarmingAndProcessingInformationModule.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return farmingAndProcessingInformationModule;
		}
	
	
}

