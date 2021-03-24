package com.ascention.validationWS.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(
		name="rule_table",
		indexes={
				@Index(
						columnList="channel_name,channel_zone,channel_region,channel_branch,insurer_id,make,model,fuel_type,variant,cubic_capacity,seating_capacity,vehicle_type,policy_type,manufacture_year,effective_date,expiry_date",
						name="unique_index_rule")
		}
		)
@Data
public class RuleTable {

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelZone() {
		return channelZone;
	}

	public void setChannelZone(String channelZone) {
		this.channelZone = channelZone;
	}

	public String getChannelRegion() {
		return channelRegion;
	}

	public void setChannelRegion(String channelRegion) {
		this.channelRegion = channelRegion;
	}

	public String getChannelBranch() {
		return channelBranch;
	}

	public void setChannelBranch(String channelBranch) {
		this.channelBranch = channelBranch;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Integer getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(Integer manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public Integer getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public Integer getCubicCapacity() {
		return cubicCapacity;
	}

	public void setCubicCapacity(Integer cubicCapacity) {
		this.cubicCapacity = cubicCapacity;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(Boolean ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public Integer getMinDiscount() {
		return minDiscount;
	}

	public void setMinDiscount(Integer minDiscount) {
		this.minDiscount = minDiscount;
	}

	public Integer getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(Integer maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rule_id")
	private Integer ruleId;

	@Column(name="channel_name")
	private String channelName;

	@Column(name="channel_zone")
	private String channelZone;

	@Column(name="channel_region")
	private String channelRegion;

	@Column(name="channel_branch")
	private String channelBranch;

	@Column(name="vehicle_type")
	private String vehicleType;

	@Column(name="policy_type")
	private String policyType;

	@Column(name="manufacture_year")
	private Integer manufactureYear=null;

	@Column(name="insurer_id", nullable=false)
	private Integer insurerId=null; 

	@Column
	private String make;

	@Column
	private String model;

	@Column(name="fuel_type")
	private String fuelType; 

	@Column
	private String variant;

	@Column(name="cubic_capacity")
	private Integer cubicCapacity=null; 

	@Column(name="seating_capacity")
	private Integer seatingCapacity=null;

	@Column(name="effective_date")
	private String effectiveDate; 

	@Column(name="expiry_date")
	private String expiryDate;

	@Column(name="rule_status")
	private Boolean ruleStatus; 

	@Column(name="min_discount")
	private Integer minDiscount=null; 

	@Column(name="max_discount")
	private Integer maxDiscount=null;

}