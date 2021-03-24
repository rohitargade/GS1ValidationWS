package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PriceDetails {
	
	private String Prod_Group;
    private String Sector;
	    
	    
		public String getProd_Group() {
			return Prod_Group;
		}
		public void setProd_Group(String prod_Group) {
			Prod_Group = prod_Group;
		}
		public String getSector() {
			return Sector;
		}
		public void setSector(String sector) {
			Sector = sector;
		}

		@Override
		public String toString() {
		     return ReflectionToStringBuilder.toString(this);
		 }
	 
		 
}
