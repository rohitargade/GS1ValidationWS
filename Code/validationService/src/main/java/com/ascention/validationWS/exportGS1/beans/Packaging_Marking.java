package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Marking {
	private String Batch_Num_Reqd;
	private String Warning_Copy_Desc;
	private String Packaging_Label_Accreditation;

	public String getBatch_Num_Reqd() {
		return Batch_Num_Reqd;
	}

	public void setBatch_Num_Reqd(String batch_Num_Reqd) {
		Batch_Num_Reqd = batch_Num_Reqd;
	}

	public String getWarning_Copy_Desc() {
		return Warning_Copy_Desc;
	}

	public void setWarning_Copy_Desc(String warning_Copy_Desc) {
		Warning_Copy_Desc = warning_Copy_Desc;
	}

	public String getPackaging_Label_Accreditation() {
		return Packaging_Label_Accreditation;
	}

	public void setPackaging_Label_Accreditation(String packaging_Label_Accreditation) {
		Packaging_Label_Accreditation = packaging_Label_Accreditation;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
