package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.beans.ValidationFailure;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.dao.ValidationFailureDAO;

@Repository
public class ValidationFailureRepository implements ValidationFailureDAO  {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	    public int deleteExistingRecords(ValidationFailure vf) {
	    	MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", vf.getProduct_Id());
			parameters.addValue("Product_Hierarchy_Id", vf.getProduct_Hierarchy_Id());
			parameters.addValue("Client_Id", vf.getClient_Id());
			
			String sql = "Delete from [log].[Validation_Failure] where [Product_Id]=:Product_Id and [Product_Hierarchy_Id] =:Product_Hierarchy_Id and Client_Id=:Client_Id";

			int noofRowsAffected = jdbcTemplate.update(sql, parameters);

		    return noofRowsAffected;
	    }
	    
	
		public int insertValidationRuleFailre(ValidationFailure vf) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", vf.getProduct_Id());
			parameters.addValue("Product_Hierarchy_Id", vf.getProduct_Hierarchy_Id());
			parameters.addValue("Client_Id", vf.getClient_Id());
			parameters.addValue("Country_Origin_Id", vf.getCountry_Origin_Id());
			parameters.addValue("Prod_Group", vf.getProd_Group());
			parameters.addValue("Validation_Rule_Id", vf.getValidation_Rule_Id());
			parameters.addValue("Create_Date", vf.getCreate_Date());
			
			Boolean Hierarchy_Validation = true;
			if(vf.getProduct_Hierarchy_Id()!=null) {
				Hierarchy_Validation = false;
			}
			parameters.addValue("Hierarchy_Validation", Hierarchy_Validation);
			
			String sql = "INSERT INTO [log].[Validation_Failure]\r\n" + 
					"           ([Client_Id]\r\n" + 
					"           ,[Product_Id]\r\n" + 
					"           ,[Product_Hierarchy_Id]\r\n" + 
					"           ,[Country_Origin_Id]\r\n" + 
					"           ,[Prod_Group]\r\n" + 
					"           ,[Validation_Rule_Id]\r\n" + 
					"           ,[Create_Date]\r\n" +					 
					"           ,[Hierarchy_Validation])\r\n" + 
					"     VALUES\r\n" + 
					"           (:Client_Id\r\n" + 
					"           ,:Product_Id\r\n" + 
					"           ,:Product_Hierarchy_Id\r\n" + 
					"           ,:Country_Origin_Id\r\n" + 
					"           ,:Prod_Group\r\n" + 
					"           ,:Validation_Rule_Id\r\n" + 
					"           ,:Create_Date\r\n" +  
					"           ,:Hierarchy_Validation)";

			int noofRowsAffected = jdbcTemplate.update(sql, parameters);

		    return noofRowsAffected;
		}
	
	
}

