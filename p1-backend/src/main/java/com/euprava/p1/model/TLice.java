//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.15 at 02:41:52 PM CET 
//


package com.euprava.p1.model;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for TLice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TLice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Adresa" type="{http://euprava.com/p1/model}TAdresa_unutar_drzave"/&gt;
 *         &lt;element name="Kontakt_informacije" type="{http://euprava.com/p1/model}TLice_kontakt"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLice", propOrder = {
    "adresa",
    "kontaktInformacije"
})
@XmlSeeAlso({
    TFizickoLice.class,
    TPravnoLice.class
})
public abstract class TLice {

    @XmlElement(name = "Adresa", required = true)
    protected TAdresaUnutarDrzave adresa;
    @XmlElement(name = "Kontakt_informacije", required = true)
    protected TLiceKontakt kontaktInformacije;

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresaUnutarDrzave }
     *     
     */
    public TAdresaUnutarDrzave getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresaUnutarDrzave }
     *     
     */
    public void setAdresa(TAdresaUnutarDrzave value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the kontaktInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link TLiceKontakt }
     *     
     */
    public TLiceKontakt getKontaktInformacije() {
        return kontaktInformacije;
    }

    /**
     * Sets the value of the kontaktInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLiceKontakt }
     *     
     */
    public void setKontaktInformacije(TLiceKontakt value) {
        this.kontaktInformacije = value;
    }

}
