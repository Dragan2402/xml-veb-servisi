//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 01:55:17 AM CET 
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
 *         &lt;element ref="{http://euprava.com/p1/model}Popunjava_zavod"/&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Zahtev_za_priznanje_patenta"/&gt;
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
    "popunjavaZavod",
    "zahtevZaPriznanjePatenta"
})
@XmlRootElement(name = "Obrazac_P1")
public class ObrazacP1 {

    @XmlElement(name = "Popunjava_zavod", required = true)
    protected PopunjavaZavod popunjavaZavod;
    @XmlElement(name = "Zahtev_za_priznanje_patenta", required = true)
    protected ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta;

    /**
     * Gets the value of the popunjavaZavod property.
     * 
     * @return
     *     possible object is
     *     {@link PopunjavaZavod }
     *     
     */
    public PopunjavaZavod getPopunjavaZavod() {
        return popunjavaZavod;
    }

    /**
     * Sets the value of the popunjavaZavod property.
     * 
     * @param value
     *     allowed object is
     *     {@link PopunjavaZavod }
     *     
     */
    public void setPopunjavaZavod(PopunjavaZavod value) {
        this.popunjavaZavod = value;
    }

    /**
     * Gets the value of the zahtevZaPriznanjePatenta property.
     * 
     * @return
     *     possible object is
     *     {@link ZahtevZaPriznanjePatenta }
     *     
     */
    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta() {
        return zahtevZaPriznanjePatenta;
    }

    /**
     * Sets the value of the zahtevZaPriznanjePatenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZahtevZaPriznanjePatenta }
     *     
     */
    public void setZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatenta value) {
        this.zahtevZaPriznanjePatenta = value;
    }

}
