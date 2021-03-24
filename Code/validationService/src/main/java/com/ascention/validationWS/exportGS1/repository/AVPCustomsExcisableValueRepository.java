package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.AVPCustomsExcisableValue;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.AVPCustomsExcisableValueDAO;

@Repository
public class AVPCustomsExcisableValueRepository implements AVPCustomsExcisableValueDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<AVPCustomsExcisableValue> getAllPackaging(Integer Product_Id, String strHierarchyIds,
				String strCOOIds, String Target_Market) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			parameters.addValue("Target_Market", Target_Market);
			
			String sql = "SELECT 		\r\n" + 
					"-- Add More Case Statements for other Duties / UOMS\r\n" + 
					"			CASE\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in MLT AND BEER AND Australian Duty\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14335 AND PC.[Gpc_Brick] ='10000159' AND T.Country = 'AU'\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * ((cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) - (1.15/100)) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1000)) as decimal(8,3))\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in LTR AND BEER AND Australian Duty\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14334 AND PC.[Gpc_Brick] ='10000159' AND T.Country = 'AU'\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * ((cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) - (1.15/100)) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1)) as decimal(8,3))\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in MLT AND BEER\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14335 AND PC.[Gpc_Brick] ='10000159'\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * ((cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) ) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1000)) as decimal(8,3))\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in LTR AND BEER\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14334 AND PC.[Gpc_Brick] ='10000159'\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * ((cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) ) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1)) as decimal(8,3))\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in MLT\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14335								\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * (cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1000)) as decimal(8,3))\r\n" + 
					"-- Value Tax AND $ per Litre of alcohol AND UOM in LTR\r\n" + 
					"				WHEN		T.[Value_Type] = 2517	AND Tax_Rate_Uom = 14429 AND PH.Net_Volume_Uom = 14334								\r\n" + 
					"					THEN	cast((TR.[Tax_Rate] * (cast(PHAPN.[Addl_Prod_Num_Value] as decimal(10,5))/100) * ISNULL(PH1.[Base_Unit_Qty],1) * (PH.[Net_Volume]/1)) as decimal(8,3))\r\n" + 
					"-- \r\n" + 
					"				ELSE NULL\r\n" + 
					"			END AS [customsOrExcisableValue] " + 
					"FROM		[pm].[Product_Core] PC	\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]							-- BASE\r\n" + 
					"															AND PH.[Is_Base_Unit] = 1\r\n" + 
					"															AND PH.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH1					ON	PH1.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND PH1.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"															AND PH1.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Duty] PD								ON	PD.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND PD.[Country] = :Target_Market\r\n" + 
					"															AND PD.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Product_Hierarchy_Id] = PH1.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.[Country_Origin_Id] in ( "+strCOOIds+")\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO1					ON	PHCO1.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]	-- BASE \r\n" + 
					"															AND	PHCO1.[Country_Origin] = PHCO.[Country_Origin]\r\n" + 
					"															AND	PHCO1.[State_Origin] = PHCO.[State_Origin]\r\n" + 
					"JOIN		[pm].[P_H_Additional_Prod_Num] PHAPN			ON	PHAPN.[Country_Origin_Id] = PHCO1.[Country_Origin_Id]\r\n" + 
					"															AND	PHAPN.[Addl_Prod_Num_Type]='14646'\r\n" + 
					"															AND	PHAPN.[Is_Active] = 1\r\n" + 
					"JOIN		[fin].[Tax] T									ON	T.[Tax_Id] = PD.[Tax_Id]\r\n" + 
					"															AND	T.[Tax_Type] = 'Excise'\r\n" + 
					"															AND	T.[Value_Type] = 2517										-- VALUE TAX\r\n" + 
					"															AND T.[Is_Active] = 1\r\n" + 
					"JOIN		[fin].[Tax_Rate] TR								ON	TR.[Tax_Id] = PD.[Tax_Id]\r\n" + 
					"															AND TR.Tax_Start_Date <= GETDATE()\r\n" + 
					"															AND	(TR.Tax_End_Date IS NULL\r\n" + 
					"															OR TR.Tax_End_Date >= GETDATE())\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1";

			List<AVPCustomsExcisableValue> lstAVPCustomsExcisableValue = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AVPCustomsExcisableValue.class));

		    return lstAVPCustomsExcisableValue;
		}
	
	
}

