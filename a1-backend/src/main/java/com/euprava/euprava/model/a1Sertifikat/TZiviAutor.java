//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.05 at 03:38:56 по подне CET 
//


package com.euprava.euprava.model.a1Sertifikat;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZivi_Autor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZivi_Autor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://euprava.rs/a1}TAutor">
 *       &lt;sequence>
 *         &lt;element name="Podaci_Autor" type="{http://euprava.rs/a1}TOsoba"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZivi_Autor", propOrder = {
    "podaciAutor"
})
public class TZiviAutor
    extends TAutor
{

    @XmlElement(name = "Podaci_Autor", required = true)
    protected TOsoba podaciAutor;

    /**
     * Gets the value of the podaciAutor property.
     * 
     * @return
     *     possible object is
     *     {@link com.euprava.TOsoba }
     *     
     */
    public TOsoba getPodaciAutor() {
        return podaciAutor;
    }

    /**
     * Sets the value of the podaciAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.euprava.TOsoba }
     *     
     */
    public void setPodaciAutor(TOsoba value) {
        this.podaciAutor = value;
    }

}
