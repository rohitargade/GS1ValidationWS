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
 * <p>Java class for AdditionalTradeItemDimensionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalTradeItemDimensionsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="depth" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType"/&gt;
 *         &lt;element name="dimensionTypeCode" type="{urn:gs1:gdsn:gdsn_common:xsd:3}DimensionTypeCodeType"/&gt;
 *         &lt;element name="height" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType"/&gt;
 *         &lt;element name="width" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType"/&gt;
 *         &lt;element name="grossWeight" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalTradeItemDimensionsType", namespace = "urn:gs1:gdsn:gdsn_common:xsd:3", propOrder = {
    "depth",
    "dimensionTypeCode",
    "height",
    "width",
    "grossWeight"
})
public class AdditionalTradeItemDimensionsType {

    @XmlElement(required = true)
    protected MeasurementType depth;
    @XmlElement(required = true)
    protected DimensionTypeCodeType dimensionTypeCode;
    @XmlElement(required = true)
    protected MeasurementType height;
    @XmlElement(required = true)
    protected MeasurementType width;
    protected MeasurementType grossWeight;

    /**
     * Gets the value of the depth property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     */
    public void setDepth(MeasurementType value) {
        this.depth = value;
    }

    /**
     * Gets the value of the dimensionTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionTypeCodeType }
     *     
     */
    public DimensionTypeCodeType getDimensionTypeCode() {
        return dimensionTypeCode;
    }

    /**
     * Sets the value of the dimensionTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionTypeCodeType }
     *     
     */
    public void setDimensionTypeCode(DimensionTypeCodeType value) {
        this.dimensionTypeCode = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     */
    public void setHeight(MeasurementType value) {
        this.height = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     */
    public void setWidth(MeasurementType value) {
        this.width = value;
    }

    /**
     * Gets the value of the grossWeight property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getGrossWeight() {
        return grossWeight;
    }

    /**
     * Sets the value of the grossWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     */
    public void setGrossWeight(MeasurementType value) {
        this.grossWeight = value;
    }

}
