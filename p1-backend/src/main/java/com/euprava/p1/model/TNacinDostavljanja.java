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
 * <p>Java class for TNacin_dostavljanja.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TNacin_dostavljanja"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Elektronska_forma"/&gt;
 *     &lt;enumeration value="Papirna_forma"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TNacin_dostavljanja")
@XmlEnum
public enum TNacinDostavljanja {

    @XmlEnumValue("Elektronska_forma")
    ELEKTRONSKA_FORMA("Elektronska_forma"),
    @XmlEnumValue("Papirna_forma")
    PAPIRNA_FORMA("Papirna_forma");
    private final String value;

    TNacinDostavljanja(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TNacinDostavljanja fromValue(String v) {
        for (TNacinDostavljanja c: TNacinDostavljanja.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
