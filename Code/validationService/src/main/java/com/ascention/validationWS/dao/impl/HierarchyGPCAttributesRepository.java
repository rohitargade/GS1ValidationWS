package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.dao.HierarchyGPCAttributesDAO;

@Repository
public class HierarchyGPCAttributesRepository implements HierarchyGPCAttributesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	
		public List<GPCAttributes> getAllGPCAttributesForHierarchy(Integer Product_Id, Integer Client_Id,
				Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Org_Id", Org_Id);
			
			String sql = "-- ARRAY - GPC Attributes\r\n" + 
					"SELECT		PGBA.[Product_Id] \r\n" + 
					"			,PGBA.[Gpc_Attr_Type_Code]\r\n" + 
					"			,PGBA.[Gpc_Attr_Value_Code]\r\n" + 
					"FROM		[pm].[P_Gpc_Brick_Attr] PGBA					\r\n" + 
					"WHERE		PGBA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PGBA.[Is_Active] = 1;";

			List<GPCAttributes> lstGPCAttributes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(GPCAttributes.class));

		    return lstGPCAttributes;
		}
	
	
}

