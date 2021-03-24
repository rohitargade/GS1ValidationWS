package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DeliveryPurchasingInformationModule;
import com.ascention.validationWS.exportGS1.dao.DeliveryPurchasingInformationModuleDAO;

@Repository
public class DeliveryPurchasingInformationModuleRepository implements DeliveryPurchasingInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public List<DeliveryPurchasingInformationModule> getAllDeliveryPurchasingInformationModule(Integer Product_Id,
				String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- DeliveryPurchasingInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		RL001.[Reference_List_Name] AS [Order_Uom]\r\n" + 
					"			,PH.[Is_Non_Sold_Prod_Returnable]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in("+strHierarchyIds+")\r\n" + 
					"															AND	PH.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PC.[Order_Uom]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<DeliveryPurchasingInformationModule> lstDeliveryPurchasingInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(DeliveryPurchasingInformationModule.class));

		    return lstDeliveryPurchasingInformationModule;
		}
	
	
}

