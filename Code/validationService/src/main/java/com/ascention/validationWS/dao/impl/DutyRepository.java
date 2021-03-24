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
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;

@Repository
public class DutyRepository implements DutyDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	
		public List<Duty> getAllDutyForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id,
				Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Product_Hierarchy_Id", Product_Hierarchy_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "-- ARRAY - Duty\r\n" + 
					"SELECT		:Product_Id AS [Product_Id]\r\n" + 
					"			,[Product_Hierarchy_Id]\r\n" + 
					"			,T.[Country]\r\n" + 
					"			,CASE\r\n" + 
					"				WHEN T.Tax_Type = 'Excise' AND T.[Tax_Type_Desc] = 'Wine Equalisation Tax' THEN 'WET'\r\n" + 
					"				ELSE T.Tax_Type\r\n" + 
					"			END AS [Tax_Type]\r\n" + 
					"			,T.[Tax_Type_Desc]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Value_Type]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Tax_Agency_Code]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Exempt_Party_Code]\r\n" + 
					"			,PHD.[Country]\r\n" + 
					"			,TR.[Tax_Rate]\r\n" + 
					"FROM		[pm].[P_H_Duty] PHD\r\n" + 
					"JOIN		[fin].[Tax] T									ON	T.[Tax_Id] = PHD.Tax_Id\r\n" + 
					"JOIN		[fin].[Tax_Rate] TR								ON	TR.[Tax_Id] = T.Tax_Id\r\n" + 
					"															AND	TR.Is_Active = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHD.[Exempt_Party_Code]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = T.[Tax_Agency_Code]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = T.[Value_Type]\r\n" + 
					"WHERE		PHD.[Product_Hierarchy_Id] = :Product_Hierarchy_Id \r\n" + 
					"AND			PHD.[Is_Active] = 1";

			List<Duty> lstDuty = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Duty.class));

		    return lstDuty;
		}
	
	
}

