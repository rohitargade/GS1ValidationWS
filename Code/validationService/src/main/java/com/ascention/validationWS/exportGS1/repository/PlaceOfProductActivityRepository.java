package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.PlaceOfProductActivity;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.PlaceOfProductActivityDAO;

@Repository
public class PlaceOfProductActivityRepository implements PlaceOfProductActivityDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<PlaceOfProductActivity> getAllPlaceOfProductActivity(Integer Product_Id, String strHierarchyIds,
				String strCOOIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PH.[Cntry_Origin_Statement]\r\n" + 
					"			,C.[Numeric_Id]\r\n" + 
					"			,CASE\r\n" + 
					"				WHEN PHCO.[State_Origin] LIKE '%-All' THEN NULL\r\n" + 
					"				ELSE PHCO.[State_Origin]\r\n" + 
					"			END as [State_Origin]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.[Country_Origin_Id] in ( "+strCOOIds+")\r\n" + 
					"JOIN		[ref].[Country] C								ON	C.[Alpha_2] = PHCO.[Country_Origin]\r\n" + 
					"\r\n" + 
					"WHERE		PH.[Product_Hierarchy_Id] in ( "+strHierarchyIds+")\r\n" + 
					"AND			PH.[Is_Active] = 1;";

			List<PlaceOfProductActivity> lstPlaceOfProductActivity = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PlaceOfProductActivity.class));

		    return lstPlaceOfProductActivity;
		}
	
	
}

