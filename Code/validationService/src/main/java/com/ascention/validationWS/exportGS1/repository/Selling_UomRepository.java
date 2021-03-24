package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Selling_Uom;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Selling_UomDAO;

@Repository
public class Selling_UomRepository implements Selling_UomDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 

		public List<Selling_Uom> getAllSelling_Uom(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Selling_Uom]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PC.[Selling_Uom]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;\r\n" + 
					"";

			List<Selling_Uom> lstSelling_Uom = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Selling_Uom.class));

		    return lstSelling_Uom;
		}
	
	
}

