package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.MicrobiologicalInformation;
import com.ascention.validationWS.exportGS1.beans.ServingQuantityInformation;
import com.ascention.validationWS.exportGS1.dao.MicrobiologicalInformationDAO;
import com.ascention.validationWS.exportGS1.dao.ServingQuantityInformationDAO;

@Repository
public class MicrobiologicalInformationRepository implements MicrobiologicalInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	

		public List<MicrobiologicalInformation> getAllMicrobiologicalInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Organism_Code]\r\n" + 
					"			,PM.[Organism_Max_Value]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Organism_Max_Value_Uom]\r\n" + 
					"FROM		[pm].[PFBT_Microbiological] PM\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PM.[Organism_Code]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PM.[Organism_Max_Value_Uom]\r\n" + 
					"WHERE		PM.[Product_Id] = :Product_Id\r\n" + 
					"AND			PM.[Is_Active] = 1;";

			List<MicrobiologicalInformation> lstMicrobiologicalInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(MicrobiologicalInformation.class));

		    return lstMicrobiologicalInformation;
		}
	
	
}

