//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:22:51 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentIdentificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gsin" type="{urn:gs1:shared:shared_common:xsd:3}GSINType"/&gt;
 *         &lt;element name="additionalShipmentIdentification" type="{urn:gs1:shared:shared_common:xsd:3}AdditionalShipmentIdentificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentIdentificationType", propOrder = {
    "gsin",
    "additionalShipmentIdentification"
})
public class ShipmentIdentificationType {

    @XmlElement(required = true)
    protected String gsin;
    protected List<AdditionalShipmentIdentificationType> additionalShipmentIdentification;

    /**
     * Gets the value of the gsin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGsin() {
        return gsin;
    }

    /**
     * Sets the value of the gsin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGsin(String value) {
        this.gsin = value;
    }

    /**
     * Gets the value of the additionalShipmentIdentification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalShipmentIdentification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalShipmentIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalShipmentIdentificationType }
     * 
     * 
     */
    public List<AdditionalShipmentIdentificationType> getAdditionalShipmentIdentification() {
        if (additionalShipmentIdentification == null) {
            additionalShipmentIdentification = new ArrayList<AdditionalShipmentIdentificationType>();
        }
        return this.additionalShipmentIdentification;
    }

}
