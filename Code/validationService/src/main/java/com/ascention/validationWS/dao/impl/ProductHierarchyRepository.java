package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.AlcoholPercentage;
import com.ascention.validationWS.beans.ProductHierarchy;
import com.ascention.validationWS.dao.AlcoholPercentageDAO;
import com.ascention.validationWS.dao.ProductHierarchyDAO;


@Repository
public class ProductHierarchyRepository implements ProductHierarchyDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		public List<ProductHierarchy> getAllProductHierachyAttributes(Integer Product_Id,
				String strProduct_Hierarchy_Ids, Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "SELECT		PC.[Product_Id]\r\n" + 
					"			,PH.[Product_Hierarchy_Id]\r\n" + 
					"			,B.[Brand_Name]\r\n" + 
					"			,SB.[Sub_Brand_Name]\r\n" + 
					"			,PC.[Functional_Name]\r\n" + 
					"			,PC.[Short_Desc]\r\n" + 
					"			,PC.[Addl_Prod_Desc]\r\n" + 
					"			,PC.[Full_Desc]\r\n" + 
					"			,isnull(PC.[Is_Service],0) AS [Is_Service]\r\n" + 
					"			,isnull(PC.[Is_Variable_Unit],0) AS [Is_Variable_Unit]\r\n" + 
					"			,isnull(PC.[Is_Non_Physical],0) AS [Is_Non_Physical]\r\n" + 
					"			,isnull(PC.[Batch_Num_Reqd],0) AS [Batch_Num_Reqd]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Order_Uom]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Selling_Uom]\r\n" + 
					"			,PC.[Min_Life_Arrival]\r\n" + 
					"			,PC.[Min_Life_Production]\r\n" + 
					"			,RL067.[Reference_List_Name] AS [Gm_Declaration_Code]		-- UPDATED 04/03\r\n" + 
					"			,PC.[Irradiated_Code]\r\n" + 
					"			,PC.[Gpc_Segment]\r\n" + 
					"			,PC.[Gpc_Family]\r\n" + 
					"			,PC.[Gpc_Class]\r\n" + 
					"			,PC.[Gpc_Brick]\r\n" + 
					"			,PC.[Prod_Group_Code]\r\n" + 
					"			,PC.[Prod_Group_Code_Desc]\r\n" + 
					"			,PC.[Final_Batch_Expiry_Date]\r\n" + 
					"			,PC.[Is_Recalled]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Feature_Code_Ref]\r\n" + 
					"			,PA.[Warning_Copy_Desc]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [Transportation_Type]\r\n" + 
					"--			,RL005.[Reference_List_Name] AS [Dg_Class]					-- Removed 04/03\r\n" + 
					"			,HL001.[Hierarchy_List_Code] AS [Dg_Class]					-- Added 04/03\r\n" + 
					"--			,RL006.[Reference_List_Name] AS [Dg_Subsidiary_Class]		-- Removed 04/03\r\n" + 
					"			,HL003.[Hierarchy_List_Code] AS [Dg_Subsidiary_Class]		-- Added 04/03\r\n" + 
					"			,PA.[Dg_Hazardous_Code]\r\n" + 
					"			,RL007.[Reference_List_Name] AS [Dg_Packing_Group]\r\n" + 
					"			,RL008.[Reference_List_Name] AS [Dg_Regulation_Code]\r\n" + 
					"			,PA.[Dg_Shipping_Name]\r\n" + 
					"			,PA.[Dg_Technical_Name]\r\n" + 
					"			,PA.[Flash_Point_Temp]\r\n" + 
					"			,RL009.[Reference_List_Name] AS [Flash_Point_Temp_Uom]\r\n" + 
					"			,RL010.[Reference_List_Name] AS [Flash_Point_Type]\r\n" + 
					"			,RL011.[Reference_List_Name] AS [Hazardous_Identifier]\r\n" + 
					"			,RL012.[Reference_List_Name] AS [Dangerous_Good]\r\n" + 
					"			,RL013.[Reference_List_Name] AS [Hazardous_Good]\r\n" + 
					"			,PA.[Sds_Issue_Date]\r\n" + 
					"--			,RL014.[Reference_List_Name] AS [Un_Dg_Num]					-- Removed 04/03\r\n" + 
					"			,HL002.[Hierarchy_List_Code] AS [Un_Dg_Num]					-- Added 04/03\r\n" + 
					"			,RL015.[Reference_List_Name] AS [Cds_Mat_Type]\r\n" + 
					"			,PA.[Chem_Regl_Agency]\r\n" + 
					"			,PA.[Chem_Regl_Name]\r\n" + 
					"			,RL016.[Reference_List_Name] AS [Chem_Phys_State]\r\n" + 
					"			,PA.[Consumer_Storage_Instr]\r\n" + 
					"			,PA.[Consumer_Usage_Instr]\r\n" + 
					"			,RL017.[Reference_List_Name] AS [Target_Consumer_Gender]\r\n" + 
					"			,PA.[Prod_Marketing_Msg]\r\n" + 
					"			,PA.[Colour_Code]\r\n" + 
					"			,RL018.[Reference_List_Name] AS [Colour_Code_Provider]\r\n" + 
					"			,PA.[Colour_Desc]\r\n" + 
					"			,PA.[Descriptive_Size]\r\n" + 
					"			,RL019.[Reference_List_Name] AS [Size_Code_List]\r\n" + 
					"			,PA.[Size_Code_List_Value]\r\n" + 
					"			,RL020.[Reference_List_Name] AS [Has_Safety_Warning]\r\n" + 
					"			,RL021.[Reference_List_Name] AS [Import_Classification_Type]\r\n" + 
					"			,PA.[Import_Classification_Value]\r\n" + 
					"			,PA.[Non_Food_Ingredient_Statement]\r\n" + 
					"			,PA.[Labelling_Claims]\r\n" + 
					"			,PA.[Consumer_Recommended_Use]\r\n" + 
					"			,PA.[Suitable_For]\r\n" + 
					"			,PA.[Batteries_Included]\r\n" + 
					"			,RL022.[Reference_List_Name] AS [Target_Consumer_Age_Group_Code]\r\n" + 
					"			,PA.[Custom_Stats_Code]\r\n" + 
					"			,PA.[Label_Desc]\r\n" + 
					"			,PA.[Prod_Keyword]\r\n" + 
					"			,PA.[Is_Repairable]\r\n" + 
					"			,PA.[Ce_Code]\r\n" + 
					"			,RL023.[Reference_List_Name] AS [Ce_Type]\r\n" + 
					"			,RL024.[Reference_List_Name] AS [Alcohol_Strength]\r\n" + 
					"			,RL025.[Reference_List_Name] AS [Champagne_Ind]\r\n" + 
					"			,PL.[Liquor_Age]\r\n" + 
					"			,RL026.[Reference_List_Name] AS [Liquor_Mkt_Segment]\r\n" + 
					"			,PL.[Std_Drinks]\r\n" + 
					"--			,PL.[Alcohol_By_Vol]			TO BE REMOVED\r\n" + 
					"			,PL.[Provenance_Statement]\r\n" + 
					"			,RL027.[Reference_List_Name] AS [Sweetness_Level_Ind]\r\n" + 
					"			,PL.[Vintage]\r\n" + 
					"			,RL028.[Reference_List_Name] AS [Wooded_Code]\r\n" + 
					"			,PFBT.[Ingr_Statement]\r\n" + 
					"			,PFBT.[Open_Prod_Lifespan]\r\n" + 
					"			,PFBT.[Shelf_Life_After_Thaw]\r\n" + 
					"			,PFBT.[Servings_Per_Pack]\r\n" + 
					"			,PFBT.[Gram_Measure_Dec]\r\n" + 
					"			,PFBT.[Total_Energy_Dec]\r\n" + 
					"			,PFBT.[Health_Star_Rating]\r\n" + 
					"			,RL029.[Reference_List_Name] AS [Interpretive_Term_Opt_Nutrient]\r\n" + 
					"			,RL030.[Reference_List_Name] AS [Interpretive_Term_Sat_Fat]\r\n" + 
					"			,RL031.[Reference_List_Name] AS [Interpretive_Term_Sodium]\r\n" + 
					"			,RL032.[Reference_List_Name] AS [Interpretive_Term_Sugar]\r\n" + 
					"			,PFBT.[Optional_Nutrient_Code]\r\n" + 
					"			,PFBT.[Diet_Type_Desc]\r\n" + 
					"			,RL033.[Reference_List_Name] AS [Labelling_Logos]\r\n" + 
					"			,RL034.[Reference_List_Name] AS [Labelling_Country_Origin]\r\n" + 
					"			,RL035.[Reference_List_Name] AS [Labelling_Ingr_Statement]\r\n" + 
					"			,RL036.[Reference_List_Name] AS [Labelling_Packed_Statement]\r\n" + 
					"			,PFBT.[Labelling_Aus_Content_Pct]\r\n" + 
					"			,PFBT.[Labelling_Prod_Name]\r\n" + 
					"			,PFBT.[Labelling_Addtl_Phrase]\r\n" + 
					"			,PFBT.[Labelling_Addtl_Dtls]\r\n" + 
					"			,RL037.[Reference_List_Name] AS [Packaging_Label_Accreditation]\r\n" + 
					"			,RL038.[Reference_List_Name] AS [Prod_Activity_Type]\r\n" + 
					"			,PFBT.[Prod_Activity_Region_Desc]\r\n" + 
					"			,PFBT.[Serving_Size_Desc]\r\n" + 
					"			,RL039.[Reference_List_Name] AS [Prod_Unit_Desc]\r\n" + 
					"			,PH.[Is_Base_Unit]\r\n" + 
					"			,PH.[Is_Consumer_Unit]\r\n" + 
					"			,PH.[Is_Despatch_Unit]\r\n" + 
					"			,PH.[Is_Invoice_Unit]\r\n" + 
					"			,PH.[Is_Orderable_Unit]\r\n" + 
					"			,PH.[Is_Non_Sold_Prod_Returnable]\r\n" + 
					"			,PH.[Base_Unit_Prod_Id]								-- NOT ADDING FK \r\n" + 
					"			,PH.[Base_Unit_Qty]\r\n" + 
					"			,PH.[Child_Unit_Prod_Id]							-- NOT ADDING FK \r\n" + 
					"			,PH.[Child_Unit_Qty]\r\n" + 
					"			,PH.[Child_Layer_Cnt]\r\n" + 
					"			,PH.[Child_Layer_Unit_Depth]\r\n" + 
					"			,PH.[Child_Layer_Unit_Width]\r\n" + 
					"			,PH.[Manufacturer_Id]								-- NOT ADDING FK \r\n" + 
					"			,PH.[Cntry_Origin_Statement]\r\n" + 
					"			,PH.[Prod_Finish_Desc]\r\n" + 
					"			,PH.[Season_Name]\r\n" + 
					"			,PH.[Seasonal_Availability_Start_Date]\r\n" + 
					"			,PH.[Seasonal_Availability_End_Date]\r\n" + 
					"			,PH.[Depth]\r\n" + 
					"			,RL040.[Reference_List_Name] AS [Depth_Uom]\r\n" + 
					"			,PH.[Height]\r\n" + 
					"			,RL041.[Reference_List_Name] AS [Height_Uom]\r\n" + 
					"			,PH.[Width]\r\n" + 
					"			,RL042.[Reference_List_Name] AS [Width_Uom]\r\n" + 
					"			,PH.[Net_Weight]\r\n" + 
					"			,RL043.[Reference_List_Name] AS [Net_Weight_Uom]\r\n" + 
					"			,PH.[Gross_Weight]\r\n" + 
					"			,RL044.[Reference_List_Name] AS [Gross_Weight_Uom]\r\n" + 
					"			,PH.[Drained_Weight]\r\n" + 
					"			,RL045.[Reference_List_Name] AS [Drained_Weight_Uom]\r\n" + 
					"			,PH.[Net_Volume]\r\n" + 
					"			,RL046.[Reference_List_Name] AS [Net_Volume_Uom]\r\n" + 
					"			,RL047.[Reference_List_Name] AS [Trade_Measurement_Method]\r\n" + 
					"			,PH.[Dec_Weight_Vol]\r\n" + 
					"			,RL048.[Reference_List_Name] AS [Dec_Weight_Vol_Uom]\r\n" + 
					"			,PH.[In_Box_Cube_Dim]\r\n" + 
					"			,RL049.[Reference_List_Name] AS [In_Box_Cube_Dim_Uom]\r\n" + 
					"			,PH.[Net_Base_Volume]\r\n" + 
					"			,RL050.[Reference_List_Name] AS [Net_Base_Volume_Uom]\r\n" + 
					"			,PH.[Gross_Volume]\r\n" + 
					"			,RL051.[Reference_List_Name] AS [Gross_Volume_Uom]\r\n" + 
					"			,PH.[Community_Visibility_Date]\r\n" + 
					"			,RL052.[Reference_List_Name] AS [Display_Ready_Packaging]\r\n" + 
					"			,PHA.[Packaging_Marked_Returnable]\r\n" + 
					"			,PHA.[Is_Price_On_Pack]\r\n" + 
					"			,PHA.[Marked_As_Recyclable]\r\n" + 
					"			,RL053.[Reference_List_Name] AS [Warning_On_Package]\r\n" + 
					"			,PHA.[Nesting]\r\n" + 
					"			,PHA.[Packaging_Marked_Recyclable_Scheme]\r\n" + 
					"			,RL054.[Reference_List_Name] AS [Packaging_Type_Code]\r\n" + 
					"			,PHA.[Packaging_Type_Desc]\r\n" + 
					"			,RL055.[Reference_List_Name] AS [Platform_Type]\r\n" + 
					"			,RL056.[Reference_List_Name] AS [Security_Tag_Loc]\r\n" + 
					"			,RL057.[Reference_List_Name] AS [Security_Tag_Type]\r\n" + 
					"			,PHA.[Shipping_Container_Qty_Desc]\r\n" + 
					"			,RL058.[Reference_List_Name] AS [Handling_Instr_Ref]\r\n" + 
					"			,RL059.[Reference_List_Name] AS [Hazard_Pack_Type]\r\n" + 
					"			,RL060.[Reference_List_Name] AS [Hazardous_Packaging_Type]\r\n" + 
					"			,PHA.[Hazardous_Unit_Vol]\r\n" + 
					"			,RL061.[Reference_List_Name] AS [Hazardous_Unit_Vol_Uom]\r\n" + 
					"			,PHA.[Hazardous_Unit_Size]\r\n" + 
					"			,RL062.[Reference_List_Name] AS [Hazardous_Unit_Size_Uom]\r\n" + 
					"			,RL063.[Reference_List_Name] AS [Promotional_Product]\r\n" + 
					"			,RL064.[Reference_List_Name] AS [Price_Comparison_Content_Type]\r\n" + 
					"			,PHA.[Price_Comparison_Measurement]\r\n" + 
					"			,RL065.[Reference_List_Name] AS [Price_Comparison_Measurement_Uom]\r\n" + 
					"			,PHA.[Mfr_Internal_Ref]\r\n" + 
					"			,RL066.[Reference_List_Name] AS [Front_Face_Type]\r\n" + 
					"			,PHA.[RfId_On_Packaging]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"-- Non Repeated Tables\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"JOIN		[pm].[Brand] B									ON	B.Brand_Id = PC.Brand_Id\r\n" + 
					"															AND B.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Sub_Brand] SB								ON	SB.[Sub_Brand_Id] = PC.[Sub_Brand_Id]\r\n" + 
					"															AND SB.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PA.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Liquor] PL						ON	PL.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PL.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Food_Beverage_Tobacco] PFBT		ON	PFBT.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PFBT.Is_Active = 1\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PH.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Additional] PHA			ON	PHA.Product_Hierarchy_Id = PH.Product_Hierarchy_Id\r\n" + 
					"															AND	PHA.Is_Active = 1\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"-- Reference Data Lists\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL001					ON	HL001.[Hierarchy_List_Id] = PA.[Dg_Subsidiary_Class]	-- Added 04/03	\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL002					ON	HL002.[Hierarchy_List_Id] = PA.[Un_Dg_Num]				-- Added 04/03\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL003					ON	HL003.[Hierarchy_List_Id] = PA.[Dg_Secondary_Sub_Div]	-- Added 04/03\r\n" + 
					"--LEFT JOIN	[ref].[Reference_List] RL005					ON	RL005.[Reference_List_Id] = PA.[Dg_Class]				-- Removed 04/03\r\n" + 
					"--LEFT JOIN	[ref].[Reference_List] RL006					ON	RL006.[Reference_List_Id] = PA.[Dg_Subsidiary_Class]	-- Removed 04/03\r\n" + 
					"--LEFT JOIN	[ref].[Reference_List] RL014					ON	RL014.[Reference_List_Id] = PA.[Un_Dg_Num]				-- Removed 04/03\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PC.[Order_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PC.[Selling_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PA.[Feature_Code_Ref]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PA.[Transportation_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL007					ON	RL007.[Reference_List_Id] = PA.[Dg_Packing_Group]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL008					ON	RL008.[Reference_List_Id] = PA.[Dg_Regulation_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL009					ON	RL009.[Reference_List_Id] = PA.[Flash_Point_Temp_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL010					ON	RL010.[Reference_List_Id] = PA.[Flash_Point_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL011					ON	RL011.[Reference_List_Id] = PA.[Hazardous_Identifier]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL012					ON	RL012.[Reference_List_Id] = PA.[Dangerous_Good]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL013					ON	RL013.[Reference_List_Id] = PA.[Hazardous_Good]\r\n" + 
					"\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL015					ON	RL015.[Reference_List_Id] = PA.[Cds_Mat_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL016					ON	RL016.[Reference_List_Id] = PA.[Chem_Phys_State]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL017					ON	RL017.[Reference_List_Id] = PA.[Target_Consumer_Gender]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL018					ON	RL018.[Reference_List_Id] = PA.[Colour_Code_Provider]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL019					ON	RL019.[Reference_List_Id] = PA.[Size_Code_List]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL020					ON	RL020.[Reference_List_Id] = PA.[Has_Safety_Warning]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL021					ON	RL021.[Reference_List_Id] = PA.[Import_Classification_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL022					ON	RL022.[Reference_List_Id] = PA.[Target_Consumer_Age_Group_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL023					ON	RL023.[Reference_List_Id] = PA.[Ce_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL024					ON	RL024.[Reference_List_Id] = PL.[Alcohol_Strength]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL025					ON	RL025.[Reference_List_Id] = PL.[Champagne_Ind]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL026					ON	RL026.[Reference_List_Id] = PL.[Liquor_Mkt_Segment]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL027					ON	RL027.[Reference_List_Id] = PL.[Sweetness_Level_Ind]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL028					ON	RL028.[Reference_List_Id] = PL.[Wooded_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL029					ON	RL029.[Reference_List_Id] = PFBT.[Interpretive_Term_Opt_Nutrient]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL030					ON	RL030.[Reference_List_Id] = PFBT.[Interpretive_Term_Sat_Fat]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL031					ON	RL031.[Reference_List_Id] = PFBT.[Interpretive_Term_Sodium]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL032					ON	RL032.[Reference_List_Id] = PFBT.[Interpretive_Term_Sugar]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL033					ON	RL033.[Reference_List_Id] = PFBT.[Labelling_Logos]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL034					ON	RL034.[Reference_List_Id] = PFBT.[Labelling_Country_Origin]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL035					ON	RL035.[Reference_List_Id] = PFBT.[Labelling_Ingr_Statement]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL036					ON	RL036.[Reference_List_Id] = PFBT.[Labelling_Packed_Statement]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL037					ON	RL037.[Reference_List_Id] = PFBT.[Packaging_Label_Accreditation]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL038					ON	RL038.[Reference_List_Id] = PFBT.[Prod_Activity_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL039					ON	RL039.[Reference_List_Id] = PH.[Prod_Unit_Desc]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL040					ON	RL040.[Reference_List_Id] = PH.[Depth_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL041					ON	RL041.[Reference_List_Id] = PH.[Height_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL042					ON	RL042.[Reference_List_Id] = PH.[Width_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL043					ON	RL043.[Reference_List_Id] = PH.[Net_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL044					ON	RL044.[Reference_List_Id] = PH.[Gross_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL045					ON	RL045.[Reference_List_Id] = PH.[Drained_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL046					ON	RL046.[Reference_List_Id] = PH.[Net_Volume_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL047					ON	RL047.[Reference_List_Id] = PH.[Trade_Measurement_Method]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL048					ON	RL048.[Reference_List_Id] = PH.[Dec_Weight_Vol_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL049					ON	RL049.[Reference_List_Id] = PH.[In_Box_Cube_Dim_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL050					ON	RL050.[Reference_List_Id] = PH.[Net_Base_Volume_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL051					ON	RL051.[Reference_List_Id] = PH.[Gross_Volume_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL052					ON	RL052.[Reference_List_Id] = PHA.[Display_Ready_Packaging]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL053					ON	RL053.[Reference_List_Id] = PHA.[Warning_On_Package]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL054					ON	RL054.[Reference_List_Id] = PHA.[Packaging_Type_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL055					ON	RL055.[Reference_List_Id] = PHA.[Platform_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL056					ON	RL056.[Reference_List_Id] = PHA.[Security_Tag_Loc]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL057					ON	RL057.[Reference_List_Id] = PHA.[Security_Tag_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL058					ON	RL058.[Reference_List_Id] = PHA.[Handling_Instr_Ref]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL059					ON	RL059.[Reference_List_Id] = PHA.[Hazard_Pack_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL060					ON	RL060.[Reference_List_Id] = PHA.[Hazardous_Packaging_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL061					ON	RL061.[Reference_List_Id] = PHA.[Hazardous_Unit_Vol_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL062					ON	RL062.[Reference_List_Id] = PHA.[Hazardous_Unit_Size_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL063					ON	RL063.[Reference_List_Id] = PHA.[Promotional_Product]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL064					ON	RL064.[Reference_List_Id] = PHA.[Price_Comparison_Content_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL065					ON	RL065.[Reference_List_Id] = PHA.[Price_Comparison_Measurement_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL066					ON	RL066.[Reference_List_Id] = PHA.[Front_Face_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL067					ON	RL067.[Reference_List_Id] = PC.[Gm_Declaration_Code]		-- ADDED 04/03\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] IN ("+strProduct_Hierarchy_Ids+")";

			List<ProductHierarchy> lstProductHierarchy = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ProductHierarchy.class));

		    return lstProductHierarchy;
		}
	 
	
	
}

