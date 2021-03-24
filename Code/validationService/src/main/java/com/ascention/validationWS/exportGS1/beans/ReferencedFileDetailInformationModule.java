package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ReferencedFileDetailInformationModule {

	private List<ReferencedFileHeader> lstReferencedFileHeader;



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public List<ReferencedFileHeader> getLstReferencedFileHeader() {
		return lstReferencedFileHeader;
	}



	public void setLstReferencedFileHeader(List<ReferencedFileHeader> lstReferencedFileHeader) {
		this.lstReferencedFileHeader = lstReferencedFileHeader;
	}


	



}
