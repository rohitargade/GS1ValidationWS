package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;
import com.ascention.validationWS.exportGS1.dao.BatteryTypeDAO;
import com.ascention.validationWS.exportGS1.dao.GrowMethodDAO;

@Repository
public class GrowMethodRepository implements GrowMethodDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Grow_Method> getAllGrow_Method(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Grow_Method]\r\n" + 
					"FROM		[pm].[PA_Grow_Method] PGM\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PGM.[Grow_Method]\r\n" + 
					"WHERE		PGM.[Product_Id] = :Product_Id\r\n" + 
					"AND			PGM.[Is_Active] = 1;";

			List<Grow_Method> lstGrowMethod = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Grow_Method.class));

		    return lstGrowMethod;
		}
	
	
}

