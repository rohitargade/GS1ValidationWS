package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;

@Repository
public class PA_Feature_BenefitRepository implements PA_Feature_BenefitDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<PA_Feature_Benefit> getAllPA_Feature_Benefit(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		*\r\n" + 
					"FROM		[pm].[PA_Feature_Benefit] PFB\r\n" + 
					"WHERE		PFB.[Product_Id] = :Product_Id\r\n" + 
					"AND			PFB.[Is_Active] = 1;";

			List<PA_Feature_Benefit> lstPA_Feature_Benefit = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PA_Feature_Benefit.class));

		    return lstPA_Feature_Benefit;
		}
	
	
}

