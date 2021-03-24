package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Serving_Size;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.dao.Nutrient_Serving_SizeDAO;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;

@Repository
public class Nutrient_Serving_SizeRepository implements Nutrient_Serving_SizeDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Nutrient_Serving_Size> getAllNutrient_Serving_Size(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			
			
			String sql = "\r\n" + 
					"SELECT		PNI.[Nutrient_Info_Id]\r\n" + 
					"			,PSS.[Serving_Size]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Serving_Size_Uom]\r\n" + 
					"FROM		[pm].[PFBT_Nutrient_Info] PNI\r\n" + 
					"JOIN		[pm].[PFBT_Serving_Size] PSS					ON	PSS.[Nutrient_Info_Id] = PNI.[Nutrient_Info_Id]\r\n" + 
					"															AND	PSS.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PSS.[Serving_Size_Uom]\r\n" + 
					"WHERE		PNI.Product_Id = :Product_Id\r\n" + 
					"AND			PNI.Is_Active = 1;";

			List<Nutrient_Serving_Size> lstNutrient_Serving_Size = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Nutrient_Serving_Size.class));

		    return lstNutrient_Serving_Size;
		}
	
	
}

