package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.VariableTradeItemInformation;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.VariableTradeItemInformationDAO;

@Repository
public class VariableTradeItemInformationRepository implements VariableTradeItemInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<VariableTradeItemInformation> getAllVariableTradeItemInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "-- ************************************************************************************\r\n" + 
					"-- VariableTradeItemInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PC.[Is_Variable_Unit]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<VariableTradeItemInformation> lstVariableTradeItemInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(VariableTradeItemInformation.class));

		    return lstVariableTradeItemInformation;
		}
	
	
}

