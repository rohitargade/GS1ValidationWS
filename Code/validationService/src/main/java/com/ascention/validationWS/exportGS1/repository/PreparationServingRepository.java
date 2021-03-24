package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PreparationServing;
import com.ascention.validationWS.exportGS1.beans.ServingQuantityInformation;
import com.ascention.validationWS.exportGS1.dao.PreparationServingDAO;
import com.ascention.validationWS.exportGS1.dao.ServingQuantityInformationDAO;

@Repository
public class PreparationServingRepository implements PreparationServingDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<PreparationServing> getAllPreparationServing(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			String sql = "SELECT		PPT.[Prep_ConsumptionPrecautions]\r\n" + 
					"			,PPT.[Prep_Instr]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Prep_Type]\r\n" + 
					"			,PPT.[Serving_Suggestion]\r\n" + 
					"FROM		[pm].[PFBT_Prep_Type] PPT\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PPT.[Prep_Type]\r\n" + 
					"WHERE		PPT.[Product_Id] = :Product_Id\r\n" + 
					"AND			PPT.[Is_Active] = 1;";

			List<PreparationServing> lstPreparationServing = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PreparationServing.class));

		    return lstPreparationServing;
		}
	
	
}

