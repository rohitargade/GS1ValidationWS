//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 08:10:28 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnimalFeedingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnimalFeedingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="feedLifestage" type="{urn:gs1:shared:shared_common:xsd:3}Description500Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="maximumWeightOfAnimalBeingFed" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="minimumWeightOfAnimalBeingFed" type="{urn:gs1:shared:shared_common:xsd:3}MeasurementType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="animalFeedingDetail" type="{urn:gs1:gdsn:animal_feeding:xsd:3}AnimalFeedingDetailType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="avpList" type="{urn:gs1:gdsn:gdsn_common:xsd:3}GS1_AttributeValuePairListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnimalFeedingType", namespace = "urn:gs1:gdsn:animal_feeding:xsd:3", propOrder = {
    "feedLifestage",
    "maximumWeightOfAnimalBeingFed",
    "minimumWeightOfAnimalBeingFed",
    "animalFeedingDetail",
    "avpList"
})
public class AnimalFeedingType {

    protected List<Description500Type> feedLifestage;
    protected List<MeasurementType> maximumWeightOfAnimalBeingFed;
    protected List<MeasurementType> minimumWeightOfAnimalBeingFed;
    protected List<AnimalFeedingDetailType> animalFeedingDetail;
    protected GS1AttributeValuePairListType avpList;

    /**
     * Gets the value of the feedLifestage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the feedLifestage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeedLifestage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description500Type }
     * 
     * 
     */
    public List<Description500Type> getFeedLifestage() {
        if (feedLifestage == null) {
            feedLifestage = new ArrayList<Description500Type>();
        }
        return this.feedLifestage;
    }

    /**
     * Gets the value of the maximumWeightOfAnimalBeingFed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maximumWeightOfAnimalBeingFed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaximumWeightOfAnimalBeingFed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasurementType }
     * 
     * 
     */
    public List<MeasurementType> getMaximumWeightOfAnimalBeingFed() {
        if (maximumWeightOfAnimalBeingFed == null) {
            maximumWeightOfAnimalBeingFed = new ArrayList<MeasurementType>();
        }
        return this.maximumWeightOfAnimalBeingFed;
    }

    /**
     * Gets the value of the minimumWeightOfAnimalBeingFed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the minimumWeightOfAnimalBeingFed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinimumWeightOfAnimalBeingFed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasurementType }
     * 
     * 
     */
    public List<MeasurementType> getMinimumWeightOfAnimalBeingFed() {
        if (minimumWeightOfAnimalBeingFed == null) {
            minimumWeightOfAnimalBeingFed = new ArrayList<MeasurementType>();
        }
        return this.minimumWeightOfAnimalBeingFed;
    }

    /**
     * Gets the value of the animalFeedingDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the animalFeedingDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnimalFeedingDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnimalFeedingDetailType }
     * 
     * 
     */
    public List<AnimalFeedingDetailType> getAnimalFeedingDetail() {
        if (animalFeedingDetail == null) {
            animalFeedingDetail = new ArrayList<AnimalFeedingDetailType>();
        }
        return this.animalFeedingDetail;
    }

    /**
     * Gets the value of the avpList property.
     * 
     * @return
     *     possible object is
     *     {@link GS1AttributeValuePairListType }
     *     
     */
    public GS1AttributeValuePairListType getAvpList() {
        return avpList;
    }

    /**
     * Sets the value of the avpList property.
     * 
     * @param value
     *     allowed object is
     *     {@link GS1AttributeValuePairListType }
     *     
     */
    public void setAvpList(GS1AttributeValuePairListType value) {
        this.avpList = value;
    }

}
