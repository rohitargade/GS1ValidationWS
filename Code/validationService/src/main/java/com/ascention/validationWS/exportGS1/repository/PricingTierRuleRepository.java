package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingTierRule;
import com.ascention.validationWS.exportGS1.dao.PricingTierRuleDAO;

@Repository
public class PricingTierRuleRepository implements PricingTierRuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	


		public List<PricingTierRule> getAllPricingTierRule(Integer Price_Export) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Export", Price_Export);
			
			
			String sql = "SELECT		PD.[Price_Dtl_Id]\r\n" + 
					"			,PTR.[Tier_Rule_Id]\r\n" + 
					"			,PTR.[Tier_Rule_Seq]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Rule_Operator]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Rule_Range_Qualifier]\r\n" + 
					"			,PTR.[Rule_Min]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Rule_Min_Uom]\r\n" + 
					"			,PTR.[Rule_Max]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [Rule_Max_Uom]\r\n" + 
					"FROM		[sm].[Price_Dtl] PD	\r\n" + 
					"JOIN		[sm].[PR_Tier] PT								ON	PT.[Tier_Id] = PD.[Tier_Id]\r\n" + 
					"															AND	PT.[Is_Active] = 1 \r\n" + 
					"JOIN		[sm].[PR_Tier_Rule]	PTR							ON	PTR.[Tier_Id] = PT.[Tier_Id]\r\n" + 
					"															AND	PTR.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PTR.[Rule_Operator]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PTR.[Rule_Range_Qualifier]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PTR.[Rule_Min_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PTR.[Rule_Max_Uom]\r\n" + 
					"WHERE		PD.[Price_Hdr_Id] = (SELECT	PE.Price_Hdr_Id\r\n" + 
					"FROM		[dm].[Price_Export] PE\r\n" + 
					"WHERE		PE.[Price_Export_Id] = :Price_Export\r\n" + 
					"AND			PE.[Is_Active] = 1)\r\n" + 
					"AND			PD.[Is_Active] = 1;";

			List<PricingTierRule> lstPricingTierRule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingTierRule.class));

		    return lstPricingTierRule;
		}
	
	
}

