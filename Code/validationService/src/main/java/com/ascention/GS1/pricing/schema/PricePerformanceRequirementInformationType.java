//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.18 at 01:24:23 AM AEDT 
//


package com.ascention.GS1.pricing.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PricePerformanceRequirementInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePerformanceRequirementInformationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="performanceRequirementDescription" type="{urn:gs1:shared:shared_common:xsd:3}Description70Type" minOccurs="0"/&gt;
 *         &lt;element name="performanceRequirementEndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="performanceRequirementStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="performanceRequirementOption" type="{urn:gs1:gdsn:price_synchronisation_document:xsd:3}PerformanceRequirementOptionCodeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePerformanceRequirementInformationType", namespace = "urn:gs1:gdsn:price_synchronisation_document:xsd:3", propOrder = {
    "performanceRequirementDescription",
    "performanceRequirementEndDateTime",
    "performanceRequirementStartDateTime",
    "performanceRequirementOption"
})
public class PricePerformanceRequirementInformationType {

    protected Description70Type performanceRequirementDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar performanceRequirementEndDateTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar performanceRequirementStartDateTime;
    protected PerformanceRequirementOptionCodeType performanceRequirementOption;

    /**
     * Gets the value of the performanceRequirementDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Description70Type }
     *     
     */
    public Description70Type getPerformanceRequirementDescription() {
        return performanceRequirementDescription;
    }

    /**
     * Sets the value of the performanceRequirementDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description70Type }
     *     
     */
    public void setPerformanceRequirementDescription(Description70Type value) {
        this.performanceRequirementDescription = value;
    }

    /**
     * Gets the value of the performanceRequirementEndDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPerformanceRequirementEndDateTime() {
        return performanceRequirementEndDateTime;
    }

    /**
     * Sets the value of the performanceRequirementEndDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPerformanceRequirementEndDateTime(XMLGregorianCalendar value) {
        this.performanceRequirementEndDateTime = value;
    }

    /**
     * Gets the value of the performanceRequirementStartDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPerformanceRequirementStartDateTime() {
        return performanceRequirementStartDateTime;
    }

    /**
     * Sets the value of the performanceRequirementStartDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPerformanceRequirementStartDateTime(XMLGregorianCalendar value) {
        this.performanceRequirementStartDateTime = value;
    }

    /**
     * Gets the value of the performanceRequirementOption property.
     * 
     * @return
     *     possible object is
     *     {@link PerformanceRequirementOptionCodeType }
     *     
     */
    public PerformanceRequirementOptionCodeType getPerformanceRequirementOption() {
        return performanceRequirementOption;
    }

    /**
     * Sets the value of the performanceRequirementOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformanceRequirementOptionCodeType }
     *     
     */
    public void setPerformanceRequirementOption(PerformanceRequirementOptionCodeType value) {
        this.performanceRequirementOption = value;
    }

}
