package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Returnable;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_ReturnableDAO;

@Repository
public class Packaging_ReturnableRepository implements Packaging_ReturnableDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Returnable> getAllPackaging_Returnable(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql ="SELECT		PHA.[Packaging_Marked_Returnable]\r\n" + 
					"			,PHA.[Is_Price_On_Pack]\r\n" + 
					"			,CASE PH.[Is_Base_Unit]						-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PHA.[Marked_As_Recyclable]	-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL								-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Marked_As_Recyclable]				-- UPDATED 02/02/2021\r\n" + 
					"			,PHA.[Packaging_Marked_Recyclable_Scheme]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Additional] PHA\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH			ON	PH.[Product_Hierarchy_Id] = PHA.[Product_Hierarchy_Id] -- UPDATED 02/02/2021\r\n" + 
					"WHERE		PHA.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHA.[Is_Active] = 1;";
					
					
			List<Packaging_Returnable> lstPackaging_Returnable = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Returnable.class));

		    return lstPackaging_Returnable;
		}
	
	
}

