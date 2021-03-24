package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Selling_Price_Comparison;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Selling_Price_ComparisonDAO;

@Repository
public class Selling_Price_ComparisonRepository implements Selling_Price_ComparisonDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Selling_Price_Comparison> getAllSelling_Price_Comparison(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Price_Comparison_Content_Type]\r\n" + 
					"			,PHA.[Price_Comparison_Measurement]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Price_Comparison_Measurement_Uom]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Additional] PHA\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHA.[Price_Comparison_Content_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PHA.[Price_Comparison_Measurement_Uom]\r\n" + 
					"WHERE		PHA.[Product_Hierarchy_Id] in ( "+strHierarchyIds+")\r\n" + 
					"AND			PHA.[Is_Active] = 1";

			List<Selling_Price_Comparison> lstSelling_Price_Comparison = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Selling_Price_Comparison.class));

		    return lstSelling_Price_Comparison;
		}
	
	
}

