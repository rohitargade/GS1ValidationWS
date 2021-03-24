package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Import_Classification {
	private String Import_Classification_Type;
	private String Import_Classification_Value;
	private String Provenance_Statement;
	private String Prod_Activity_Type;
	private String Prod_Activity_Region_Desc;


	public String getImport_Classification_Type() {
		return Import_Classification_Type;
	}


	public void setImport_Classification_Type(String import_Classification_Type) {
		Import_Classification_Type = import_Classification_Type;
	}


	public String getImport_Classification_Value() {
		return Import_Classification_Value;
	}


	public void setImport_Classification_Value(String import_Classification_Value) {
		Import_Classification_Value = import_Classification_Value;
	}


	public String getProvenance_Statement() {
		return Provenance_Statement;
	}


	public void setProvenance_Statement(String provenance_Statement) {
		Provenance_Statement = provenance_Statement;
	}


	public String getProd_Activity_Type() {
		return Prod_Activity_Type;
	}


	public void setProd_Activity_Type(String prod_Activity_Type) {
		Prod_Activity_Type = prod_Activity_Type;
	}


	public String getProd_Activity_Region_Desc() {
		return Prod_Activity_Region_Desc;
	}


	public void setProd_Activity_Region_Desc(String prod_Activity_Region_Desc) {
		Prod_Activity_Region_Desc = prod_Activity_Region_Desc;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
