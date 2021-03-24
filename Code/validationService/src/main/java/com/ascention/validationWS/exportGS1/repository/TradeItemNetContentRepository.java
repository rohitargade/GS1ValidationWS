package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemNetContent;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemNetContentDAO;

@Repository
public class TradeItemNetContentRepository implements TradeItemNetContentDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemNetContent> getAllTradeItemNetContent(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
		
			
			String sql = "SELECT		PHNC.[Net_Content]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Net_Content_Uom]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH					\r\n" + 
					"JOIN		[pm].[P_H_Net_Content] PHNC						ON PHNC.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHNC.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHNC.[Net_Content_Uom]\r\n" + 
					"WHERE		PH.[Product_Id] = :Product_Id\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] in ( "+strHierarchyIds+")\r\n" + 
					"AND			PH.[Is_Active] = 1\r\n" + 
					"AND			(PH.[Is_Base_Unit] = 1 OR PH.[Is_Consumer_Unit] = 1) -- ADDED 01/02/2021\r\n" + 
					"UNION\r\n" + 
					"SELECT		PH.[Base_Unit_Qty]\r\n" + 
					"			,'EA' AS [Net_Content_Uom]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH					\r\n" + 
					"WHERE		PH.[Product_Id] = :Product_Id\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+") \r\n" + 
					"AND			PH.[Is_Active] = 1\r\n" + 
					"AND			(PH.[Is_Base_Unit] = 1 OR PH.[Is_Consumer_Unit] = 1) -- ADDED 01/02/2021";
			
			List<TradeItemNetContent> lstTradeItemNetContent = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemNetContent.class));

		    return lstTradeItemNetContent;
		}
	
	
}

