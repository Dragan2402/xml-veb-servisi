//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 04:24:54 PM CET 
//


package com.euprava.p1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Pronalazac_nije_naveden" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Lice" type="{http://euprava.com/p1/model}TFizicki_podnosilac"/&gt;
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
    "pronalazacNijeNaveden",
    "lice"
})
@XmlRootElement(name = "Pronalazac")
public class Pronalazac {

    @XmlElement(name = "Pronalazac_nije_naveden")
    protected boolean pronalazacNijeNaveden;
    @XmlElement(name = "Lice", required = true)
    protected TFizickiPodnosilac lice;

    /**
     * Gets the value of the pronalazacNijeNaveden property.
     * 
     */
    public boolean isPronalazacNijeNaveden() {
        return pronalazacNijeNaveden;
    }

    /**
     * Sets the value of the pronalazacNijeNaveden property.
     * 
     */
    public void setPronalazacNijeNaveden(boolean value) {
        this.pronalazacNijeNaveden = value;
    }

    /**
     * Gets the value of the lice property.
     * 
     * @return
     *     possible object is
     *     {@link TFizickiPodnosilac }
     *     
     */
    public TFizickiPodnosilac getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFizickiPodnosilac }
     *     
     */
    public void setLice(TFizickiPodnosilac value) {
        this.lice = value;
    }

}
