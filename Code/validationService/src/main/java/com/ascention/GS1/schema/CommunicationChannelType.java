//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:22:51 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommunicationChannelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommunicationChannelType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="communicationChannelCode" type="{urn:gs1:shared:shared_common:xsd:3}CommunicationChannelCodeType"/&gt;
 *         &lt;element name="communicationValue"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="200"/&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="communicationChannelName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="200"/&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommunicationChannelType", propOrder = {
    "communicationChannelCode",
    "communicationValue",
    "communicationChannelName"
})
public class CommunicationChannelType {

    @XmlElement(required = true)
    protected CommunicationChannelCodeType communicationChannelCode;
    @XmlElement(required = true)
    protected String communicationValue;
    protected String communicationChannelName;

    /**
     * Gets the value of the communicationChannelCode property.
     * 
     * @return
     *     possible object is
     *     {@link CommunicationChannelCodeType }
     *     
     */
    public CommunicationChannelCodeType getCommunicationChannelCode() {
        return communicationChannelCode;
    }

    /**
     * Sets the value of the communicationChannelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationChannelCodeType }
     *     
     */
    public void setCommunicationChannelCode(CommunicationChannelCodeType value) {
        this.communicationChannelCode = value;
    }

    /**
     * Gets the value of the communicationValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationValue() {
        return communicationValue;
    }

    /**
     * Sets the value of the communicationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationValue(String value) {
        this.communicationValue = value;
    }

    /**
     * Gets the value of the communicationChannelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationChannelName() {
        return communicationChannelName;
    }

    /**
     * Sets the value of the communicationChannelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationChannelName(String value) {
        this.communicationChannelName = value;
    }

}
