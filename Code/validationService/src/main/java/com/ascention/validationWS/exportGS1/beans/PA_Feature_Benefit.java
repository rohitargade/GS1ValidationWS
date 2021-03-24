package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PA_Feature_Benefit {
	private Integer Feature_Benefit_Id;
	private Integer Product_Id;
	private String Is_Active;
	private String Is_Archived;
	private String Create_Date;
	private String Create_By;
	private String Modify_Date;
	private String Modify_By;
	private String Feature_Benefit;


	public Integer getFeature_Benefit_Id() {
		return Feature_Benefit_Id;
	}


	public void setFeature_Benefit_Id(Integer feature_Benefit_Id) {
		Feature_Benefit_Id = feature_Benefit_Id;
	}


	public Integer getProduct_Id() {
		return Product_Id;
	}


	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}


	public String getIs_Active() {
		return Is_Active;
	}


	public void setIs_Active(String is_Active) {
		Is_Active = is_Active;
	}


	public String getIs_Archived() {
		return Is_Archived;
	}


	public void setIs_Archived(String is_Archived) {
		Is_Archived = is_Archived;
	}


	public String getCreate_Date() {
		return Create_Date;
	}


	public void setCreate_Date(String create_Date) {
		Create_Date = create_Date;
	}


	public String getCreate_By() {
		return Create_By;
	}


	public void setCreate_By(String create_By) {
		Create_By = create_By;
	}


	public String getModify_Date() {
		return Modify_Date;
	}


	public void setModify_Date(String modify_Date) {
		Modify_Date = modify_Date;
	}


	public String getModify_By() {
		return Modify_By;
	}


	public void setModify_By(String modify_By) {
		Modify_By = modify_By;
	}


	public String getFeature_Benefit() {
		return Feature_Benefit;
	}


	public void setFeature_Benefit(String feature_Benefit) {
		Feature_Benefit = feature_Benefit;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
