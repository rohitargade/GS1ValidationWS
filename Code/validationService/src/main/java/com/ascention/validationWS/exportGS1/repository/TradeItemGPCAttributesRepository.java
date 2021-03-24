package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemGPCAttributes;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemGPCAttributesDAO;

@Repository
public class TradeItemGPCAttributesRepository implements TradeItemGPCAttributesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemGPCAttributes> getAllTradeItemGPCAttributes(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "-- ARRAY - GPC Attributes\r\n" + 
					"SELECT		PGBA.[Product_Id] \r\n" + 
					"			, "+ strHierarchyIds +" AS [Product_Hierarchy_Id]\r\n" + 
					"			,PGBA.[Gpc_Attr_Type_Code]\r\n" + 
					"			,PGBA.[Gpc_Attr_Value_Code]\r\n" + 
					"FROM		[pm].[P_Gpc_Brick_Attr] PGBA					\r\n" + 
					"WHERE		PGBA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PGBA.[Is_Active] = 1;\r\n" + 
					"";

			List<TradeItemGPCAttributes> lstTradeItemGPCAttributes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemGPCAttributes.class));

		    return lstTradeItemGPCAttributes;
		}
	
	
}

