//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:16:12 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TradeItemMeasurementsModuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TradeItemMeasurementsModuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tradeItemMeasurements" type="{urn:gs1:gdsn:trade_item_measurements:xsd:3}TradeItemMeasurementsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeItemMeasurementsModuleType", namespace = "urn:gs1:gdsn:trade_item_measurements:xsd:3", propOrder = {
    "tradeItemMeasurements"
})
@XmlRootElement(name = "tradeItemMeasurementsModule",namespace = "urn:gs1:gdsn:trade_item_measurements:xsd:3")
public class TradeItemMeasurementsModuleType {

    protected TradeItemMeasurementsType tradeItemMeasurements;

    /**
     * Gets the value of the tradeItemMeasurements property.
     * 
     * @return
     *     possible object is
     *     {@link TradeItemMeasurementsType }
     *     
     */
    public TradeItemMeasurementsType getTradeItemMeasurements() {
        return tradeItemMeasurements;
    }

    /**
     * Sets the value of the tradeItemMeasurements property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeItemMeasurementsType }
     *     
     */
    public void setTradeItemMeasurements(TradeItemMeasurementsType value) {
        this.tradeItemMeasurements = value;
    }

}
