//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.06.09 at 09:24:29 PM CEST 
//


package com.euprava.p1.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Podnesen"/&gt;
 *     &lt;enumeration value="Odobren"/&gt;
 *     &lt;enumeration value="Odbijen"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TStatus")
@XmlEnum
public enum TStatus {

    @XmlEnumValue("Podnesen")
    PODNESEN("Podnesen"),
    @XmlEnumValue("Odobren")
    ODOBREN("Odobren"),
    @XmlEnumValue("Odbijen")
    ODBIJEN("Odbijen");
    private final String value;

    TStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TStatus fromValue(String v) {
        for (TStatus c: TStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
