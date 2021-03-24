package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Vintner;
import com.ascention.validationWS.exportGS1.dao.VintnerDAO;

@Repository
public class VintnerRepository implements VintnerDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<Vintner> getAllVintnersForProduct(Integer Product_Id, String strHierarchyIds, Integer Client_Id,
				Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "SELECT		\r\n" + 
					"-- BEGIN ARRAY - Vintner\r\n" + 
					"			PV.Vintner\r\n" + 
					"-- BEGIN ARRAY - Vintner\r\n" + 
					"FROM		[pm].[Product_Liquor] PL\r\n" + 
					"LEFT JOIN	[pm].[PL_Vintner] PV							ON	PV.[Product_Id] = PL.[Product_Id]\r\n" + 
					"															AND	PV.[Is_Active] = 1\r\n" + 
					"WHERE		PL.[Product_Id] = :Product_Id\r\n" + 
					"AND			PL.[Is_Active] = 1";

			List<Vintner> lstVintner = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Vintner.class));

		    return lstVintner;
		}
	
	
}

