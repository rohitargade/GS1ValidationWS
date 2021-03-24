package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PhysiochemicalCharacteristic;
import com.ascention.validationWS.exportGS1.beans.ServingQuantityInformation;
import com.ascention.validationWS.exportGS1.dao.PhysiochemicalCharacteristicDAO;
import com.ascention.validationWS.exportGS1.dao.ServingQuantityInformationDAO;

@Repository
public class PhysiochemicalCharacteristicRepository implements PhysiochemicalCharacteristicDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	
		public List<PhysiochemicalCharacteristic> getAllPhysiochemicalCharacteristic(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Characteristic_Code]\r\n" + 
					"			,PPC.[Characteristic_Value]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Characteristic_Value_Uom]\r\n" + 
					"FROM		[pm].[PFBT_Physiochemical_Characteristic] PPC\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PPC.[Characteristic_Code]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PPC.[Characteristic_Value_Uom]\r\n" + 
					"WHERE		PPC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PPC.[Is_Active] = 1;";

			List<PhysiochemicalCharacteristic> lstPhysiochemicalCharacteristic = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PhysiochemicalCharacteristic.class));

		    return lstPhysiochemicalCharacteristic;
		}
	
	
}

