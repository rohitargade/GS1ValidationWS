package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Suggested_Price;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Suggested_PriceDAO;

@Repository
public class Suggested_PriceRepository implements Suggested_PriceDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Suggested_Price> getAllSuggested_Price(Integer Product_Id, String strHierarchyIds,
				 Integer Client_Id, Integer Product_Export_Hdr_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			parameters.addValue("Client_Id", Client_Id);
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			
			String sql = "SELECT		PSP.[Suggested_Price]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Suggested_Price_Curr]\r\n" + 
					"			,PSP.[Suggested_Price_Basis_Qty]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Suggested_Price_Basis_Qty_Uom]\r\n" + 
					"			,PSP.[Suggested_Price_Start_Date]\r\n" + 
					"			,PSP.[Suggested_Price_End_Date]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Client] PHC		\r\n" + 
					"JOIN		[pm].[PHC_Suggested_Price] PSP					ON	PSP.[Product_Hierarchy_Client_Id] = PHC.[Product_Hierarchy_Client_Id]\r\n" + 
					"															AND	PSP.[Is_Active] = 1\r\n" + 
					"															AND	(\r\n" + 
					"																PSP.[Recipient_Id] is NULL\r\n" + 
					"																OR\r\n" + 
					"																(PSP.[Recipient_Id] IN (SELECT DISTINCT [Recipient_Id] FROM [pm].[Product_Recipient] WHERE [Product_Export_Hdr_Id] = :Product_Export_Hdr_Id ))\r\n" + 
					"																)\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PSP.[Suggested_Price_Curr]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PSP.[Suggested_Price_Basis_Qty_Uom]\r\n" + 
					"WHERE		PHC.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHC.[Active] = 1\r\n" + 
					"AND			PHC.[Client_Id] = :Client_Id;\r\n" + 
					"";

			List<Suggested_Price> lstSuggested_Price = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Suggested_Price.class));

		    return lstSuggested_Price;
		}
	
	
}

