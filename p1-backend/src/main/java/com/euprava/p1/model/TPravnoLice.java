//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.15 at 02:41:52 PM CET 
//


package com.euprava.p1.model;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for TPravno_lice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPravno_lice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://euprava.com/p1/model}TLice"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Poslovno_ime"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[A-ZŠĐČĆŽ]+"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPravno_lice", propOrder = {
    "poslovnoIme"
})
public class TPravnoLice
    extends TLice
{

    @XmlElement(name = "Poslovno_ime", required = true)
    protected String poslovnoIme;

    /**
     * Gets the value of the poslovnoIme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoslovnoIme() {
        return poslovnoIme;
    }

    /**
     * Sets the value of the poslovnoIme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoslovnoIme(String value) {
        this.poslovnoIme = value;
    }

}
