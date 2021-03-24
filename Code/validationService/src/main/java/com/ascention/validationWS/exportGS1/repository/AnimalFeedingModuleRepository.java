package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.AnimalFeedingModule;
import com.ascention.validationWS.exportGS1.beans.Animal_Nutrient_Detail;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.AnimalFeedingModuleDAO;

@Repository
public class AnimalFeedingModuleRepository implements AnimalFeedingModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	

		public List<Animal_Nutrient_Detail> getAllAnimal_Nutrient_Detail(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Nutr_Type]\r\n" + 
					"			,PNA.[Nutr_Exact_Pct]\r\n" + 
					"			,PNA.[Nutr_Min_Pct]\r\n" + 
					"			,PNA.[Nutr_Max_Pct]\r\n" + 
					"FROM		[pm].[PFBT_Nutrient_Animal] PNA\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PNA.[Nutr_Type]\r\n" + 
					"WHERE		PNA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PNA.[Is_Active] = 1;";

			List<Animal_Nutrient_Detail> lstAnimalFeedingModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Animal_Nutrient_Detail.class));

		    return lstAnimalFeedingModule;
		}
	
	
}

