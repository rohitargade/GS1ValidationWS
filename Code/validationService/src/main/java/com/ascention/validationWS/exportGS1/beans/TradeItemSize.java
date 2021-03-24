package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemSize {

	private String Descriptive_Size;
	private String Size_Code_List_Value;
	private String Size_Code_List;


	public String getDescriptive_Size() {
		return Descriptive_Size;
	}


	public void setDescriptive_Size(String descriptive_Size) {
		Descriptive_Size = descriptive_Size;
	}


	public String getSize_Code_List_Value() {
		return Size_Code_List_Value;
	}


	public void setSize_Code_List_Value(String size_Code_List_Value) {
		Size_Code_List_Value = size_Code_List_Value;
	}


	public String getSize_Code_List() {
		return Size_Code_List;
	}


	public void setSize_Code_List(String size_Code_List) {
		Size_Code_List = size_Code_List;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

		



}
