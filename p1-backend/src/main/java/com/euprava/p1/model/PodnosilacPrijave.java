//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.15 at 02:41:52 PM CET 
//


package com.euprava.p1.model;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Podnosilac_je_pronalazac" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Lice" type="{http://euprava.com/p1/model}TPodnosilac"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilacJePronalazac",
    "lice"
})
@XmlRootElement(name = "Podnosilac_prijave")
public class PodnosilacPrijave {

    @XmlElement(name = "Podnosilac_je_pronalazac")
    protected boolean podnosilacJePronalazac;
    @XmlElement(name = "Lice", required = true)
    protected TPodnosilac lice;

    /**
     * Gets the value of the podnosilacJePronalazac property.
     * 
     */
    public boolean isPodnosilacJePronalazac() {
        return podnosilacJePronalazac;
    }

    /**
     * Sets the value of the podnosilacJePronalazac property.
     * 
     */
    public void setPodnosilacJePronalazac(boolean value) {
        this.podnosilacJePronalazac = value;
    }

    /**
     * Gets the value of the lice property.
     * 
     * @return
     *     possible object is
     *     {@link TPodnosilac }
     *     
     */
    public TPodnosilac getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodnosilac }
     *     
     */
    public void setLice(TPodnosilac value) {
        this.lice = value;
    }

}
