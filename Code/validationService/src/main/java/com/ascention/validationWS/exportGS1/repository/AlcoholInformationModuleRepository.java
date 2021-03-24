package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.dao.AlcoholInformationModuleDAO;

@Repository
public class AlcoholInformationModuleRepository implements AlcoholInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 



		public AlcoholInformationModule getAllAlcoholInformationModule(Integer Product_Id, String strHierarchyIds, String strCOOIds,
				Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "SELECT	distinct	PL.Vintage as Vintage\r\n" + 
					"			,PHAPN.Addl_Prod_Num_Value as PercentageOfAlcoholByVolume\r\n" + 
					"FROM		[pm].[Product_Liquor] PL\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO1					ON	PHCO1.[Country_Origin_Id] in ( "+strCOOIds+" )\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH1					ON	PH1.[Product_Id] = :Product_Id\r\n" + 
					"															AND PH1.[Is_Base_Unit] = 1\r\n" + 
					"															AND PH1.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO2					ON	PHCO2.[Product_Hierarchy_Id] = PH1.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO2.[Country_Origin] = PHCO1.[Country_Origin]\r\n" + 
					"															AND	PHCO2.[State_Origin] = PHCO1.[State_Origin]\r\n" + 
					"															AND	PHCO2.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Additional_Prod_Num] PHAPN			ON	PHAPN.[Country_Origin_Id] = PHCO2.[Country_Origin_Id]\r\n" + 
					"															AND	PHAPN.[Is_Active] = 1\r\n" + 
					"															AND	PHAPN.[Addl_Prod_Num_Type] = 14646\r\n" + 
					"WHERE		PL.[Product_Id] = :Product_Id\r\n" + 
					"AND			PL.[Is_Active] = 1";

			AlcoholInformationModule alcModule = null;
			try {
				alcModule = (AlcoholInformationModule) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(AlcoholInformationModule.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return alcModule;
		}
	
	
}

