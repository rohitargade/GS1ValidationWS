package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.beans.BatteryInformationModule;
import com.ascention.validationWS.exportGS1.dao.AlcoholInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.BatteryInformationModuleDAO;

@Repository
public class BatteryInformationModuleRepository implements BatteryInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 


		public BatteryInformationModule getAllBatteryInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- BatteryInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PA.[Batteries_Included]\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1";

			BatteryInformationModule batteryModule = null;
			try {
				batteryModule = (BatteryInformationModule) jdbcTemplate.queryForObject(sql, parameters,
						new BeanPropertyRowMapper(BatteryInformationModule.class));
			} catch (Exception e) {
				// TODO: handle exception
			}

		    return batteryModule;
		}
	
	
}

