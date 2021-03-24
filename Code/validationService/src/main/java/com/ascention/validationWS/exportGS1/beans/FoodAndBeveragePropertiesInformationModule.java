package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FoodAndBeveragePropertiesInformationModule {

	private List<MicrobiologicalInformation> lstMicrobiologicalInformation;
	private List<PhysiochemicalCharacteristic> lstPhysiochemicalCharacteristic;

	public List<MicrobiologicalInformation> getLstMicrobiologicalInformation() {
		return lstMicrobiologicalInformation;
	}

	public void setLstMicrobiologicalInformation(List<MicrobiologicalInformation> lstMicrobiologicalInformation) {
		this.lstMicrobiologicalInformation = lstMicrobiologicalInformation;
	}

	public List<PhysiochemicalCharacteristic> getLstPhysiochemicalCharacteristic() {
		return lstPhysiochemicalCharacteristic;
	}

	public void setLstPhysiochemicalCharacteristic(List<PhysiochemicalCharacteristic> lstPhysiochemicalCharacteristic) {
		this.lstPhysiochemicalCharacteristic = lstPhysiochemicalCharacteristic;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
