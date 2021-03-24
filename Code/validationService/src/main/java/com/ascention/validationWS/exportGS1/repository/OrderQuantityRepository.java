package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.OrderQuantity;
import com.ascention.validationWS.exportGS1.dao.OrderQuantityDAO;

@Repository
public class OrderQuantityRepository implements OrderQuantityDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;




		public List<OrderQuantity> getAllOrderQuantity(Integer Product_Id, String strHierarchyIds, Integer Product_Export_Hdr_Id, Integer Client_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			parameters.addValue("Client_Id", Client_Id);

			String sql = "-- Ord Qty's - Default WHERE [Recipient_Id] is NULL ELSE CLIENT SPECIFIC\r\n" + 
					"SELECT		POQ.[Recipient_Id]\r\n" + 
					"			,POQ.[Ord_Qty_Min]\r\n" + 
					"			,POQ.[Ord_Qty_Multiple]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Client] PHC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Hierarchy_Id] = PHC.[Product_Hierarchy_Id]		-- ADDED 02/02/21\r\n" + 
					"															AND	PH.[Is_Orderable_Unit] = 1									-- ADDED 02/02/21\r\n" + 
					"LEFT JOIN	[pm].[PHC_Ord_Qty] POQ							ON	POQ.[Product_Hierarchy_Client_Id] = PHC.[Product_Hierarchy_Client_Id]\r\n" + 
					"															AND POQ.[Is_Active] = 1\r\n" + 
					"															AND	(\r\n" + 
					"																POQ.[Recipient_Id] is NULL\r\n" + 
					"																OR\r\n" + 
					"																(POQ.[Recipient_Id] IN (SELECT DISTINCT [Recipient_Id] FROM [pm].[Product_Recipient] WHERE [Product_Export_Hdr_Id] = :Product_Export_Hdr_Id ))\r\n" + 
					"																)\r\n" + 
					"WHERE		PHC.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHC.[Active] = 1\r\n" + 
					"AND			PHC.[Client_Id] = :Client_Id ;		-- ADDED 01/02/21";

			List<OrderQuantity> lstOrderQty = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(OrderQuantity.class));

		    return lstOrderQty;
		}
	
	
}

