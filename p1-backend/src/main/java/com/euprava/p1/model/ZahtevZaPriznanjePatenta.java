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
 *         &lt;element ref="{http://euprava.com/p1/model}Naziv_pronalaska"/&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Podnosilac_prijave"/&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Pronalazac" minOccurs="0"/&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Punomocnik_ili_predstavnik" minOccurs="0"/&gt;
 *         &lt;element name="Adresa_za_dostavljanje" type="{http://euprava.com/p1/model}TAdresa_unutar_drzave" minOccurs="0"/&gt;
 *         &lt;element name="Nacin_dostavljanja" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Elektronski_dokument"/&gt;
 *               &lt;enumeration value="Papirni_dokument"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Povezana_prijava" minOccurs="0"/&gt;
 *         &lt;element ref="{http://euprava.com/p1/model}Zahtev_za_priznanje_prava_prvenstva" minOccurs="0"/&gt;
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
    "nazivPronalaska",
    "podnosilacPrijave",
    "pronalazac",
    "punomocnikIliPredstavnik",
    "adresaZaDostavljanje",
    "nacinDostavljanja",
    "povezanaPrijava",
    "zahtevZaPriznanjePravaPrvenstva"
})
@XmlRootElement(name = "Zahtev_za_priznanje_patenta")
public class ZahtevZaPriznanjePatenta {

    @XmlElement(name = "Naziv_pronalaska", required = true)
    protected NazivPronalaska nazivPronalaska;
    @XmlElement(name = "Podnosilac_prijave", required = true)
    protected PodnosilacPrijave podnosilacPrijave;
    @XmlElement(name = "Pronalazac")
    protected Pronalazac pronalazac;
    @XmlElement(name = "Punomocnik_ili_predstavnik")
    protected PunomocnikIliPredstavnik punomocnikIliPredstavnik;
    @XmlElement(name = "Adresa_za_dostavljanje")
    protected TAdresaUnutarDrzave adresaZaDostavljanje;
    @XmlElement(name = "Nacin_dostavljanja")
    protected String nacinDostavljanja;
    @XmlElement(name = "Povezana_prijava")
    protected PovezanaPrijava povezanaPrijava;
    @XmlElement(name = "Zahtev_za_priznanje_prava_prvenstva")
    protected ZahtevZaPriznanjePravaPrvenstva zahtevZaPriznanjePravaPrvenstva;

    /**
     * Gets the value of the nazivPronalaska property.
     * 
     * @return
     *     possible object is
     *     {@link NazivPronalaska }
     *     
     */
    public NazivPronalaska getNazivPronalaska() {
        return nazivPronalaska;
    }

    /**
     * Sets the value of the nazivPronalaska property.
     * 
     * @param value
     *     allowed object is
     *     {@link NazivPronalaska }
     *     
     */
    public void setNazivPronalaska(NazivPronalaska value) {
        this.nazivPronalaska = value;
    }

    /**
     * Gets the value of the podnosilacPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link PodnosilacPrijave }
     *     
     */
    public PodnosilacPrijave getPodnosilacPrijave() {
        return podnosilacPrijave;
    }

    /**
     * Sets the value of the podnosilacPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodnosilacPrijave }
     *     
     */
    public void setPodnosilacPrijave(PodnosilacPrijave value) {
        this.podnosilacPrijave = value;
    }

    /**
     * Gets the value of the pronalazac property.
     * 
     * @return
     *     possible object is
     *     {@link Pronalazac }
     *     
     */
    public Pronalazac getPronalazac() {
        return pronalazac;
    }

    /**
     * Sets the value of the pronalazac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pronalazac }
     *     
     */
    public void setPronalazac(Pronalazac value) {
        this.pronalazac = value;
    }

    /**
     * Gets the value of the punomocnikIliPredstavnik property.
     * 
     * @return
     *     possible object is
     *     {@link PunomocnikIliPredstavnik }
     *     
     */
    public PunomocnikIliPredstavnik getPunomocnikIliPredstavnik() {
        return punomocnikIliPredstavnik;
    }

    /**
     * Sets the value of the punomocnikIliPredstavnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link PunomocnikIliPredstavnik }
     *     
     */
    public void setPunomocnikIliPredstavnik(PunomocnikIliPredstavnik value) {
        this.punomocnikIliPredstavnik = value;
    }

    /**
     * Gets the value of the adresaZaDostavljanje property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresaUnutarDrzave }
     *     
     */
    public TAdresaUnutarDrzave getAdresaZaDostavljanje() {
        return adresaZaDostavljanje;
    }

    /**
     * Sets the value of the adresaZaDostavljanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresaUnutarDrzave }
     *     
     */
    public void setAdresaZaDostavljanje(TAdresaUnutarDrzave value) {
        this.adresaZaDostavljanje = value;
    }

    /**
     * Gets the value of the nacinDostavljanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacinDostavljanja() {
        return nacinDostavljanja;
    }

    /**
     * Sets the value of the nacinDostavljanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacinDostavljanja(String value) {
        this.nacinDostavljanja = value;
    }

    /**
     * Gets the value of the povezanaPrijava property.
     * 
     * @return
     *     possible object is
     *     {@link PovezanaPrijava }
     *     
     */
    public PovezanaPrijava getPovezanaPrijava() {
        return povezanaPrijava;
    }

    /**
     * Sets the value of the povezanaPrijava property.
     * 
     * @param value
     *     allowed object is
     *     {@link PovezanaPrijava }
     *     
     */
    public void setPovezanaPrijava(PovezanaPrijava value) {
        this.povezanaPrijava = value;
    }

    /**
     * Gets the value of the zahtevZaPriznanjePravaPrvenstva property.
     * 
     * @return
     *     possible object is
     *     {@link ZahtevZaPriznanjePravaPrvenstva }
     *     
     */
    public ZahtevZaPriznanjePravaPrvenstva getZahtevZaPriznanjePravaPrvenstva() {
        return zahtevZaPriznanjePravaPrvenstva;
    }

    /**
     * Sets the value of the zahtevZaPriznanjePravaPrvenstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZahtevZaPriznanjePravaPrvenstva }
     *     
     */
    public void setZahtevZaPriznanjePravaPrvenstva(ZahtevZaPriznanjePravaPrvenstva value) {
        this.zahtevZaPriznanjePravaPrvenstva = value;
    }

}
