//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.05 at 08:29:53 AM CET 
//


package com.euprava.p1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TFizicki_punomocnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFizicki_punomocnik"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://p1.euprava.com/model}TPunomocnik"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Prezime"&gt;
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
@XmlType(name = "TFizicki_punomocnik", propOrder = {
    "ime",
    "prezime"
})
public class TFizickiPunomocnik
    extends TPunomocnik
{

    @XmlElement(name = "Ime", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", required = true)
    protected String prezime;

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

}
