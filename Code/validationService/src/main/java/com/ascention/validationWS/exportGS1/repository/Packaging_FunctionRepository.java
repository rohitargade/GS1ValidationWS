package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Packaging_Function;
import com.ascention.validationWS.exportGS1.dao.Packaging_FunctionDAO;

@Repository
public class Packaging_FunctionRepository implements Packaging_FunctionDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Function> getAllPackaging_Function(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Packaging_Function_Code]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Function] PHPF\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPF.[Packaging_Function_Code]\r\n" + 
					"WHERE		PHPF.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPF.[Is_Active] = 1;";

			List<Packaging_Function> lstPackaging = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Function.class));

		    return lstPackaging;
		}
	
	
}

