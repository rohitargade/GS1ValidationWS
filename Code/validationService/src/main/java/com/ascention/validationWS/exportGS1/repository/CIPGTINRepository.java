package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.CIPGTIN;
import com.ascention.validationWS.exportGS1.dao.CIPGTINDAO;

@Repository
public class CIPGTINRepository implements CIPGTINDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	


		public List<CIPGTIN> getAllCIPGTIN(Integer Product_Id, String strHierarchyIds, String strCOOIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			
			
			String sql = /*"SELECT		PHCO.[Gtin]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PH.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.[Is_Active] = 1\r\n" + 
					"															AND PHCO.[Country_Origin_Id] in ("+strCOOIds+") \r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+");";*/
					"SELECT PHCO.[Gtin]\r\n" + 
					"FROM   [pm].[Product_Hierarchy] PH\r\n" + 
					"JOIN   [pm].[P_H_Country_Origin] PHCO    ON       PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"                                         AND       PHCO.[Is_Active] = 1\r\n" + 
					"                                         AND        PHCO.[Country_Origin_Id] in ("+strCOOIds+")\r\n" + 
					"WHERE  PH.[Product_Id] = :Product_Id\r\n" + 
					"AND    PH.[Is_Active] = 1\r\n" + 
					"AND    PH.[Product_Hierarchy_Id] IN ("+strHierarchyIds+")\r\n" + 
					"AND    PH.Base_Unit_Qty = ( SELECT       MAX([Base_Unit_Qty])\r\n" + 
					"                           FROM       [pm].[Product_Hierarchy]\r\n" + 
					"                           WHERE  [Product_Id] = :Product_Id\r\n" + 
					"                           AND    [Is_Active] = 1\r\n" + 
					"                           AND       [Product_Hierarchy_Id] IN ("+strHierarchyIds+"))\r\n" + 
					"";

			List<CIPGTIN> lstCIPGTIN = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(CIPGTIN.class));

		    return lstCIPGTIN;
		}
	
	
}

