//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.21 at 01:34:35 AM CET 
//


package com.euprava.p1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPunomocnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPunomocnik"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://euprava.com/p1/model}TLice"&gt;
 *       &lt;attribute name="tip" use="required" type="{http://euprava.com/p1/model}TTip_punomocnika" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPunomocnik")
@XmlSeeAlso({
    TFizickiPunomocnik.class,
    TPravniPunomocnik.class
})
public abstract class TPunomocnik
    extends TLice
{

    @XmlAttribute(name = "tip", required = true)
    protected TTipPunomocnika tip;

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link TTipPunomocnika }
     *     
     */
    public TTipPunomocnika getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTipPunomocnika }
     *     
     */
    public void setTip(TTipPunomocnika value) {
        this.tip = value;
    }

}
