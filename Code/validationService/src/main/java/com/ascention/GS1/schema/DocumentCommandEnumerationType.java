//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.29 at 09:22:51 PM AEDT 
//


package com.ascention.GS1.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentCommandEnumerationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DocumentCommandEnumerationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ADD"/&gt;
 *     &lt;enumeration value="CHANGE_BY_REFRESH"/&gt;
 *     &lt;enumeration value="CORRECT"/&gt;
 *     &lt;enumeration value="DELETE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DocumentCommandEnumerationType", namespace = "urn:gs1:gdsn:gdsn_common:xsd:3")
@XmlEnum
public enum DocumentCommandEnumerationType {

    ADD,
    CHANGE_BY_REFRESH,
    CORRECT,
    DELETE;

    public String value() {
        return name();
    }

    public static DocumentCommandEnumerationType fromValue(String v) {
        return valueOf(v);
    }

}
