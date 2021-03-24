package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Material;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_MaterialDAO;

@Repository
public class Packaging_MaterialRepository implements Packaging_MaterialDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Material> getAllPackaging_Material(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Pack_Mat_Type]\r\n" + 
					"			,CAST(PHPM.[Pack_Mat_Comp_Qty] as decimal(15,3)) AS [Pack_Mat_Comp_Qty]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Pack_Mat_Comp_Qty_Uom]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Material] PHPM\r\n" + 
					"LEFT JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPM.[Pack_Mat_Type]\r\n" + 
					"LEFT JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PHPM.[Pack_Mat_Comp_Qty_Uom]\r\n" + 
					"WHERE		PHPM.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPM.[Is_Active] = 1;";

			List<Packaging_Material> lstPackaging_Material = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Material.class));

		    return lstPackaging_Material;
		}
	
	
}

