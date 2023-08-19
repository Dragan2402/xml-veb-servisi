package com.euprava.z1.controller.request;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "primerakZnaka",
        "spisakRobe",
        "punomocje",
        "punomocjeRanije",
        "punomocjeNaknadno",
        "opstiAkt",
        "pravoPrvenstva",
        "uplataTakse",
})

@XmlRootElement(name = "Z1ZavodRequest")
public class Z1ZavodRequest {

    @XmlElement(name = "primerakZnaka", required = true)
    private String primerakZnaka;

    @XmlElement(name = "spisakRobe", required = true)
    private String spisakRobe;

    @XmlElement(name = "punomocje", required = true)
    private String punomocje;

    @XmlElement(name = "punomocjeRanije", required = true)
    private String punomocjeRanije;

    @XmlElement(name = "punomocjeNaknadno", required = true)
    private String punomocjeNaknadno;

    @XmlElement(name = "opstiAkt", required = true)
    private String opstiAkt;

    @XmlElement(name = "pravoPrvenstva", required = true)
    private String pravoPrvenstva;

    @XmlElement(name = "uplataTakse", required = true)
    private String uplataTakse;

    public Z1ZavodRequest(){}

    public Z1ZavodRequest(String primerakZnaka, String spisakRobe, String punomocje, String punomocjeRanije, String punomocjeNaknadno, String opstiAkt, String pravoPrvenstva, String uplataTakse) {
        this.primerakZnaka = primerakZnaka;
        this.spisakRobe = spisakRobe;
        this.punomocje = punomocje;
        this.punomocjeRanije = punomocjeRanije;
        this.punomocjeNaknadno = punomocjeNaknadno;
        this.opstiAkt = opstiAkt;
        this.pravoPrvenstva = pravoPrvenstva;
        this.uplataTakse = uplataTakse;
    }

    public String getPrimerakZnaka() {
        return primerakZnaka;
    }

    public void setPrimerakZnaka(String primerakZnaka) {
        this.primerakZnaka = primerakZnaka;
    }

    public String getSpisakRobe() {
        return spisakRobe;
    }

    public void setSpisakRobe(String spisakRobe) {
        this.spisakRobe = spisakRobe;
    }

    public String getPunomocje() {
        return punomocje;
    }

    public void setPunomocje(String punomocje) {
        this.punomocje = punomocje;
    }

    public String getPunomocjeRanije() {
        return punomocjeRanije;
    }

    public void setPunomocjeRanije(String punomocjeRanije) {
        this.punomocjeRanije = punomocjeRanije;
    }

    public String getPunomocjeNaknadno() {
        return punomocjeNaknadno;
    }

    public void setPunomocjeNaknadno(String punomocjeNaknadno) {
        this.punomocjeNaknadno = punomocjeNaknadno;
    }

    public String getOpstiAkt() {
        return opstiAkt;
    }

    public void setOpstiAkt(String opstiAkt) {
        this.opstiAkt = opstiAkt;
    }

    public String getPravoPrvenstva() {
        return pravoPrvenstva;
    }

    public void setPravoPrvenstva(String pravoPrvenstva) {
        this.pravoPrvenstva = pravoPrvenstva;
    }

    public String getUplataTakse() {
        return uplataTakse;
    }

    public void setUplataTakse(String uplataTakse) {
        this.uplataTakse = uplataTakse;
    }

    @Override
    public String toString() {
        return "Z1ZavodRequest{" +
                "primerakZnaka='" + primerakZnaka + '\'' +
                ", spisakRobe='" + spisakRobe + '\'' +
                ", punomocje='" + punomocje + '\'' +
                ", punomocjeRanije='" + punomocjeRanije + '\'' +
                ", punomocjeNaknadno='" + punomocjeNaknadno + '\'' +
                ", opstiAkt='" + opstiAkt + '\'' +
                ", pravoPrvenstva='" + pravoPrvenstva + '\'' +
                ", uplataTakse='" + uplataTakse + '\'' +
                '}';
    }
}
