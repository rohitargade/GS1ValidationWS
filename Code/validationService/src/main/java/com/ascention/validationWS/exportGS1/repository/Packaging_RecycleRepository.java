package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle;
import com.ascention.validationWS.exportGS1.dao.Packaging_RecycleDAO;

@Repository
public class Packaging_RecycleRepository implements Packaging_RecycleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Recycle> getAllPackaging_Recycle(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Packaging_Recycle_Process]\r\n" + 
					"FROM		[pm].[PA_H_Packaging_Recycle_Process] PHPRP\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHPRP.[Packaging_Recycle_Process]\r\n" + 
					"WHERE		PHPRP.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHPRP.[Is_Active] = 1;";

			List<Packaging_Recycle> lstPackaging_Recycle = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Recycle.class));

		    return lstPackaging_Recycle;
		}
	
	
}

