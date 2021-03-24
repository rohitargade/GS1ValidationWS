package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemTemperatureInformation;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemTemperatureInformationDAO;

@Repository
public class TradeItemTemperatureInformationRepository implements TradeItemTemperatureInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemTemperatureInformation> getAllTradeItemTemperatureInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);


			String sql = "-- ************************************************************************************\r\n" + 
					"-- TradeItemTemperatureInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PPT.[Max_Temp]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Max_Temp_Uom]\r\n" + 
					"			,PPT.[Min_Temp]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Min_Temp_Uom]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Temp_Qualifier_Code]\r\n" + 
					"FROM		[pm].[PA_Product_Temp] PPT\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PPT.[Max_Temp_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PPT.[Min_Temp_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PPT.[Temp_Qualifier_Code]\r\n" + 
					"WHERE		PPT.[Product_Id] = :Product_Id\r\n" + 
					"AND			PPT.[Is_Active] = 1;";

			List<TradeItemTemperatureInformation> lstTradeItemTemperatureInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemTemperatureInformation.class));

		    return lstTradeItemTemperatureInformation;
		}
	
	
}

