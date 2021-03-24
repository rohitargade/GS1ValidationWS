package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.AVPEnvironmental;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.AVPEnvironmentalDAO;

@Repository
public class AVPEnvironmentalRepository implements AVPEnvironmentalDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<AVPEnvironmental> getAllPackaging(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Environmental_Claim]\r\n" + 
					"FROM		[pm].[PFBT_Environment_Claim] PEC\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PEC.[Environmental_Claim]\r\n" + 
					"WHERE		PEC.[Product_Id] = :Product_Id \r\n" + 
					"AND			PEC.[Is_Active] = 1;";

			List<AVPEnvironmental> lstAVPEnvironmental = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AVPEnvironmental.class));

		    return lstAVPEnvironmental;
		}
	
	
}

