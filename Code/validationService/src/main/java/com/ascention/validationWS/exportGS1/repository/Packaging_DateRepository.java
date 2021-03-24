package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Date;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_DateDAO;

@Repository
public class Packaging_DateRepository implements Packaging_DateDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Date> getAllPackaging_Date(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Packaging_Date_Type]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Date] PHPD\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPD.[Packaging_Date_Type]\r\n" + 
					"WHERE		PHPD.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPD.[Is_Active] = 1;";

			List<Packaging_Date> lstPackaging_Date = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Date.class));

		    return lstPackaging_Date;
		}
	
	
}

