//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.18 at 11:07:10 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogueItemNotificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogueItemNotificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:gs1:shared:shared_common:xsd:3}DocumentType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="catalogueItemNotificationIdentification" type="{urn:gs1:shared:shared_common:xsd:3}EntityIdentificationType"/&gt;
 *         &lt;element name="isReload" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="catalogueItem" type="{urn:gs1:gdsn:catalogue_item_notification:xsd:3}CatalogueItemType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogueItemNotification", namespace = "urn:gs1:gdsn:catalogue_item_notification:xsd:3", propOrder = {
    "catalogueItemNotificationIdentification",
    "isReload",
    "catalogueItem"
})
@XmlRootElement(name = "catalogue_item_notification",namespace = "urn:gs1:gdsn:catalogue_item_notification:xsd:3")
public class CatalogueItemNotificationType
    extends DocumentType
{

    @XmlElement(required = true)
    protected EntityIdentificationType catalogueItemNotificationIdentification;
    protected boolean isReload;
    @XmlElement(required = true)
    protected CatalogueItemType catalogueItem;

    /**
     * Gets the value of the catalogueItemNotificationIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getCatalogueItemNotificationIdentification() {
        return catalogueItemNotificationIdentification;
    }

    /**
     * Sets the value of the catalogueItemNotificationIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setCatalogueItemNotificationIdentification(EntityIdentificationType value) {
        this.catalogueItemNotificationIdentification = value;
    }

    /**
     * Gets the value of the isReload property.
     * 
     */
    public boolean isIsReload() {
        return isReload;
    }

    /**
     * Sets the value of the isReload property.
     * 
     */
    public void setIsReload(boolean value) {
        this.isReload = value;
    }

    /**
     * Gets the value of the catalogueItem property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogueItemType }
     *     
     */
    public CatalogueItemType getCatalogueItem() {
        return catalogueItem;
    }

    /**
     * Sets the value of the catalogueItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogueItemType }
     *     
     */
    public void setCatalogueItem(CatalogueItemType value) {
        this.catalogueItem = value;
    }

}
