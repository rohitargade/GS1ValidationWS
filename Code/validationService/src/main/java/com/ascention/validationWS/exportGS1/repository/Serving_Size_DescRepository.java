package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Serving_Size_Desc;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Serving_Size_DescDAO;

@Repository
public class Serving_Size_DescRepository implements Serving_Size_DescDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 

		public List<Serving_Size_Desc> getAllServing_Size_Desc(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PFBT.[Serving_Size_Desc]\r\n" + 
					"FROM		[pm].[Product_Food_Beverage_Tobacco] PFBT\r\n" + 
					"WHERE		PFBT.Product_Id = :Product_Id\r\n" + 
					"AND			PFBT.Is_Active = 1;";

			List<Serving_Size_Desc> lstServing_Size_Desc = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Serving_Size_Desc.class));

		    return lstServing_Size_Desc;
		}
	
	
}

