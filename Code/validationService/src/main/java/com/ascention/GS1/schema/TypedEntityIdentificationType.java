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
 * <p>Java class for TypedEntityIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypedEntityIdentificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:gs1:shared:shared_common:xsd:3}EntityIdentificationType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entityTypeCode" type="{urn:gs1:shared:shared_common:xsd:3}EntityTypeCodeType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypedEntityIdentificationType", propOrder = {
    "entityTypeCode"
})
public class TypedEntityIdentificationType
    extends EntityIdentificationType
{

    @XmlElement(required = true)
    protected EntityTypeCodeType entityTypeCode;

    /**
     * Gets the value of the entityTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link EntityTypeCodeType }
     *     
     */
    public EntityTypeCodeType getEntityTypeCode() {
        return entityTypeCode;
    }

    /**
     * Sets the value of the entityTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityTypeCodeType }
     *     
     */
    public void setEntityTypeCode(EntityTypeCodeType value) {
        this.entityTypeCode = value;
    }

}
