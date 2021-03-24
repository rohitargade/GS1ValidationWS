package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProductDetails {
	
	private String Prod_Group;
	private Integer Prod_Group_Id;
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
		public Integer getProd_Group_Id() {
			return Prod_Group_Id;
		}
		public void setProd_Group_Id(Integer prod_Group_Id) {
			Prod_Group_Id = prod_Group_Id;
		}
		 
}
