package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ConsumerInstructionsModule {
	private String Consumer_Storage_Instr;
	private String Consumer_Usage_Instr;

	public String getConsumer_Storage_Instr() {
		return Consumer_Storage_Instr;
	}

	public void setConsumer_Storage_Instr(String consumer_Storage_Instr) {
		Consumer_Storage_Instr = consumer_Storage_Instr;
	}

	public String getConsumer_Usage_Instr() {
		return Consumer_Usage_Instr;
	}

	public void setConsumer_Usage_Instr(String consumer_Usage_Instr) {
		Consumer_Usage_Instr = consumer_Usage_Instr;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
