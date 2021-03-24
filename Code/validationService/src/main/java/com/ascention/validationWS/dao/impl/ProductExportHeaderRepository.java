package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.AlcoholPercentage;
import com.ascention.validationWS.beans.ProductExportHeader;
import com.ascention.validationWS.dao.AlcoholPercentageDAO;
import com.ascention.validationWS.dao.ProductExportHeaderDAO;


@Repository
public class ProductExportHeaderRepository implements ProductExportHeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		

		public List<ProductExportHeader> getAllProductExportHeader(Integer Product_Export_Hdr_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
		
			
			String sql = "SELECT		PEH.[Product_Export_Hdr_Id]\r\n" + 
					"				,PEH.[Client_Id]\r\n" + 
					"				,CO.[Organisation_Id] as Org_Id\r\n" + 
					"				,PEH.[Target_Market]\r\n" + 
					"				,PEH.[Product_Id]\r\n" + 
					"				,PED.[Product_Export_Dtl_Id]\r\n" + 
					"				,PED.[Product_Hierarchy_Id]\r\n" + 
					"				,PED.[Country_Origin_Id]\r\n" + 
					"	FROM		[dm].[Product_Export_Hdr] PEH\r\n" + 
					"	JOIN		[dm].[Product_Export_Dtl] PED					ON PED.[Product_Export_Hdr_Id] = PEH.[Product_Export_Hdr_Id]\r\n" + 
					"	JOIN		[cm].[Client_Organisation] CO					ON CO.[Client_Organisation_Id] = PEH.[Client_Id]\r\n" + 
					"	WHERE		PEH.[Product_Export_Hdr_Id] = :Product_Export_Hdr_Id" + 
					"";

			List<ProductExportHeader> lstProductExportHeader = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ProductExportHeader.class));

		    return lstProductExportHeader;
		}
	 
	
	
}

