package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSR;
import com.ascention.validationWS.exportGS1.dao.PricingPSRDAO;

@Repository
public class PricingPSRRepository implements PricingPSRDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<PricingPSR> getAllPricingPSR( Integer Price_Relationship) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Relationship", Price_Relationship);
	
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- PriceSynchronisationDocument.xsd (PSR)\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		O.[Organisation_GLN] AS [contentOwner] -- Sender contentOwner informationProvider\r\n" + 
					"			,C.[Recipient_Ref_Id] AS [Receiver] --  \r\n" + 
					"			,PR.[Relationship_Code]\r\n" + 
					"			,O1.[Organisation_GLN] AS [partyReceivingPrivateData] -- businessLocation\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Currency_Id]\r\n" + 
					"			,COU.[Numeric_Id]\r\n" + 
					"			,PR.[Effective_Start_Date]\r\n" + 
					"			,PR.[Effective_End_Date]\r\n" + 
					"FROM		[sm].[Price_Relationship] PR\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO					ON	CO.[Client_Organisation_Id] = PR.[Client_Id]\r\n" + 
					"															AND CO.[Is_Active] = 1\r\n" + 
					"JOIN		[cm].[Organisation] O							ON	O.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND O.[Organisation_GLN] IS NOT NULL\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO1					ON	CO1.[Client_Organisation_Id] = PR.[Recipient_Id]		-- ADDED 03/02/2012\r\n" + 
					"															AND CO1.[Is_Active] = 1										-- ADDED 03/02/2012\r\n" + 
					"JOIN		[cm].[Organisation] O1							ON	O1.[Organisation_Id] = CO1.[Organisation_Id]			-- AMENDED 03/02/2012			\r\n" + 
					"															AND O1.[Organisation_GLN] IS NOT NULL\r\n" + 
					"JOIN		[dm].[Client_Target_Channel] CTC				ON	CTC.[Client_Target_Channel_Id] = PR.[Target_Channel_Id]\r\n" + 
					"															AND	CTC.[Is_Active] = 1\r\n" + 
					"															AND CTC.[Channel_Id] = 2\r\n" + 
					"JOIN		[dm].[Connection] C								ON	C.[Connection_Id] = CTC.[Connection_Id]\r\n" + 
					"															AND	C.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Country] COU								ON	COU.[Alpha_2] = PR.[Target_Market]\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PR.[Currency_Id]\r\n" + 
					"\r\n" + 
					"WHERE		PR.[Price_Relationship_Id] = :Price_Relationship\r\n" + 
					"AND			PR.[Is_Active] = 1";

			List<PricingPSR> lstPricingPSR = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSR.class));

		    return lstPricingPSR;
		}
	
	
}

