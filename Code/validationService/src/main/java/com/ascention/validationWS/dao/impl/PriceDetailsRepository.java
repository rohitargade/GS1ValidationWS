package com.ascention.validationWS.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.PriceDetails;
import com.ascention.validationWS.dao.PriceDetailsDAO;
import com.ascention.validationWS.dao.PriceDetailsDAO;

@Repository
public class PriceDetailsRepository implements PriceDetailsDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
 
	 
	    
		public List<PriceDetails> findAll() {
			 String sql = "SELECT RL1.Reference_List_Name as Prod_Group " + 
		                ",RL2.Reference_List_Name as Sector " + 
		                "FROM pm.Product_Core PC " + 
		                "JOIN ref.Reference_List RL1 ON RL1.Reference_List_Id = PC.Prod_Group " + 
		                "JOIN ref.Reference_List RL2 ON RL2.Reference_List_Id = PC.Sector ";

		        List<PriceDetails> lstProducts = jdbcTemplate.query(sql, new PriceDetailsMapper());

		        return lstProducts;

		}



		@Override
		public PriceDetails getPriceGroupSectorValuesById(Integer Price_Export_Id) {
			 MapSqlParameterSource parameters = new MapSqlParameterSource();
		        parameters.addValue("Price_Export_Id", Price_Export_Id);
		       
		        
		        String sql = "SELECT	 PC.[Sector]\r\n" + 
		        		"			,PC.[Prod_Group]\r\n" + 
		        		"FROM		[dm].[Price_Export] PE\r\n" + 
		        		"JOIN		[sm].[Price_Hdr] PH			ON	PH.[Price_Hdr_Id] = PE.[Price_Hdr_Id]\r\n" + 
		        		"										AND	PH.[Is_Active] = 1\r\n" + 
		        		"JOIN		[pm].[Product_Core] PC		ON	PC.[Product_Id] = PH.[Product_Id]\r\n" + 
		        		"										AND	PC.[Is_Active] = 1\r\n" + 
		        		"WHERE		PE.[Price_Export_Id] = :Price_Export_Id \r\n" + 
		        		"AND			PE.[Is_Active] = 1;";
		        		
		        		
		        return (PriceDetails)jdbcTemplate.queryForObject(sql, parameters, new PriceDetailsMapper());  
		}
	
	
}
class PriceDetailsMapper extends BeanPropertyRowMapper<PriceDetails> {

    @Override
	public PriceDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		PriceDetails price = new PriceDetails();
		price.setProd_Group(rs.getString("Prod_Group"));
		price.setSector(rs.getString("Sector"));
		 
		return price;
	}

}
