//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 08:55:15 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SeasonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isTradeItemSeasonal" type="{urn:gs1:shared:shared_common:xsd:3}NonBinaryLogicEnumerationType" minOccurs="0"/&gt;
 *         &lt;element name="seasonalAvailabilityEndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="seasonalAvailabilityStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="seasonCalendarYear" type="{http://www.w3.org/2001/XMLSchema}gYear" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="seasonName" type="{urn:gs1:shared:shared_common:xsd:3}Description70Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="seasonParameterCode" type="{urn:gs1:gdsn:marketing_information:xsd:3}SeasonParameterCodeType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonType", namespace = "urn:gs1:gdsn:marketing_information:xsd:3", propOrder = {
    "isTradeItemSeasonal",
    "seasonalAvailabilityEndDateTime",
    "seasonalAvailabilityStartDateTime",
    "seasonCalendarYear",
    "seasonName",
    "seasonParameterCode"
})
public class SeasonType {

    @XmlSchemaType(name = "string")
    protected NonBinaryLogicEnumerationType isTradeItemSeasonal;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar seasonalAvailabilityEndDateTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar seasonalAvailabilityStartDateTime;
    @XmlSchemaType(name = "gYear")
    protected List<XMLGregorianCalendar> seasonCalendarYear;
    protected List<Description70Type> seasonName;
    protected List<SeasonParameterCodeType> seasonParameterCode;

    /**
     * Gets the value of the isTradeItemSeasonal property.
     * 
     * @return
     *     possible object is
     *     {@link NonBinaryLogicEnumerationType }
     *     
     */
    public NonBinaryLogicEnumerationType getIsTradeItemSeasonal() {
        return isTradeItemSeasonal;
    }

    /**
     * Sets the value of the isTradeItemSeasonal property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonBinaryLogicEnumerationType }
     *     
     */
    public void setIsTradeItemSeasonal(NonBinaryLogicEnumerationType value) {
        this.isTradeItemSeasonal = value;
    }

    /**
     * Gets the value of the seasonalAvailabilityEndDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSeasonalAvailabilityEndDateTime() {
        return seasonalAvailabilityEndDateTime;
    }

    /**
     * Sets the value of the seasonalAvailabilityEndDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSeasonalAvailabilityEndDateTime(XMLGregorianCalendar value) {
        this.seasonalAvailabilityEndDateTime = value;
    }

    /**
     * Gets the value of the seasonalAvailabilityStartDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSeasonalAvailabilityStartDateTime() {
        return seasonalAvailabilityStartDateTime;
    }

    /**
     * Sets the value of the seasonalAvailabilityStartDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSeasonalAvailabilityStartDateTime(XMLGregorianCalendar value) {
        this.seasonalAvailabilityStartDateTime = value;
    }

    /**
     * Gets the value of the seasonCalendarYear property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seasonCalendarYear property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeasonCalendarYear().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getSeasonCalendarYear() {
        if (seasonCalendarYear == null) {
            seasonCalendarYear = new ArrayList<XMLGregorianCalendar>();
        }
        return this.seasonCalendarYear;
    }

    /**
     * Gets the value of the seasonName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seasonName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeasonName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description70Type }
     * 
     * 
     */
    public List<Description70Type> getSeasonName() {
        if (seasonName == null) {
            seasonName = new ArrayList<Description70Type>();
        }
        return this.seasonName;
    }

    /**
     * Gets the value of the seasonParameterCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seasonParameterCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeasonParameterCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SeasonParameterCodeType }
     * 
     * 
     */
    public List<SeasonParameterCodeType> getSeasonParameterCode() {
        if (seasonParameterCode == null) {
            seasonParameterCode = new ArrayList<SeasonParameterCodeType>();
        }
        return this.seasonParameterCode;
    }

}
