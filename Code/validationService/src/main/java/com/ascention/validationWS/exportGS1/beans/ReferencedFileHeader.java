package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ReferencedFileHeader {
	private String File_Type_Code;
	private String Effective_Start_Date;
	private String File_Format;
	private String Filename;
	private String Url;

	

	public String getFile_Type_Code() {
		return File_Type_Code;
	}



	public void setFile_Type_Code(String file_Type_Code) {
		File_Type_Code = file_Type_Code;
	}



	public String getEffective_Start_Date() {
		return Effective_Start_Date;
	}



	public void setEffective_Start_Date(String effective_Start_Date) {
		Effective_Start_Date = effective_Start_Date;
	}



	public String getFile_Format() {
		return File_Format;
	}



	public void setFile_Format(String file_Format) {
		File_Format = file_Format;
	}



	public String getFilename() {
		return Filename;
	}



	public void setFilename(String filename) {
		Filename = filename;
	}



	public String getUrl() {
		return Url;
	}



	public void setUrl(String url) {
		Url = url;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
