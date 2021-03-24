package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.ProductRecipientHeader;
import com.ascention.validationWS.dao.ProductRecipientHeaderDAO;


@Repository
public class ProductRecipientHeaderRepository implements ProductRecipientHeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		

		public ProductRecipientHeader getAllProductRecipientHeader(Integer Product_Recipient_Id, Boolean Is_CIHW) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Recipient_Id", Product_Recipient_Id);
		
			String sql = "";
			
			if(!Is_CIHW) {
				sql = "SELECT		PEH.[Product_Export_Hdr_Id]	AS Product_Export_Hdr_Id 					-- :Product_Export_Hdr_Id\r\n" + 
						"			,PEH.[Client_Id] AS Client_Id\r\n" + 
						"			,CO.[Organisation_Id] AS Org_Id\r\n" + 
						"			,PEH.[Target_Market] AS Target_Market\r\n" + 
						"			,PEH.[Product_Id]	 AS Product_Id\r\n" + 	
						"			,string_agg(PED.[Product_Hierarchy_Id],',') AS strHierarchyIds\r\n" + 
						"			,string_agg(PED.[Country_Origin_Id],',') AS strCOOIds \r\n" + 
						"FROM		[pm].[Product_Recipient] PR		\r\n" + 
						"JOIN		[dm].[Product_Export_Hdr] PEH					ON PEH.[Product_Export_Hdr_Id] = PR.[Product_Export_Hdr_Id]\r\n" + 
						"JOIN		[dm].[Product_Export_Dtl] PED					ON PED.[Product_Export_Hdr_Id] = PEH.[Product_Export_Hdr_Id]\r\n" + 
						"JOIN		[cm].[Client_Organisation] CO					ON CO.[Client_Organisation_Id] = PEH.[Client_Id]		\r\n" + 
						"WHERE		PR.[Product_Recipient_Id] = :Product_Recipient_Id\r\n" + 
						"AND			PR.[Is_Active] = 1\r\n" + 
						"-- ONLY DIFFERENCE WITH CIP\r\n" + 
						"AND			(PR.[Hierarchy_Withdrawal] IS NULL OR PR.[Hierarchy_Withdrawal] = 0)\r\n" + 
						"GROUP BY	PEH.[Product_Export_Hdr_Id]\r\n" + 
						"			,PEH.[Client_Id]\r\n" + 
						"			,CO.[Organisation_Id]\r\n" + 
						"			,PEH.[Target_Market]\r\n" + 
						"			,PEH.[Product_Id]	" + 
						"";
			}else {
				sql = "SELECT		PEH.[Product_Export_Hdr_Id]	AS Product_Export_Hdr_Id 					-- :Product_Export_Hdr_Id\r\n" + 
						"			,PEH.[Client_Id] AS Client_Id\r\n" + 
						"			,CO.[Organisation_Id] AS Org_Id\r\n" + 
						"			,PEH.[Target_Market] AS Target_Market\r\n" + 
						"			,PEH.[Product_Id]	 AS Product_Id\r\n" + 	
						"			,string_agg(PED.[Product_Hierarchy_Id],',') AS strHierarchyIds\r\n" + 
						"			,string_agg(PED.[Country_Origin_Id],',') AS strCOOIds \r\n" +  
						"FROM		[pm].[Product_Recipient] PR		\r\n" + 
						"JOIN		[dm].[Product_Export_Hdr] PEH					ON PEH.[Product_Export_Hdr_Id] = PR.[Product_Export_Hdr_Id]\r\n" + 
						"JOIN		[dm].[Product_Export_Dtl] PED					ON PED.[Product_Export_Hdr_Id] = PEH.[Product_Export_Hdr_Id]\r\n" + 
						"JOIN		[cm].[Client_Organisation] CO					ON CO.[Client_Organisation_Id] = PEH.[Client_Id]		\r\n" + 
						"WHERE		PR.[Product_Recipient_Id] = :Product_Recipient_Id\r\n" + 
						"AND			PR.[Is_Active] = 1\r\n" + 
						"-- ONLY DIFFERENCE WITH CIP\r\n" + 
						"AND			PR.[Hierarchy_Withdrawal] = 1\r\n" + 
						"GROUP BY	PEH.[Product_Export_Hdr_Id]\r\n" + 
						"			,PEH.[Client_Id]\r\n" + 
						"			,CO.[Organisation_Id]\r\n" + 
						"			,PEH.[Target_Market]\r\n" + 
						"			,PEH.[Product_Id]	" + 
						"";
			}
			ProductRecipientHeader productRecipientHeader = (ProductRecipientHeader) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(ProductRecipientHeader.class));

		    return productRecipientHeader;
		}
	 
	
	
}

