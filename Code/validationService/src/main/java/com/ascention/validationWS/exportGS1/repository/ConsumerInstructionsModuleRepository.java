package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.ConsumerInstructionsModule;
import com.ascention.validationWS.exportGS1.dao.ConsumerInstructionsModuleDAO;

@Repository
public class ConsumerInstructionsModuleRepository implements ConsumerInstructionsModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

	

		public List<ConsumerInstructionsModule> getAllConsumerInstructionsModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
	
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- ConsumerInstructionsModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PA.[Consumer_Storage_Instr]\r\n" + 
					"			,PA.[Consumer_Usage_Instr]\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1";

			List<ConsumerInstructionsModule> lstConsumerInstructionsModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ConsumerInstructionsModule.class));

		    return lstConsumerInstructionsModule;
		}
	
	
}

