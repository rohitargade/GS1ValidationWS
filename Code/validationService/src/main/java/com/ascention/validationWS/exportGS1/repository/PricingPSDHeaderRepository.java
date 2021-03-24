package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;
import com.ascention.validationWS.exportGS1.dao.PricingPSDHeaderDAO;

@Repository
public class PricingPSDHeaderRepository implements PricingPSDHeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<PricingPSDHeader> getAllPriceBasisQuantity(Integer Price_Export, Integer Product_Export_Dtl_Id, Integer Recipient_Id, Integer Price_Relationship_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Export", Price_Export);
			parameters.addValue("Product_Export_Dtl_Id", Product_Export_Dtl_Id);
			parameters.addValue("Recipient_Id", Recipient_Id);			
			parameters.addValue("Price_Relationship_Id",Price_Relationship_Id);
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- PriceSynchronisationDocument.xsd (PSD)\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		O.[Organisation_GLN] AS [Sender and Content Owner]\r\n" + 
					"			,C.[Recipient_Ref_Id] AS [Receiver]\r\n" + 
					"			,O1.[Organisation_GLN] AS [partyReceivingPrivateData]\r\n" + 
					"			,PR.[Relationship_Code]\r\n" + 
					"			,PR.[Relationship_Seq_Id]\r\n" + 
					"			,PHCO.[Gtin]\r\n" + 
					"			,COU.[Numeric_Id]\r\n" + 
					"			,PH.[Price_Relationship_Id]\r\n" + 
					"			,CASE	\r\n" + 
					"				WHEN	(	SELECT COUNT(*)	FROM [dm].[Price_Export] PE	WHERE PE.[Product_Export_Dtl_Id] = :Product_Export_Dtl_Id	AND PE.[Recipient_Id] = :Recipient_Id	AND PE.[Price_Export_Id] != :Price_Export AND PE.[Price_Relationship_Id] = :Price_Relationship_Id) = 0\r\n" + 
					"				THEN 'NI'\r\n" + 
					"				ELSE RL005.[Reference_List_Name]\r\n" + 
					"			END	AS [Price_Action]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Dist_Method] \r\n" + 
					"			,PH.[Price_Basis_Qty]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Price_Basis_Qty_Uom]\r\n" + 
					"			,PH.[Effective_Start_Date]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Effective_Start_Date_Context]\r\n" + 
					"			,PH.[Effective_End_Date]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [Effective_End_Date_Context]\r\n" + 
					"FROM		[dm].[Price_Export] PE\r\n" + 
					"JOIN		[sm].[Price_Hdr] PH								ON	PH.[Price_Hdr_Id] = PE.[Price_Hdr_Id]\r\n" + 
					"															AND PH.[Is_Active] = 1\r\n" + 
					"\r\n" + 
					"LEFT JOIN	[sm].[Price_Relationship] PR					ON	PR.[Price_Relationship_Id] = PE.[Price_Relationship_Id]\r\n" + 
					"															AND PR.[Is_Active] = 1\r\n" + 
					"JOIN		[dm].[Product_Export_Dtl] PED					ON	PED.[Product_Export_Dtl_Id] = PE.[Product_Export_Dtl_Id]\r\n" + 
					"															AND PED.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Country_Origin_Id] = PED.[Country_Origin_Id]\r\n" + 
					"															AND PHCO.[Is_Active] = 1\r\n" + 
					"JOIN		[dm].[Product_Export_Hdr] PEH					ON	PEH.[Product_Export_Hdr_Id] =  PED.[Product_Export_Hdr_Id]\r\n" + 
					"															AND PEH.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Country] COU								ON	COU.[Alpha_2] = PEH.[Target_Market]\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO					ON	CO.[Client_Organisation_Id] = PEH.[Client_Id]\r\n" + 
					"															AND CO.[Is_Active] = 1\r\n" + 
					"JOIN		[cm].[Organisation] O							ON	O.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND O.[Organisation_GLN] IS NOT NULL\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO1					ON	CO1.[Client_Organisation_Id] = PR.[Recipient_Id]		-- ADDED 03/02/2012\r\n" + 
					"															AND CO1.[Is_Active] = 1										-- ADDED 03/02/2012\r\n" + 
					"JOIN		[cm].[Organisation] O1							ON	O1.[Organisation_Id] = CO1.[Organisation_Id]			-- AMENDED 03/02/2012	\r\n" + 
					"															AND O1.[Organisation_GLN] IS NOT NULL\r\n" + 
					"JOIN		[dm].[Client_Target_Channel] CTC				ON	CTC.[Client_Target_Channel_Id] = PEH.[Target_Channel_Id]\r\n" + 
					"															AND	CTC.[Is_Active] = 1\r\n" + 
					"															AND CTC.[Channel_Id] = 2\r\n" + 
					"JOIN		[dm].[Connection] C								ON	C.[Connection_Id] = CTC.[Connection_Id]\r\n" + 
					"															AND	C.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PH.[Dist_Method] \r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PH.[Price_Basis_Qty_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PH.[Effective_Start_Date_Context] \r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PH.[Effective_End_Date_Context]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL005					ON	RL005.[Reference_List_Id] = PH.[Price_Action]\r\n" + 
					"WHERE		PE.[Price_Export_Id] = :Price_Export\r\n" + 
					"AND			PE.[Is_Active] = 1;" + 
					"";

			List<PricingPSDHeader> lstPricingPSDHeader = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSDHeader.class));

		    return lstPricingPSDHeader;
		}
	
	
}

