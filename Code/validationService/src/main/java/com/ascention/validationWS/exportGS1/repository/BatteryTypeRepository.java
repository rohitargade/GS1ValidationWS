package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.dao.BatteryTypeDAO;

@Repository
public class BatteryTypeRepository implements BatteryTypeDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Battery_Type> getAllBatteryTypes(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Battery_Type]\r\n" + 
					"FROM		[pm].[PA_Battery_Type] PBT\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PBT.[Battery_Type]\r\n" + 
					"WHERE		PBT.[Product_Id] = :Product_Id\r\n" + 
					"AND			PBT.[Is_Active] = 1";

			List<Battery_Type> lstBatteryType = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Battery_Type.class));

		    return lstBatteryType;
		}
	
	
}

