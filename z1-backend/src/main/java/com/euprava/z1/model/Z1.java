//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.08.19 at 10:18:14 PM CEST 
//


package com.euprava.z1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *         &lt;element name="Podnosilac" type="{http://euprava.com/z1/model}Lice"/&gt;
 *         &lt;element name="Zajednicki_predstavnik" type="{http://euprava.com/z1/model}Lice"/&gt;
 *         &lt;element name="Punomocnik" type="{http://euprava.com/z1/model}Lice"/&gt;
 *         &lt;element name="Tip_ziga" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Tip_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Naznacenje_boje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Opis_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Brojevi_klasa_robe"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Broj_klase_robe" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Pravo_prvenstva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Placene_takse"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Osnovna_taksa" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *                   &lt;element name="Graficko_resenje" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *                   &lt;element name="Ukupno" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Prilozi"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Primerak_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Spisak_robe_i_usluga" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Punomocje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Generalno_punomocje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Punomocje_naknadno_dostavljeno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Opsti_akt_o_kolektivnom_zigu" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Dokaz_o_pravu_prvenstva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Dokaz_o_uplati_takse" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://euprava.com/z1/model}Status"/&gt;
 *         &lt;element ref="{http://euprava.com/z1/model}Datum"/&gt;
 *         &lt;element name="Id_Resenja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Broj_prijave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "podnosilac",
    "zajednickiPredstavnik",
    "punomocnik",
    "tipZiga",
    "tipZnaka",
    "naznacenjeBoje",
    "opisZnaka",
    "brojeviKlasaRobe",
    "pravoPrvenstva",
    "placeneTakse",
    "prilozi",
    "status",
    "datum",
    "idResenja",
    "brojPrijave"
})
@XmlRootElement(name = "Z1")
public class Z1 {

    @XmlElement(name = "Podnosilac", required = true)
    protected Lice podnosilac;
    @XmlElement(name = "Zajednicki_predstavnik", required = true)
    protected Lice zajednickiPredstavnik;
    @XmlElement(name = "Punomocnik", required = true)
    protected Lice punomocnik;
    @XmlElement(name = "Tip_ziga", required = true)
    protected String tipZiga;
    @XmlElement(name = "Tip_znaka", required = true)
    protected String tipZnaka;
    @XmlElement(name = "Naznacenje_boje", required = true)
    protected String naznacenjeBoje;
    @XmlElement(name = "Opis_znaka", required = true)
    protected String opisZnaka;
    @XmlElement(name = "Brojevi_klasa_robe", required = true)
    protected Z1 .BrojeviKlasaRobe brojeviKlasaRobe;
    @XmlElement(name = "Pravo_prvenstva", required = true)
    protected String pravoPrvenstva;
    @XmlElement(name = "Placene_takse", required = true)
    protected Z1 .PlaceneTakse placeneTakse;
    @XmlElement(name = "Prilozi", required = true)
    protected Z1 .Prilozi prilozi;
    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElement(name = "Datum", required = true)
    protected Datum datum;
    @XmlElement(name = "Id_Resenja")
    protected String idResenja;
    @XmlElement(name = "Broj_prijave")
    protected String brojPrijave;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;
    @XmlAttribute(name = "typeof")
    @XmlSchemaType(name = "anySimpleType")
    protected String typeof;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link Lice }
     *     
     */
    public Lice getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lice }
     *     
     */
    public void setPodnosilac(Lice value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the zajednickiPredstavnik property.
     * 
     * @return
     *     possible object is
     *     {@link Lice }
     *     
     */
    public Lice getZajednickiPredstavnik() {
        return zajednickiPredstavnik;
    }

    /**
     * Sets the value of the zajednickiPredstavnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lice }
     *     
     */
    public void setZajednickiPredstavnik(Lice value) {
        this.zajednickiPredstavnik = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link Lice }
     *     
     */
    public Lice getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lice }
     *     
     */
    public void setPunomocnik(Lice value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the tipZiga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipZiga() {
        return tipZiga;
    }

    /**
     * Sets the value of the tipZiga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipZiga(String value) {
        this.tipZiga = value;
    }

    /**
     * Gets the value of the tipZnaka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipZnaka() {
        return tipZnaka;
    }

    /**
     * Sets the value of the tipZnaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipZnaka(String value) {
        this.tipZnaka = value;
    }

    /**
     * Gets the value of the naznacenjeBoje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaznacenjeBoje() {
        return naznacenjeBoje;
    }

    /**
     * Sets the value of the naznacenjeBoje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaznacenjeBoje(String value) {
        this.naznacenjeBoje = value;
    }

    /**
     * Gets the value of the opisZnaka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpisZnaka() {
        return opisZnaka;
    }

    /**
     * Sets the value of the opisZnaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpisZnaka(String value) {
        this.opisZnaka = value;
    }

    /**
     * Gets the value of the brojeviKlasaRobe property.
     * 
     * @return
     *     possible object is
     *     {@link Z1 .BrojeviKlasaRobe }
     *     
     */
    public Z1 .BrojeviKlasaRobe getBrojeviKlasaRobe() {
        return brojeviKlasaRobe;
    }

    /**
     * Sets the value of the brojeviKlasaRobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Z1 .BrojeviKlasaRobe }
     *     
     */
    public void setBrojeviKlasaRobe(Z1 .BrojeviKlasaRobe value) {
        this.brojeviKlasaRobe = value;
    }

    /**
     * Gets the value of the pravoPrvenstva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPravoPrvenstva() {
        return pravoPrvenstva;
    }

    /**
     * Sets the value of the pravoPrvenstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPravoPrvenstva(String value) {
        this.pravoPrvenstva = value;
    }

    /**
     * Gets the value of the placeneTakse property.
     * 
     * @return
     *     possible object is
     *     {@link Z1 .PlaceneTakse }
     *     
     */
    public Z1 .PlaceneTakse getPlaceneTakse() {
        return placeneTakse;
    }

    /**
     * Sets the value of the placeneTakse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Z1 .PlaceneTakse }
     *     
     */
    public void setPlaceneTakse(Z1 .PlaceneTakse value) {
        this.placeneTakse = value;
    }

    /**
     * Gets the value of the prilozi property.
     * 
     * @return
     *     possible object is
     *     {@link Z1 .Prilozi }
     *     
     */
    public Z1 .Prilozi getPrilozi() {
        return prilozi;
    }

    /**
     * Sets the value of the prilozi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Z1 .Prilozi }
     *     
     */
    public void setPrilozi(Z1 .Prilozi value) {
        this.prilozi = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link Datum }
     *     
     */
    public Datum getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Datum }
     *     
     */
    public void setDatum(Datum value) {
        this.datum = value;
    }

    /**
     * Gets the value of the idResenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdResenja() {
        return idResenja;
    }

    /**
     * Sets the value of the idResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdResenja(String value) {
        this.idResenja = value;
    }

    /**
     * Gets the value of the brojPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPrijave(String value) {
        this.brojPrijave = value;
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
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Broj_klase_robe" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "brojKlaseRobe"
    })
    public static class BrojeviKlasaRobe {

        @XmlElement(name = "Broj_klase_robe", type = Integer.class)
        protected List<Integer> brojKlaseRobe;

        /**
         * Gets the value of the brojKlaseRobe property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the brojKlaseRobe property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBrojKlaseRobe().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Integer }
         * 
         * 
         */
        public List<Integer> getBrojKlaseRobe() {
            if (brojKlaseRobe == null) {
                brojKlaseRobe = new ArrayList<Integer>();
            }
            return this.brojKlaseRobe;
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
     *       &lt;sequence&gt;
     *         &lt;element name="Osnovna_taksa" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
     *         &lt;element name="Graficko_resenje" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
     *         &lt;element name="Ukupno" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
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
        "osnovnaTaksa",
        "grafickoResenje",
        "ukupno"
    })
    public static class PlaceneTakse {

        @XmlElement(name = "Osnovna_taksa", required = true)
        protected BigDecimal osnovnaTaksa;
        @XmlElement(name = "Graficko_resenje", required = true)
        protected BigDecimal grafickoResenje;
        @XmlElement(name = "Ukupno", required = true)
        protected BigDecimal ukupno;

        /**
         * Gets the value of the osnovnaTaksa property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getOsnovnaTaksa() {
            return osnovnaTaksa;
        }

        /**
         * Sets the value of the osnovnaTaksa property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setOsnovnaTaksa(BigDecimal value) {
            this.osnovnaTaksa = value;
        }

        /**
         * Gets the value of the grafickoResenje property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getGrafickoResenje() {
            return grafickoResenje;
        }

        /**
         * Sets the value of the grafickoResenje property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setGrafickoResenje(BigDecimal value) {
            this.grafickoResenje = value;
        }

        /**
         * Gets the value of the ukupno property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupno() {
            return ukupno;
        }

        /**
         * Sets the value of the ukupno property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupno(BigDecimal value) {
            this.ukupno = value;
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
     *       &lt;sequence&gt;
     *         &lt;element name="Primerak_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Spisak_robe_i_usluga" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Punomocje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Generalno_punomocje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Punomocje_naknadno_dostavljeno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Opsti_akt_o_kolektivnom_zigu" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Dokaz_o_pravu_prvenstva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Dokaz_o_uplati_takse" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "primerakZnaka",
        "spisakRobeIUsluga",
        "punomocje",
        "generalnoPunomocje",
        "punomocjeNaknadnoDostavljeno",
        "opstiAktOKolektivnomZigu",
        "dokazOPravuPrvenstva",
        "dokazOUplatiTakse"
    })
    public static class Prilozi {

        @XmlElement(name = "Primerak_znaka", required = true)
        protected String primerakZnaka;
        @XmlElement(name = "Spisak_robe_i_usluga", required = true)
        protected String spisakRobeIUsluga;
        @XmlElement(name = "Punomocje", required = true)
        protected String punomocje;
        @XmlElement(name = "Generalno_punomocje", required = true)
        protected String generalnoPunomocje;
        @XmlElement(name = "Punomocje_naknadno_dostavljeno", required = true)
        protected String punomocjeNaknadnoDostavljeno;
        @XmlElement(name = "Opsti_akt_o_kolektivnom_zigu", required = true)
        protected String opstiAktOKolektivnomZigu;
        @XmlElement(name = "Dokaz_o_pravu_prvenstva", required = true)
        protected String dokazOPravuPrvenstva;
        @XmlElement(name = "Dokaz_o_uplati_takse", required = true)
        protected String dokazOUplatiTakse;

        /**
         * Gets the value of the primerakZnaka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimerakZnaka() {
            return primerakZnaka;
        }

        /**
         * Sets the value of the primerakZnaka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimerakZnaka(String value) {
            this.primerakZnaka = value;
        }

        /**
         * Gets the value of the spisakRobeIUsluga property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSpisakRobeIUsluga() {
            return spisakRobeIUsluga;
        }

        /**
         * Sets the value of the spisakRobeIUsluga property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSpisakRobeIUsluga(String value) {
            this.spisakRobeIUsluga = value;
        }

        /**
         * Gets the value of the punomocje property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPunomocje() {
            return punomocje;
        }

        /**
         * Sets the value of the punomocje property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPunomocje(String value) {
            this.punomocje = value;
        }

        /**
         * Gets the value of the generalnoPunomocje property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGeneralnoPunomocje() {
            return generalnoPunomocje;
        }

        /**
         * Sets the value of the generalnoPunomocje property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGeneralnoPunomocje(String value) {
            this.generalnoPunomocje = value;
        }

        /**
         * Gets the value of the punomocjeNaknadnoDostavljeno property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPunomocjeNaknadnoDostavljeno() {
            return punomocjeNaknadnoDostavljeno;
        }

        /**
         * Sets the value of the punomocjeNaknadnoDostavljeno property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPunomocjeNaknadnoDostavljeno(String value) {
            this.punomocjeNaknadnoDostavljeno = value;
        }

        /**
         * Gets the value of the opstiAktOKolektivnomZigu property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOpstiAktOKolektivnomZigu() {
            return opstiAktOKolektivnomZigu;
        }

        /**
         * Sets the value of the opstiAktOKolektivnomZigu property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOpstiAktOKolektivnomZigu(String value) {
            this.opstiAktOKolektivnomZigu = value;
        }

        /**
         * Gets the value of the dokazOPravuPrvenstva property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDokazOPravuPrvenstva() {
            return dokazOPravuPrvenstva;
        }

        /**
         * Sets the value of the dokazOPravuPrvenstva property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDokazOPravuPrvenstva(String value) {
            this.dokazOPravuPrvenstva = value;
        }

        /**
         * Gets the value of the dokazOUplatiTakse property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDokazOUplatiTakse() {
            return dokazOUplatiTakse;
        }

        /**
         * Sets the value of the dokazOUplatiTakse property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDokazOUplatiTakse(String value) {
            this.dokazOUplatiTakse = value;
        }

    }

}
