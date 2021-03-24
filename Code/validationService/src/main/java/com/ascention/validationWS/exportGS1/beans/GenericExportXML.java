package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GenericExportXML {
	private Integer Channel_Publish_Id;
	private String Is_Active;
	private String Create_Date;
	private String Last_Publish_Date;
	private String File_Type;
	private String Operation;
	private String Table_Name;
	private Integer Record_Id;
	private String Status_Code;

	public Integer getChannel_Publish_Id() {
		return Channel_Publish_Id;
	}

	public void setChannel_Publish_Id(Integer channel_Publish_Id) {
		Channel_Publish_Id = channel_Publish_Id;
	}

	public String getIs_Active() {
		return Is_Active;
	}

	public void setIs_Active(String is_Active) {
		Is_Active = is_Active;
	}

	public String getCreate_Date() {
		return Create_Date;
	}

	public void setCreate_Date(String create_Date) {
		Create_Date = create_Date;
	}

	public String getLast_Publish_Date() {
		return Last_Publish_Date;
	}

	public void setLast_Publish_Date(String last_Publish_Date) {
		Last_Publish_Date = last_Publish_Date;
	}

	public String getFile_Type() {
		return File_Type;
	}

	public void setFile_Type(String file_Type) {
		File_Type = file_Type;
	}

	public String getOperation() {
		return Operation;
	}

	public void setOperation(String operation) {
		Operation = operation;
	}

	public String getTable_Name() {
		return Table_Name;
	}

	public void setTable_Name(String table_Name) {
		Table_Name = table_Name;
	}

	public Integer getRecord_Id() {
		return Record_Id;
	}

	public void setRecord_Id(Integer record_Id) {
		Record_Id = record_Id;
	}

	public String getStatus_Code() {
		return Status_Code;
	}

	public void setStatus_Code(String status_Code) {
		Status_Code = status_Code;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
