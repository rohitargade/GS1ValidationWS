package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemChildProduct;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemChildProductDAO;

@Repository
public class TradeItemChildProductRepository implements TradeItemChildProductDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemChildProduct> getAllTradeItemChildProduct(Integer Product_Id, String strHierarchyIds,
				String strCOOIds, String parentGTIN) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "-- Child Product\r\n" + 
					"select		PHCO2.gtin AS [Child_Gtin]\r\n" + 
					"			,PH1.[Child_Unit_Qty]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH1					\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO1					ON PHCO1.[Product_Hierarchy_Id] = PH1.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO1.[Is_Active] = 1\r\n" + 
					"															AND PHCO1.[Country_Origin_Id] in ("+strCOOIds+")\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH2					ON	PH2.[Product_Id] = :Product_Id\r\n" + 
					"															AND PH2.[Product_Hierarchy_Id] = PH1.Child_Unit_Prod_Id\r\n" + 
					"															AND	PH2.[Is_Active] = 1\r\n" + 
					"\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO2					ON PHCO2.[Product_Hierarchy_Id] = PH2.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO2.[Is_Active] = 1\r\n" + 
					"															AND PHCO2.[Country_Origin] = PHCO1.[Country_Origin] \r\n" + 
					"															AND PHCO2.[State_Origin] = PHCO1.[State_Origin]\r\n" + 
					"WHERE		PH1.[Product_Id] = :Product_Id\r\n" + 
					"AND			PH1.[Product_Hierarchy_Id] in ( "+strHierarchyIds+")\r\n" + 
					"AND			PH1.[Is_Active] = 1 \r\n"
					+ "AND PHCO1.gtin = '"+parentGTIN+"'";

			List<TradeItemChildProduct> lstTradeItemChildProduct = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemChildProduct.class));

		    return lstTradeItemChildProduct;
		}
	
	
}

