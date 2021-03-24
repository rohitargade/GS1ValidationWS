//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 04:15:43 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllergenType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllergenType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allergenTypeCode" type="{urn:gs1:gdsn:allergen_information:xsd:3}AllergenTypeCodeType"/&gt;
 *         &lt;element name="levelOfContainmentCode" type="{urn:gs1:gdsn:gdsn_common:xsd:3}LevelOfContainmentCodeType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllergenType", namespace = "urn:gs1:gdsn:allergen_information:xsd:3", propOrder = {
    "allergenTypeCode",
    "levelOfContainmentCode"
})
public class AllergenType {

    @XmlElement(required = true)
    protected AllergenTypeCodeType allergenTypeCode;
    @XmlElement(required = true)
    protected LevelOfContainmentCodeType levelOfContainmentCode;

    /**
     * Gets the value of the allergenTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link AllergenTypeCodeType }
     *     
     */
    public AllergenTypeCodeType getAllergenTypeCode() {
        return allergenTypeCode;
    }

    /**
     * Sets the value of the allergenTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllergenTypeCodeType }
     *     
     */
    public void setAllergenTypeCode(AllergenTypeCodeType value) {
        this.allergenTypeCode = value;
    }

    /**
     * Gets the value of the levelOfContainmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link LevelOfContainmentCodeType }
     *     
     */
    public LevelOfContainmentCodeType getLevelOfContainmentCode() {
        return levelOfContainmentCode;
    }

    /**
     * Sets the value of the levelOfContainmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link LevelOfContainmentCodeType }
     *     
     */
    public void setLevelOfContainmentCode(LevelOfContainmentCodeType value) {
        this.levelOfContainmentCode = value;
    }

}
