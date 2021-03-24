package com.ascention.validationWS.dao.impl;

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
import com.ascention.validationWS.beans.UNSPSCAttributes;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.dao.UNSPSCAttributesDAO;

@Repository
public class UNSPSCAttributesRepository implements UNSPSCAttributesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 

		public List<UNSPSCAttributes> getAllUNSPSCAttributesForProduct(Integer Product_Id, Integer Product_Hierarchy_Id,
				Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Product_Hierarchy_Id", Product_Hierarchy_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "-- UNSPSC ARRAY\r\n" + 
					"SELECT		[Product_ID]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Unspsc_Version]\r\n" + 
					"			,PUH.[Family_Id]\r\n" + 
					"			,PUH.[Segment_Id]\r\n" + 
					"			,PUH.[Class_Id]\r\n" + 
					"			,PUH.[Commodity_Id]\r\n" + 
					"FROM		[pm].[P_Unspsc_Hierarchy] PUH\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PUH.[Unspsc_Version]\r\n" + 
					"WHERE		PUH.[Product_ID] = :Product_Id\r\n" + 
					"AND			PUH.[Is_Active] = 1;";

			List<UNSPSCAttributes> lstUNSPSCAttributes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(UNSPSCAttributes.class));

		    return lstUNSPSCAttributes;
		}
	
	
}

