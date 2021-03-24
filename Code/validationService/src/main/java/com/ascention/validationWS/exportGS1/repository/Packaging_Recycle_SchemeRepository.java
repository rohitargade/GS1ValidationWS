package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle_Scheme;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_Recycle_SchemeDAO;

@Repository
public class Packaging_Recycle_SchemeRepository implements Packaging_Recycle_SchemeDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Recycle_Scheme> getAllPackaging_Recycle_Scheme(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Packaging_Recycle_Scheme]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Recycle_Scheme] PHPRS\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPRS.[Packaging_Recycle_Scheme]\r\n" + 
					"WHERE		PHPRS.[Product_Hierarchy_Id]  in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPRS.[Is_Active] = 1;";

			List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Recycle_Scheme.class));

		    return lstPackaging_Recycle_Scheme;
		}
	
	
}

