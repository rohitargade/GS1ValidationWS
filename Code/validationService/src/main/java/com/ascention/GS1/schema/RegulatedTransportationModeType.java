//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:21:28 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegulatedTransportationModeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegulatedTransportationModeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isProhibitedForTransportation" type="{urn:gs1:shared:shared_common:xsd:3}NonBinaryLogicEnumerationType" minOccurs="0"/&gt;
 *         &lt;element name="prohibitedForTransportationReason" type="{urn:gs1:shared:shared_common:xsd:3}Description1000Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="shippingRegulationException" type="{urn:gs1:shared:shared_common:xsd:3}Description1000Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="specialRequirementsDescription" type="{urn:gs1:shared:shared_common:xsd:3}Description1000Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="specialPermitOrExemptionIdentification" type="{urn:gs1:shared:shared_common:xsd:3}IdentifierType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="transportationMaximumQuantity" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType" minOccurs="0"/&gt;
 *         &lt;element name="transportationModeCode" type="{urn:gs1:gdsn:transportation_hazardous_classification:xsd:3}HazardousMaterialsTransportationModeCodeType" minOccurs="0"/&gt;
 *         &lt;element name="hazardousInformationHeader" type="{urn:gs1:gdsn:gdsn_common:xsd:3}HazardousInformationHeaderType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulatedTransportationModeType", namespace = "urn:gs1:gdsn:transportation_hazardous_classification:xsd:3", propOrder = {
    "isProhibitedForTransportation",
    "prohibitedForTransportationReason",
    "shippingRegulationException",
    "specialRequirementsDescription",
    "specialPermitOrExemptionIdentification",
    "transportationMaximumQuantity",
    "transportationModeCode",
    "hazardousInformationHeader"
})
public class RegulatedTransportationModeType {

    @XmlSchemaType(name = "string")
    protected NonBinaryLogicEnumerationType isProhibitedForTransportation;
    protected List<Description1000Type> prohibitedForTransportationReason;
    protected List<Description1000Type> shippingRegulationException;
    protected List<Description1000Type> specialRequirementsDescription;
    protected List<IdentifierType> specialPermitOrExemptionIdentification;
    protected MeasurementType transportationMaximumQuantity;
    protected HazardousMaterialsTransportationModeCodeType transportationModeCode;
    protected List<HazardousInformationHeaderType> hazardousInformationHeader;

    /**
     * Gets the value of the isProhibitedForTransportation property.
     * 
     * @return
     *     possible object is
     *     {@link NonBinaryLogicEnumerationType }
     *     
     */
    public NonBinaryLogicEnumerationType getIsProhibitedForTransportation() {
        return isProhibitedForTransportation;
    }

    /**
     * Sets the value of the isProhibitedForTransportation property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonBinaryLogicEnumerationType }
     *     
     */
    public void setIsProhibitedForTransportation(NonBinaryLogicEnumerationType value) {
        this.isProhibitedForTransportation = value;
    }

    /**
     * Gets the value of the prohibitedForTransportationReason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prohibitedForTransportationReason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProhibitedForTransportationReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description1000Type }
     * 
     * 
     */
    public List<Description1000Type> getProhibitedForTransportationReason() {
        if (prohibitedForTransportationReason == null) {
            prohibitedForTransportationReason = new ArrayList<Description1000Type>();
        }
        return this.prohibitedForTransportationReason;
    }

    /**
     * Gets the value of the shippingRegulationException property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shippingRegulationException property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShippingRegulationException().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description1000Type }
     * 
     * 
     */
    public List<Description1000Type> getShippingRegulationException() {
        if (shippingRegulationException == null) {
            shippingRegulationException = new ArrayList<Description1000Type>();
        }
        return this.shippingRegulationException;
    }

    /**
     * Gets the value of the specialRequirementsDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specialRequirementsDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecialRequirementsDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description1000Type }
     * 
     * 
     */
    public List<Description1000Type> getSpecialRequirementsDescription() {
        if (specialRequirementsDescription == null) {
            specialRequirementsDescription = new ArrayList<Description1000Type>();
        }
        return this.specialRequirementsDescription;
    }

    /**
     * Gets the value of the specialPermitOrExemptionIdentification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specialPermitOrExemptionIdentification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecialPermitOrExemptionIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getSpecialPermitOrExemptionIdentification() {
        if (specialPermitOrExemptionIdentification == null) {
            specialPermitOrExemptionIdentification = new ArrayList<IdentifierType>();
        }
        return this.specialPermitOrExemptionIdentification;
    }

    /**
     * Gets the value of the transportationMaximumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getTransportationMaximumQuantity() {
        return transportationMaximumQuantity;
    }

    /**
     * Sets the value of the transportationMaximumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     */
    public void setTransportationMaximumQuantity(MeasurementType value) {
        this.transportationMaximumQuantity = value;
    }

    /**
     * Gets the value of the transportationModeCode property.
     * 
     * @return
     *     possible object is
     *     {@link HazardousMaterialsTransportationModeCodeType }
     *     
     */
    public HazardousMaterialsTransportationModeCodeType getTransportationModeCode() {
        return transportationModeCode;
    }

    /**
     * Sets the value of the transportationModeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link HazardousMaterialsTransportationModeCodeType }
     *     
     */
    public void setTransportationModeCode(HazardousMaterialsTransportationModeCodeType value) {
        this.transportationModeCode = value;
    }

    /**
     * Gets the value of the hazardousInformationHeader property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hazardousInformationHeader property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHazardousInformationHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HazardousInformationHeaderType }
     * 
     * 
     */
    public List<HazardousInformationHeaderType> getHazardousInformationHeader() {
        if (hazardousInformationHeader == null) {
            hazardousInformationHeader = new ArrayList<HazardousInformationHeaderType>();
        }
        return this.hazardousInformationHeader;
    }

}
