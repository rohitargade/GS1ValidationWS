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

import com.ascention.validationWS.beans.ProductDetails;
import com.ascention.validationWS.dao.ProductDetailsDAO;

@Repository
public class ProductRepository implements ProductDetailsDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	    public ProductDetails getProductGroupSectorValuesById(Integer Prod_ID){        
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        parameters.addValue("Product_Id", Prod_ID);
	       
	        
	        String sql = "SELECT RL1.Reference_List_Name as Prod_Group, RL1.Reference_List_Id as Prod_Group_Id " + 
	                ",RL2.Reference_List_Name as Sector " + 
	                "FROM pm.Product_Core PC " + 
	                "JOIN ref.Reference_List RL1 ON RL1.Reference_List_Id = PC.Prod_Group " + 
	                "JOIN ref.Reference_List RL2 ON RL2.Reference_List_Id = PC.Sector " + 
	                "WHERE PC.Product_Id = :Product_Id";
	        		
	        		
	        return (ProductDetails)jdbcTemplate.queryForObject(
	            sql, parameters, new ProductDetailsMapper());  
	    }
	    
	 
	    
		public List<ProductDetails> findAll() {
			 String sql = "SELECT RL1.Reference_List_Name as Prod_Group " + 
		                ",RL2.Reference_List_Name as Sector " + 
		                "FROM pm.Product_Core PC " + 
		                "JOIN ref.Reference_List RL1 ON RL1.Reference_List_Id = PC.Prod_Group " + 
		                "JOIN ref.Reference_List RL2 ON RL2.Reference_List_Id = PC.Sector ";

		        List<ProductDetails> lstProducts = jdbcTemplate.query(
		                sql,
		                new ProductDetailsMapper());

		        return lstProducts;

		}
	
	
}
class ProductDetailsMapper extends BeanPropertyRowMapper<ProductDetails> {

    @Override
	public ProductDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDetails product = new ProductDetails();
		product.setProd_Group(rs.getString("Prod_Group"));
		product.setSector(rs.getString("Sector"));
		product.setProd_Group_Id(rs.getInt("prod_Group_Id"));
		
		return product;
	}

}
