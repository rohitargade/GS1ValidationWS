package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Information;
import com.ascention.validationWS.exportGS1.dao.Nutrient_InformationDAO;

@Repository
public class Nutrient_InformationRepository implements Nutrient_InformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Nutrient_Information> getAllNutrient_Information(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		PNI.[Nutrient_Info_Id]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Nutrient_Type]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Nutrient_Qty_Measurement_Precision]\r\n" + 
					"			,PN.[Qty_Contained]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Qty_Contained_Uom]\r\n" + 
					"			,PN.[Daily_Value_Intake_Pct]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [DVIP_Measurement_Precision]\r\n" + 
					"FROM		[pm].[PFBT_Nutrient_Info] PNI\r\n" + 
					"JOIN		[pm].[PFBT_Nutrient] PN							ON	PN.[Nutrient_Info_Id] = PNI.[Nutrient_Info_Id]\r\n" + 
					"															AND	PN.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PN.[Nutrient_Type]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PN.[Nutrient_Qty_Measurement_Precision]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PN.[Qty_Contained_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PN.[DVIP_Measurement_Precision]\r\n" + 
					"WHERE		PNI.Product_Id = :Product_Id\r\n" + 
					"AND			PNI.Is_Active = 1;";

			List<Nutrient_Information> lstNutrient_Information = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Nutrient_Information.class));

		    return lstNutrient_Information;
		}
	
	
}

