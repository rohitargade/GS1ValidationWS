package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemReferencedTradeItem;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemReferencedTradeItemDAO;

@Repository
public class TradeItemReferencedTradeItemRepository implements TradeItemReferencedTradeItemDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemReferencedTradeItem> getAllTradeItemReferencedTradeItem(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
		
			
		   String sql = "-- referenced item\r\n" + 
					"SELECT		RL001.[Reference_List_Name] AS [Reference_Type_Code]\r\n" + 
					"			,PHCO.[Gtin]\r\n" + 
					"FROM		[pm].[P_H_Referenced_Item] PHRI\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Country_Origin_Id] = PHRI.[Country_Origin_Id]\r\n" + 
					"															AND	PHCO.[Is_Active] = 1\r\n" + 
					"															AND PHCO.[Gtin] IS NOT NULL\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHRI.[Reference_Type_Code]\r\n" + 
					"WHERE		PHRI.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHRI.[Is_Active] = 1";
			
			List<TradeItemReferencedTradeItem> lstTradeItemReferencedTradeItem = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemReferencedTradeItem.class));

		    return lstTradeItemReferencedTradeItem;
		}
	
	
}

