//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 08:19:32 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogueItemHierarchicalWithdrawalMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogueItemHierarchicalWithdrawalMessageType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader}StandardBusinessDocumentHeader"/&gt;
 *         &lt;element name="transaction" type="{urn:gs1:gdsn:gdsn_common:xsd:3}TransactionType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogueItemHierarchicalWithdrawalMessageType", namespace = "urn:gs1:gdsn:catalogue_item_hierarchical_withdrawal:xsd:3", propOrder = {
    "standardBusinessDocumentHeader",
    "transaction"
})
@XmlRootElement(name = "catalogue_item_hierarchical_withdrawal",namespace = "urn:gs1:gdsn:catalogue_item_hierarchical_withdrawal:xsd:3")
public class CatalogueItemHierarchicalWithdrawalMessageType {

    @XmlElement(name = "StandardBusinessDocumentHeader", namespace = "http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader", required = true)
    protected StandardBusinessDocumentHeader standardBusinessDocumentHeader;
    @XmlElement(required = true)
    protected List<TransactionType> transaction;

    /**
     * Gets the value of the standardBusinessDocumentHeader property.
     * 
     * @return
     *     possible object is
     *     {@link StandardBusinessDocumentHeader }
     *     
     */
    public StandardBusinessDocumentHeader getStandardBusinessDocumentHeader() {
        return standardBusinessDocumentHeader;
    }

    /**
     * Sets the value of the standardBusinessDocumentHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link StandardBusinessDocumentHeader }
     *     
     */
    public void setStandardBusinessDocumentHeader(StandardBusinessDocumentHeader value) {
        this.standardBusinessDocumentHeader = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionType }
     * 
     * 
     */
    public List<TransactionType> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<TransactionType>();
        }
        return this.transaction;
    }

}
