//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.15 at 12:24:16 PM CET 
//


package com.euprava.euprava.model.a1sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TAutor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAutor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pseudonim_Znak_Autora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutor", propOrder = {
    "pseudonimZnakAutora"
})
@XmlSeeAlso({
    TZiviAutor.class,
    TPreminuliAutor.class
})
public abstract class TAutor {

    @XmlElement(name = "Pseudonim_Znak_Autora")
    protected String pseudonimZnakAutora;

    /**
     * Gets the value of the pseudonimZnakAutora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudonimZnakAutora() {
        return pseudonimZnakAutora;
    }

    /**
     * Sets the value of the pseudonimZnakAutora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudonimZnakAutora(String value) {
        this.pseudonimZnakAutora = value;
    }

}
