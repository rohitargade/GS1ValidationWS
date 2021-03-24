package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemUNSPSCAttributes;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemUNSPSCAttributesDAO;

@Repository
public class TradeItemUNSPSCAttributesRepository implements TradeItemUNSPSCAttributesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemUNSPSCAttributes> getAllTradeItemUNSPSCAttributes(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "-- UNSPSC ARRAY\r\n" + 
					"SELECT		[Product_ID]\r\n" + 
					"			,5 AS [additionalTradeItemClassificationSystemCode]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Unspsc_Version]\r\n" + 
					"			,PUH.[Commodity_Id]\r\n" + 
					"			,HU.[Description]\r\n" + 
					"FROM		[pm].[P_Unspsc_Hierarchy] PUH\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PUH.[Unspsc_Version]\r\n" + 
					"JOIN		[ref].[Hierarchy_UNSPSC] HU						ON	HU.[Unspsc_Version] = PUH.[Unspsc_Version]\r\n" + 
					"															AND	HU.[Unspsc_Code] = PUH.[Commodity_Id]\r\n" + 
					"WHERE		PUH.[Product_ID] = :Product_Id\r\n" + 
					"AND			PUH.[Is_Active] = 1";

			List<TradeItemUNSPSCAttributes> lstTradeItemUNSPSCAttributes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemUNSPSCAttributes.class));

		    return lstTradeItemUNSPSCAttributes;
		}
	
	
}

