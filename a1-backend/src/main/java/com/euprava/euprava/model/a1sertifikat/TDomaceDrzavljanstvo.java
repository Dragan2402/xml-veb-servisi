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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TDomace_Drzavljanstvo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDomace_Drzavljanstvo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://euprava.euprava.com/model/a1Sertifikat}TDrzavljanstvo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Jmbg" type="{http://euprava.euprava.com/model/a1Sertifikat}TJmbg"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDomace_Drzavljanstvo", propOrder = {
    "jmbg"
})
public class TDomaceDrzavljanstvo
    extends TDrzavljanstvo
{

    @XmlElement(name = "Jmbg", required = true)
    protected String jmbg;

    /**
     * Gets the value of the jmbg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmbg(String value) {
        this.jmbg = value;
    }

}
