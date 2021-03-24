package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DeliveryPurchasingInformationModule;
import com.ascention.validationWS.exportGS1.beans.LeadTimes;
import com.ascention.validationWS.exportGS1.dao.DeliveryPurchasingInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.LeadTimesDAO;

@Repository
public class LeadTimesRepository implements LeadTimesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public List<LeadTimes> getAllLeadTimes(Integer Product_Id, Integer Product_Export_Hdr_Id, Integer Client_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "-- Lead Times - Default WHERE [Recipient_Id] is NULL ELSE CLIENT SPECIFIC\r\n" + 
					"SELECT		POLT.[Recipient_Id]\r\n" + 
					"			,POLT.[Ord_Lead_Time]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Ord_Lead_Time_Uom]\r\n" + 
					"FROM		[pm].[Product_Core_Client] PCC\r\n" + 
					"LEFT JOIN	[pm].[PCC_Ord_Lead_Time] POLT					ON	POLT.[Product_Core_Client_Id] = PCC.[Product_Core_Client_Id]\r\n" + 
					"															AND POLT.[Is_Active] = 1\r\n" + 
					"															AND	(\r\n" + 
					"																POLT.[Recipient_Id] is NULL\r\n" + 
					"																OR\r\n" + 
					"																(POLT.[Recipient_Id] IN (SELECT DISTINCT [Recipient_Id] FROM [pm].[Product_Recipient] WHERE [Product_Export_Hdr_Id] = :Product_Export_Hdr_Id ))\r\n" + 
					"																)\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = POLT.[Ord_Lead_Time_Uom]\r\n" + 
					"WHERE		PCC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PCC.[Active] = 1 AND			PCC.[Client_Id] = :Client_Id ;	";

			List<LeadTimes> lstLeadTimes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(LeadTimes.class));

		    return lstLeadTimes;
		}
	
	
}

