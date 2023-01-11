//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.11 at 04:11:17 PM CET 
//


package com.euprava.euprava.model.a1sertifikat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TDjelo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDjelo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Naslov"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Vrsta_Djela"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Vrsta_Djela"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Forma_Zapisa"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Forma_Zapisa"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Podaci_Autor" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="Poznati_Autor" type="{http://euprava.euprava.com/model/a1Sertifikat}TAutor" maxOccurs="unbounded"/&gt;
 *                   &lt;element name="Nepoznati_Autor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Stvoreno_U_Radnom_Odnosu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Nacin_koriscenja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Podaci_Originalno_Djelo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence minOccurs="0"&gt;
 *                   &lt;element name="Naslov_Originalnog_Djela" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;choice&gt;
 *                     &lt;element name="Poznati_Originalni_Autor" type="{http://euprava.euprava.com/model/a1Sertifikat}TAutor" maxOccurs="unbounded"/&gt;
 *                     &lt;element name="Nepoznati_Autor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;/choice&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDjelo", propOrder = {
    "naslov",
    "vrstaDjela",
    "formaZapisa",
    "podaciAutor",
    "stvorenoURadnomOdnosu",
    "nacinKoriscenja",
    "podaciOriginalnoDjelo"
})
public class TDjelo {

    @XmlElement(name = "Naslov", required = true)
    protected TDjelo.Naslov naslov;
    @XmlElement(name = "Vrsta_Djela", required = true)
    protected TDjelo.VrstaDjela vrstaDjela;
    @XmlElement(name = "Forma_Zapisa", required = true)
    protected TDjelo.FormaZapisa formaZapisa;
    @XmlElement(name = "Podaci_Autor")
    protected TDjelo.PodaciAutor podaciAutor;
    @XmlElement(name = "Stvoreno_U_Radnom_Odnosu")
    protected Boolean stvorenoURadnomOdnosu;
    @XmlElement(name = "Nacin_koriscenja")
    protected String nacinKoriscenja;
    @XmlElement(name = "Podaci_Originalno_Djelo")
    protected TDjelo.PodaciOriginalnoDjelo podaciOriginalnoDjelo;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo.Naslov }
     *     
     */
    public TDjelo.Naslov getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo.Naslov }
     *     
     */
    public void setNaslov(TDjelo.Naslov value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the vrstaDjela property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo.VrstaDjela }
     *     
     */
    public TDjelo.VrstaDjela getVrstaDjela() {
        return vrstaDjela;
    }

    /**
     * Sets the value of the vrstaDjela property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo.VrstaDjela }
     *     
     */
    public void setVrstaDjela(TDjelo.VrstaDjela value) {
        this.vrstaDjela = value;
    }

    /**
     * Gets the value of the formaZapisa property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo.FormaZapisa }
     *     
     */
    public TDjelo.FormaZapisa getFormaZapisa() {
        return formaZapisa;
    }

    /**
     * Sets the value of the formaZapisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo.FormaZapisa }
     *     
     */
    public void setFormaZapisa(TDjelo.FormaZapisa value) {
        this.formaZapisa = value;
    }

    /**
     * Gets the value of the podaciAutor property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo.PodaciAutor }
     *     
     */
    public TDjelo.PodaciAutor getPodaciAutor() {
        return podaciAutor;
    }

    /**
     * Sets the value of the podaciAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo.PodaciAutor }
     *     
     */
    public void setPodaciAutor(TDjelo.PodaciAutor value) {
        this.podaciAutor = value;
    }

    /**
     * Gets the value of the stvorenoURadnomOdnosu property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStvorenoURadnomOdnosu() {
        return stvorenoURadnomOdnosu;
    }

    /**
     * Sets the value of the stvorenoURadnomOdnosu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStvorenoURadnomOdnosu(Boolean value) {
        this.stvorenoURadnomOdnosu = value;
    }

    /**
     * Gets the value of the nacinKoriscenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacinKoriscenja() {
        return nacinKoriscenja;
    }

    /**
     * Sets the value of the nacinKoriscenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacinKoriscenja(String value) {
        this.nacinKoriscenja = value;
    }

    /**
     * Gets the value of the podaciOriginalnoDjelo property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo.PodaciOriginalnoDjelo }
     *     
     */
    public TDjelo.PodaciOriginalnoDjelo getPodaciOriginalnoDjelo() {
        return podaciOriginalnoDjelo;
    }

    /**
     * Sets the value of the podaciOriginalnoDjelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo.PodaciOriginalnoDjelo }
     *     
     */
    public void setPodaciOriginalnoDjelo(TDjelo.PodaciOriginalnoDjelo value) {
        this.podaciOriginalnoDjelo = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Forma_Zapisa"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class FormaZapisa {

        @XmlValue
        protected com.euprava.euprava.model.a1sertifikat.FormaZapisa value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link com.euprava.euprava.model.a1sertifikat.FormaZapisa }
         *     
         */
        public com.euprava.euprava.model.a1sertifikat.FormaZapisa getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.euprava.euprava.model.a1sertifikat.FormaZapisa }
         *     
         */
        public void setValue(com.euprava.euprava.model.a1sertifikat.FormaZapisa value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Naslov {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice&gt;
     *         &lt;element name="Poznati_Autor" type="{http://euprava.euprava.com/model/a1Sertifikat}TAutor" maxOccurs="unbounded"/&gt;
     *         &lt;element name="Nepoznati_Autor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "poznatiAutor",
        "nepoznatiAutor"
    })
    public static class PodaciAutor {

        @XmlElement(name = "Poznati_Autor")
        protected List<TAutor> poznatiAutor;
        @XmlElement(name = "Nepoznati_Autor", defaultValue = "true")
        protected Boolean nepoznatiAutor;

        /**
         * Gets the value of the poznatiAutor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the poznatiAutor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPoznatiAutor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TAutor }
         * 
         * 
         */
        public List<TAutor> getPoznatiAutor() {
            if (poznatiAutor == null) {
                poznatiAutor = new ArrayList<TAutor>();
            }
            return this.poznatiAutor;
        }

        /**
         * Gets the value of the nepoznatiAutor property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNepoznatiAutor() {
            return nepoznatiAutor;
        }

        /**
         * Sets the value of the nepoznatiAutor property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNepoznatiAutor(Boolean value) {
            this.nepoznatiAutor = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence minOccurs="0"&gt;
     *         &lt;element name="Naslov_Originalnog_Djela" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;choice&gt;
     *           &lt;element name="Poznati_Originalni_Autor" type="{http://euprava.euprava.com/model/a1Sertifikat}TAutor" maxOccurs="unbounded"/&gt;
     *           &lt;element name="Nepoznati_Autor" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;/choice&gt;
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
        "naslovOriginalnogDjela",
        "poznatiOriginalniAutor",
        "nepoznatiAutor"
    })
    public static class PodaciOriginalnoDjelo {

        @XmlElement(name = "Naslov_Originalnog_Djela")
        protected String naslovOriginalnogDjela;
        @XmlElement(name = "Poznati_Originalni_Autor")
        protected List<TAutor> poznatiOriginalniAutor;
        @XmlElement(name = "Nepoznati_Autor", defaultValue = "true")
        protected Boolean nepoznatiAutor;

        /**
         * Gets the value of the naslovOriginalnogDjela property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaslovOriginalnogDjela() {
            return naslovOriginalnogDjela;
        }

        /**
         * Sets the value of the naslovOriginalnogDjela property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaslovOriginalnogDjela(String value) {
            this.naslovOriginalnogDjela = value;
        }

        /**
         * Gets the value of the poznatiOriginalniAutor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the poznatiOriginalniAutor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPoznatiOriginalniAutor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TAutor }
         * 
         * 
         */
        public List<TAutor> getPoznatiOriginalniAutor() {
            if (poznatiOriginalniAutor == null) {
                poznatiOriginalniAutor = new ArrayList<TAutor>();
            }
            return this.poznatiOriginalniAutor;
        }

        /**
         * Gets the value of the nepoznatiAutor property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNepoznatiAutor() {
            return nepoznatiAutor;
        }

        /**
         * Sets the value of the nepoznatiAutor property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNepoznatiAutor(Boolean value) {
            this.nepoznatiAutor = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Vrsta_Djela"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class VrstaDjela {

        @XmlValue
        protected com.euprava.euprava.model.a1sertifikat.VrstaDjela value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link com.euprava.euprava.model.a1sertifikat.VrstaDjela }
         *     
         */
        public com.euprava.euprava.model.a1sertifikat.VrstaDjela getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.euprava.euprava.model.a1sertifikat.VrstaDjela }
         *     
         */
        public void setValue(com.euprava.euprava.model.a1sertifikat.VrstaDjela value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }

}
