package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.AdditiveInformation;
import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;
import com.ascention.validationWS.exportGS1.dao.AdditiveInformationDAO;
import com.ascention.validationWS.exportGS1.dao.BatteryTypeDAO;
import com.ascention.validationWS.exportGS1.dao.GrowMethodDAO;

@Repository
public class AdditiveInformationRepository implements AdditiveInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<AdditiveInformation> getAllAdditiveInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		CONCAT(	RL001.[Reference_List_Name],';'\r\n" + 
					"					,PA.[Additive_Name],';'\r\n" + 
					"					,PA.[Additive_Num],';'\r\n" + 
					"					,PA.[Additive_Use],';'\r\n" + 
					"					,PA.[Additive_Amnt]) as [AdditiveName]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Containment_Level]\r\n" + 
					"\r\n" + 
					"FROM		[pm].[PFBT_Additive] PA\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Additive_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PA.[Containment_Level]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1;";

			List<AdditiveInformation> lstAdditiveInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AdditiveInformation.class));

		    return lstAdditiveInformation;
		}
	
	
}

