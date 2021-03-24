package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.dao.Nutritional_ClaimDAO;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;

@Repository
public class Nutritional_ClaimRepository implements Nutritional_ClaimDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 


		public List<Nutritional_Claim> getAllNutritional_Claim(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		PNC.[Nutritional_Claim]\r\n" + 
					"FROM		[pm].[PFBT_Nutritional_Claim] PNC\r\n" + 
					"WHERE		PNC.Product_Id = :Product_Id\r\n" + 
					"AND			PNC.Is_Active = 1;";

			List<Nutritional_Claim> lstNutritional_Claim = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Nutritional_Claim.class));

		    return lstNutritional_Claim;
		}
	
	
}

