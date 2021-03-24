package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;

@Repository
public class OrderQtyRepository implements OrderQtyDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<OrderQty> getAllOrderQtyForProduct(Integer Product_Id, Integer Product_Hierarchy_Id,
				Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Product_Hierarchy_Id", Product_Hierarchy_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "SELECT :Product_Id AS [Product_Id]\r\n" + 
					"			,:Product_Hierarchy_Id AS [Product_Hierarchy_Id]\r\n" + 
					"			,POQ.[Recipient_Id]\r\n" + 
					"			,POQ.[Ord_Qty_Min]\r\n" + 
					"			,POQ.[Ord_Qty_Multiple]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Client] PHC			\r\n" + 
					"LEFT JOIN	[pm].[PHC_Ord_Qty] POQ							ON POQ.[Product_Hierarchy_Client_Id] = PHC.[Product_Hierarchy_Client_Id]\r\n" + 
					"															AND POQ.[Is_Active] = 1\r\n" + 
					"\r\n" + 
					"WHERE		PHC.[Product_Hierarchy_Id] = :Product_Hierarchy_Id \r\n" + 
					"AND			PHC.[Active] = 1\r\n" + 
					"AND			PHC.[Client_Id] = :Client_Id;";

			List<OrderQty> lstOrderQty = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(OrderQty.class));

		    return lstOrderQty;
		}
	
	
}

