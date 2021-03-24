//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:09:26 PM AEDT 
//


package com.ascention.GS1.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecurityTagInformationModuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityTagInformationModuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="securityTagInformation" type="{urn:gs1:gdsn:security_tag_information:xsd:3}SecurityTagInformationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityTagInformationModuleType", namespace = "urn:gs1:gdsn:security_tag_information:xsd:3", propOrder = {
    "securityTagInformation"
})
@XmlRootElement(name = "securityTagInformationModule",namespace = "urn:gs1:gdsn:security_tag_information:xsd:3")
public class SecurityTagInformationModuleType {

    protected List<SecurityTagInformationType> securityTagInformation;

    /**
     * Gets the value of the securityTagInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityTagInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityTagInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityTagInformationType }
     * 
     * 
     */
    public List<SecurityTagInformationType> getSecurityTagInformation() {
        if (securityTagInformation == null) {
            securityTagInformation = new ArrayList<SecurityTagInformationType>();
        }
        return this.securityTagInformation;
    }

}
