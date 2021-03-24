package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.TradeItemSize;
import com.ascention.validationWS.exportGS1.dao.TradeItemSizeDAO;

@Repository
public class TradeItemSizeRepository implements TradeItemSizeDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemSize> getAllTradeItemSize(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- TradeItemSizeModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PA.[Descriptive_Size]\r\n" + 
					"			,PA.[Size_Code_List_Value]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Size_Code_List]\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Size_Code_List]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1;";

			List<TradeItemSize> lstTradeItemSize = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemSize.class));

		    return lstTradeItemSize;
		}
	
	
}

