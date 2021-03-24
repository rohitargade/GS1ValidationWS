package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.PackagingDAO;

@Repository
public class PackagingRepository implements PackagingDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging> getAllPackaging(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		PHA.[RfId_On_Packaging]	\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Packaging_Type_Code]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Platform_Type]	\r\n" + 
					"			,PHA.[Shipping_Container_Qty_Desc]\r\n" + 
					"			,PHA.[Packaging_Type_Desc]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Additional] PHA\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHA.[Packaging_Type_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PHA.[Platform_Type]\r\n" + 
					"WHERE		PHA.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHA.[Is_Active] = 1;";

			List<Packaging> lstPackaging = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging.class));

		    return lstPackaging;
		}
	
	
}

