package com.ascention.validationWS.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.ValidationRule;
import com.ascention.validationWS.dao.ValidationRuleDAO;

@Repository
public class ValidationRulesRepository implements ValidationRuleDAO {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<ValidationRule> findAll(String Prod_Group, String Product_Sector, Integer Client_Id, Integer Validation_Domain_Id, Integer Hierarchy_Rule) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("Prod_Group", Prod_Group);
		parameters.addValue("Client_Id", Client_Id);
		parameters.addValue("Validation_Domain_Id", Validation_Domain_Id);
		parameters.addValue("Product_Sector",Product_Sector);
		parameters.addValue("Hierarchy_Rule", Hierarchy_Rule);
		
		String sql = "Select Validation_Rule_ID,Internal_Rule_Id, 'rule \"' + Internal_Rule_Id + Validation_Code + ' result.setOutput(\"{FAIL: '+ Internal_Rule_Id + '}\",'+cast(Validation_Rule_ID as varchar(10))+'); end' as Validation_Code\r\n" + 
				"from ref.Validation_Rule \r\n" + 
				"where (client_id = :Client_Id or client_id is null) AND Is_Active = 1 AND Validation_Domain= :Validation_Domain_Id AND Validation_Code!='NULL' and Validation_Rule_Id in (\r\n" + 
				"SELECT	DISTINCT VR.Validation_Rule_ID\r\n" + 
				"FROM	ref.Product_Group PG\r\n" + 
				"JOIN	ref.Product_Group_Validation_Rule PGVR ON PGVR.Product_Group_Id = PG.Product_Group_Id\r\n" + 
				"JOIN	ref.Validation_Rule VR on VR.Validation_Rule_ID = PGVR.Validation_Rule_ID\r\n" + 
				"WHERE	PG.Display_Name = :Prod_Group AND VR.Validation_Domain= :Validation_Domain_Id AND VR.Hierarchy_Rule = :Hierarchy_Rule \r\n" + 
				"UNION\r\n" + 
				"-- Default Rules for a Given Product Sector\r\n" + 
				"SELECT	DISTINCT VR.Validation_Rule_ID\r\n" + 
				"FROM	ref.Product_Sector PS\r\n" + 
				"JOIN	ref.Product_Sector_Validation_Channel PSVC ON PSVC.Product_Sector_Id = PS.Product_Sector_Id\r\n" + 
				"JOIN	ref.Validation_Channel VC ON VC.Validation_Channel_Id = PSVC.Validation_Channel_Id\r\n" + 
				"JOIN	ref.Validation_Group VG	ON VG.Validation_Group_Id = VC.Validation_Group_Id\r\n" + 
				"JOIN	ref.Validation_Channel_Validation_Rule VCVR ON VCVR.Validation_Channel_Id = VC.Validation_Channel_Id\r\n" + 
				"JOIN	ref.Validation_Rule VR on VR.Validation_Rule_ID = VCVR.Validation_Rule_ID\r\n" + 
				"WHERE	PS.Display_Name =  :Product_Sector AND VR.Validation_Domain= :Validation_Domain_Id AND VR.Hierarchy_Rule = :Hierarchy_Rule\r\n" + 
				"AND		-- (International Std, Sellable,Draft)) = \r\n" + 
				"		(\r\n" + 
				"		((SELECT	Sector_Validation FROM	ref.Product_Group PG WHERE PG.Display_Name =  :Prod_Group) = 'DEFAULT' AND VC.Validation_Default = 1 )\r\n" + 
				"		OR\r\n" + 
				"		((SELECT	Sector_Validation FROM	ref.Product_Group PG WHERE PG.Display_Name = :Prod_Group ) = 'TRUE'  )\r\n" + 
				"		))";
		
		

		List<ValidationRule> lstProducts = jdbcTemplate.query(sql, parameters, new ValidationRuleMapper());

		return lstProducts;
	}

	public ValidationRule getProductGroupSectorValuesById(String Prod_Group, Integer Client_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ValidationRule> findAllPricingRules(String Prod_Group, String Product_Sector, Integer Client_Id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("Prod_Group", Prod_Group);
		parameters.addValue("Client_Id", Client_Id);
		parameters.addValue("Product_Sector",Product_Sector);
		
		String sql = "SELECT		[Validation_Rule_Id]\r\n" + 
				"			,[Internal_Rule_Id]\r\n" + 
				"			, 'rule \"' + Internal_Rule_Id + Validation_Code + ' result.setOutput(\"{FAIL: '+ Internal_Rule_Id + '}\",'+cast(Validation_Rule_ID as varchar(10))+'); end' as Validation_Code\r\n" + 
				"FROM		ref.[Validation_Rule]\r\n" + 
				"WHERE		([Client_Id] = :Client_Id OR [Client_Id] IS NULL) \r\n" + 
				"AND			[Is_Active] = 1 \r\n" + 
				"AND			[Validation_Domain] = 15569\r\n" + 
				"AND			[Validation_Code] !='NULL'\r\n" + 
				"AND			[Validation_Rule_Id] IN \r\n" + 
				"			(	SELECT		DISTINCT VR.Validation_Rule_ID\r\n" + 
				"				FROM		[ref].[Product_Group] PG\r\n" + 
				"				JOIN		[ref].[Product_Group_Validation_Rule] PGVR	ON PGVR.[Product_Group_Id] = PG.[Product_Group_Id]\r\n" + 
				"				JOIN		[ref].[Validation_Rule] VR					ON VR.[Validation_Rule_Id] = PGVR.[Validation_Rule_Id]\r\n" + 
				"				WHERE		VR.[Validation_Domain] = 15569\r\n" + 
				"				AND			VR.[Hierarchy_Rule] = 0 \r\n" + 
				"				AND			PG.[Product_Group_Id] = :Prod_Group\r\n" + 
				"				UNION\r\n" + 
				"				-- Default Rules for a Given Product Sector\r\n" + 
				"				SELECT		DISTINCT VR.Validation_Rule_ID\r\n" + 
				"				FROM		[ref].[Product_Sector] PS\r\n" + 
				"				JOIN		[ref].[Product_Sector_Validation_Channel] PSVC	ON PSVC.[Product_Sector_Id] = PS.[Product_Sector_Id]\r\n" + 
				"				JOIN		[ref].[Validation_Channel] VC					ON VC.[Validation_Channel_Id] = PSVC.[Validation_Channel_Id]\r\n" + 
				"				JOIN		[ref].[Validation_Group] VG						ON VG.Validation_Group_Id = VC.[Validation_Group_Id]\r\n" + 
				"				JOIN		[ref].[Validation_Channel_Validation_Rule] VCVR	ON VCVR.[Validation_Channel_Id] = VC.[Validation_Channel_Id]\r\n" + 
				"				JOIN		[ref].Validation_Rule VR						ON VR.[Validation_Rule_Id] = VCVR.[Validation_Rule_Id]\r\n" + 
				"				WHERE		VR.[Validation_Domain]= 15569 \r\n" + 
				"				AND			VR.[Hierarchy_Rule] = 0\r\n" + 
				"				AND			PS.[Product_Sector_Id] = :Product_Sector\r\n" + 
				"				AND			(\r\n" + 
				"							((SELECT [Sector_Validation]	FROM [ref].[Product_Group] PG	WHERE PG.[Product_Group_Id] = :Prod_Group ) = 'DEFAULT' AND VC.[Validation_Default] = 1 )\r\n" + 
				"							OR\r\n" + 
				"							((SELECT [Sector_Validation]	FROM [ref].[Product_Group] PG	WHERE PG.[Product_Group_Id] = :Prod_Group ) = 'TRUE'  )\r\n" + 
				"							)\r\n" + 
				"			) ";
		
		

		List<ValidationRule> lstPrices = jdbcTemplate.query(sql, parameters, new ValidationRuleMapper());

		return lstPrices;
	}

}

class ValidationRuleMapper extends BeanPropertyRowMapper<ValidationRule> {

	@Override
	public ValidationRule mapRow(ResultSet rs, int rowNum) throws SQLException {
		ValidationRule rule = new ValidationRule();
		rule.setRule_id(rs.getInt("Validation_Rule_Id"));
		rule.setValidation_code(rs.getString("Validation_Code"));
		rule.setInternal_Rule_Id(rs.getString("Internal_Rule_Id"));
		return rule;
	}

}
