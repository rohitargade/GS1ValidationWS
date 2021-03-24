package com.ascention.validationWS.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ProductAdditionalArray {
	List<ProductAdditional> lstProductAdditional = new ArrayList<ProductAdditional>();
	boolean eligibility = true;
	public boolean isEligibility() {
		return eligibility;
	}

	public void setEligibility(boolean eligibility) {
		this.eligibility = eligibility;
	}

	public List<ProductAdditional> getLstProductAdditional() {
		return lstProductAdditional;
	}

	public void setLstProductAdditional(List<ProductAdditional> lstProductAdditional) {
		this.lstProductAdditional = lstProductAdditional;
	}
	
	@Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        
        Gson gson = new Gson();
        // convert your list to json
        String jsonCartList = gson.toJson(this.getLstProductAdditional());
        buff.append(jsonCartList);
        return buff.toString();
    }
}
