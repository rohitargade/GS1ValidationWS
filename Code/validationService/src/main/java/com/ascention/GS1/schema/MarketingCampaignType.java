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
 * <p>Java class for MarketingCampaignType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketingCampaignType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="campaignEndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="campaignName" type="{urn:gs1:shared:shared_common:xsd:3}Description200Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="campaignStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="campaignMediaTypeDescription" type="{urn:gs1:shared:shared_common:xsd:3}Description500Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingCampaignType", namespace = "urn:gs1:gdsn:marketing_information:xsd:3", propOrder = {
    "campaignEndDateTime",
    "campaignName",
    "campaignStartDateTime",
    "campaignMediaTypeDescription"
})
public class MarketingCampaignType {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar campaignEndDateTime;
    protected List<Description200Type> campaignName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar campaignStartDateTime;
    protected List<Description500Type> campaignMediaTypeDescription;

    /**
     * Gets the value of the campaignEndDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCampaignEndDateTime() {
        return campaignEndDateTime;
    }

    /**
     * Sets the value of the campaignEndDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCampaignEndDateTime(XMLGregorianCalendar value) {
        this.campaignEndDateTime = value;
    }

    /**
     * Gets the value of the campaignName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the campaignName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCampaignName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description200Type }
     * 
     * 
     */
    public List<Description200Type> getCampaignName() {
        if (campaignName == null) {
            campaignName = new ArrayList<Description200Type>();
        }
        return this.campaignName;
    }

    /**
     * Gets the value of the campaignStartDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCampaignStartDateTime() {
        return campaignStartDateTime;
    }

    /**
     * Sets the value of the campaignStartDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCampaignStartDateTime(XMLGregorianCalendar value) {
        this.campaignStartDateTime = value;
    }

    /**
     * Gets the value of the campaignMediaTypeDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the campaignMediaTypeDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCampaignMediaTypeDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description500Type }
     * 
     * 
     */
    public List<Description500Type> getCampaignMediaTypeDescription() {
        if (campaignMediaTypeDescription == null) {
            campaignMediaTypeDescription = new ArrayList<Description500Type>();
        }
        return this.campaignMediaTypeDescription;
    }

}
