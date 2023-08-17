//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.06.09 at 09:24:23 PM CEST 
//


package com.euprava.euprava.model.a1sertifikat;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


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
 *         &lt;element name="id"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Id_Klijenta"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Podnosilac" type="{http://euprava.euprava.com/model/a1Sertifikat}TPodnosilac"/&gt;
 *         &lt;element name="Punomocnik" type="{http://euprava.euprava.com/model/a1Sertifikat}TOsoba" minOccurs="0"/&gt;
 *         &lt;element name="Djelo" type="{http://euprava.euprava.com/model/a1Sertifikat}TDjelo"/&gt;
 *         &lt;element name="Potpis" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Sifra_Opisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sifra_Primjera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Datum_Podnosenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Status"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Status_Zahtjeva"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Broj_Prijave" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="Id_Rjesenja" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="typeof" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;anyAttribute/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "idKlijenta",
    "podnosilac",
    "punomocnik",
    "djelo",
    "potpis",
    "sifraOpisa",
    "sifraPrimjera",
    "datumPodnosenja",
    "status",
    "brojPrijave",
    "idRjesenja"
})
@XmlRootElement(name = "obrazacA1")
public class ObrazacA1 {

    protected long id;
    @XmlElement(name = "Id_Klijenta")
    protected long idKlijenta;
    @XmlElement(name = "Podnosilac", required = true)
    protected TPodnosilac podnosilac;
    @XmlElement(name = "Punomocnik")
    protected TOsoba punomocnik;
    @XmlElement(name = "Djelo", required = true)
    protected TDjelo djelo;
    @XmlElement(name = "Potpis", required = true)
    protected String potpis;
    @XmlElement(name = "Sifra_Opisa")
    protected String sifraOpisa;
    @XmlElement(name = "Sifra_Primjera")
    protected String sifraPrimjera;
    @XmlElement(name = "Datum_Podnosenja", required = true)
    protected ObrazacA1 .DatumPodnosenja datumPodnosenja;
    @XmlElement(name = "Status", required = true, defaultValue = "Podnesen")
    protected ObrazacA1 .Status status;
    @XmlElement(name = "Broj_Prijave")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger brojPrijave;
    @XmlElement(name = "Id_Rjesenja")
    protected Long idRjesenja;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;
    @XmlAttribute(name = "typeof")
    @XmlSchemaType(name = "anySimpleType")
    protected String typeof;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idKlijenta property.
     * 
     */
    public long getIdKlijenta() {
        return idKlijenta;
    }

    /**
     * Sets the value of the idKlijenta property.
     * 
     */
    public void setIdKlijenta(long value) {
        this.idKlijenta = value;
    }

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link TPodnosilac }
     *     
     */
    public TPodnosilac getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodnosilac }
     *     
     */
    public void setPodnosilac(TPodnosilac value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link TOsoba }
     *     
     */
    public TOsoba getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsoba }
     *     
     */
    public void setPunomocnik(TOsoba value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the djelo property.
     * 
     * @return
     *     possible object is
     *     {@link TDjelo }
     *     
     */
    public TDjelo getDjelo() {
        return djelo;
    }

    /**
     * Sets the value of the djelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDjelo }
     *     
     */
    public void setDjelo(TDjelo value) {
        this.djelo = value;
    }

    /**
     * Gets the value of the potpis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPotpis() {
        return potpis;
    }

    /**
     * Sets the value of the potpis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPotpis(String value) {
        this.potpis = value;
    }

    /**
     * Gets the value of the sifraOpisa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraOpisa() {
        return sifraOpisa;
    }

    /**
     * Sets the value of the sifraOpisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraOpisa(String value) {
        this.sifraOpisa = value;
    }

    /**
     * Gets the value of the sifraPrimjera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraPrimjera() {
        return sifraPrimjera;
    }

    /**
     * Sets the value of the sifraPrimjera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraPrimjera(String value) {
        this.sifraPrimjera = value;
    }

    /**
     * Gets the value of the datumPodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link ObrazacA1 .DatumPodnosenja }
     *     
     */
    public ObrazacA1 .DatumPodnosenja getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObrazacA1 .DatumPodnosenja }
     *     
     */
    public void setDatumPodnosenja(ObrazacA1 .DatumPodnosenja value) {
        this.datumPodnosenja = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ObrazacA1 .Status }
     *     
     */
    public ObrazacA1 .Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObrazacA1 .Status }
     *     
     */
    public void setStatus(ObrazacA1 .Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the brojPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojPrijave(BigInteger value) {
        this.brojPrijave = value;
    }

    /**
     * Gets the value of the idRjesenja property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdRjesenja() {
        return idRjesenja;
    }

    /**
     * Sets the value of the idRjesenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdRjesenja(Long value) {
        this.idRjesenja = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the typeof property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeof() {
        return typeof;
    }

    /**
     * Sets the value of the typeof property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeof(String value) {
        this.typeof = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
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
    public static class DatumPodnosenja {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
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
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValue(XMLGregorianCalendar value) {
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
     *     &lt;extension base="&lt;http://euprava.euprava.com/model/a1Sertifikat&gt;Status_Zahtjeva"&gt;
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
    public static class Status {

        @XmlValue
        protected StatusZahtjeva value;
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
         *     {@link StatusZahtjeva }
         *     
         */
        public StatusZahtjeva getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link StatusZahtjeva }
         *     
         */
        public void setValue(StatusZahtjeva value) {
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
