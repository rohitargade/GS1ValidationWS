//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:22:51 PM AEDT 
//


package com.ascention.GS1.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DangerousHazardousLabelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DangerousHazardousLabelType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dangerousHazardousLabelNumber"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dangerousHazardousLabelSequenceNumber" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DangerousHazardousLabelType", namespace = "urn:gs1:gdsn:gdsn_common:xsd:3", propOrder = {
    "dangerousHazardousLabelNumber",
    "dangerousHazardousLabelSequenceNumber"
})
public class DangerousHazardousLabelType {

    @XmlElement(required = true)
    protected String dangerousHazardousLabelNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger dangerousHazardousLabelSequenceNumber;

    /**
     * Gets the value of the dangerousHazardousLabelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDangerousHazardousLabelNumber() {
        return dangerousHazardousLabelNumber;
    }

    /**
     * Sets the value of the dangerousHazardousLabelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDangerousHazardousLabelNumber(String value) {
        this.dangerousHazardousLabelNumber = value;
    }

    /**
     * Gets the value of the dangerousHazardousLabelSequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDangerousHazardousLabelSequenceNumber() {
        return dangerousHazardousLabelSequenceNumber;
    }

    /**
     * Sets the value of the dangerousHazardousLabelSequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDangerousHazardousLabelSequenceNumber(BigInteger value) {
        this.dangerousHazardousLabelSequenceNumber = value;
    }

}
