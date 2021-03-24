package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.ServingQuantityInformation;
import com.ascention.validationWS.exportGS1.dao.ServingQuantityInformationDAO;

@Repository
public class ServingQuantityInformationRepository implements ServingQuantityInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<ServingQuantityInformation> getAllServingQuantityInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PFBT.[Servings_Per_Pack]\r\n" + 
					"FROM		[pm].[Product_Food_Beverage_Tobacco] PFBT\r\n" + 
					"WHERE		PFBT.Product_Id = :Product_Id\r\n" + 
					"AND			PFBT.Is_Active = 1;";

			List<ServingQuantityInformation> lstServingQuantityInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ServingQuantityInformation.class));

		    return lstServingQuantityInformation;
		}
	
	
}

