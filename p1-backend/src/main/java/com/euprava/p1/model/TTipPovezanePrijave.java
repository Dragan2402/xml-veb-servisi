//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.22 at 12:41:26 PM CET 
//


package com.euprava.p1.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTip_povezane_prijave.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TTip_povezane_prijave"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Dopunska_prijava"/&gt;
 *     &lt;enumeration value="Izdvojena_prijava"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TTip_povezane_prijave")
@XmlEnum
public enum TTipPovezanePrijave {

    @XmlEnumValue("Dopunska_prijava")
    DOPUNSKA_PRIJAVA("Dopunska_prijava"),
    @XmlEnumValue("Izdvojena_prijava")
    IZDVOJENA_PRIJAVA("Izdvojena_prijava");
    private final String value;

    TTipPovezanePrijave(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TTipPovezanePrijave fromValue(String v) {
        for (TTipPovezanePrijave c: TTipPovezanePrijave.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
