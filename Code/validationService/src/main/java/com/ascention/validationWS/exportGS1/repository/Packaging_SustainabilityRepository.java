package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Sustainability;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_SustainabilityDAO;

@Repository
public class Packaging_SustainabilityRepository implements Packaging_SustainabilityDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Sustainability> getAllPackaging_Sustainability(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Packaging_Sustainability_Feature]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Sustainability_Feature] PHPSF\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPSF.[Packaging_Sustainability_Feature]\r\n" + 
					"WHERE		PHPSF.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPSF.[Is_Active] = 1;";

			List<Packaging_Sustainability> lstPackaging_Sustainability = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Sustainability.class));

		    return lstPackaging_Sustainability;
		}
	
	
}

