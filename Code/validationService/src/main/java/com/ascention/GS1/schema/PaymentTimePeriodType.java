//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:22:51 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PaymentTimePeriodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTimePeriodType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateDue" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="dayOfMonthDue" type="{http://www.w3.org/2001/XMLSchema}gDay" minOccurs="0"/&gt;
 *         &lt;element name="timePeriodDue" type="{urn:gs1:shared:shared_common:xsd:3}TimeMeasurementType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTimePeriodType", propOrder = {
    "dateDue",
    "dayOfMonthDue",
    "timePeriodDue"
})
public class PaymentTimePeriodType {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDue;
    @XmlSchemaType(name = "gDay")
    protected XMLGregorianCalendar dayOfMonthDue;
    protected TimeMeasurementType timePeriodDue;

    /**
     * Gets the value of the dateDue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDue() {
        return dateDue;
    }

    /**
     * Sets the value of the dateDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDue(XMLGregorianCalendar value) {
        this.dateDue = value;
    }

    /**
     * Gets the value of the dayOfMonthDue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDayOfMonthDue() {
        return dayOfMonthDue;
    }

    /**
     * Sets the value of the dayOfMonthDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDayOfMonthDue(XMLGregorianCalendar value) {
        this.dayOfMonthDue = value;
    }

    /**
     * Gets the value of the timePeriodDue property.
     * 
     * @return
     *     possible object is
     *     {@link TimeMeasurementType }
     *     
     */
    public TimeMeasurementType getTimePeriodDue() {
        return timePeriodDue;
    }

    /**
     * Sets the value of the timePeriodDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMeasurementType }
     *     
     */
    public void setTimePeriodDue(TimeMeasurementType value) {
        this.timePeriodDue = value;
    }

}
