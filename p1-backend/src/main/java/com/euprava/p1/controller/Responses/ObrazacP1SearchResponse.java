package com.euprava.p1.controller.Responses;

import com.euprava.p1.model.NazivPronalaska;
import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.model.Status;
import com.euprava.p1.model.TPodnosilac;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "brojPrijave",
        "priznatiDatumPodnosenja",
        "status",
        "nazivPronalaska",
        "podnosilac"
})
@XmlRootElement(name = "obrazacP1SearchResponse")
public class ObrazacP1SearchResponse {
    @XmlElement(name = "brojPrijave", required = true)
    String brojPrijave;
    @XmlElement(name = "priznatiDatumPodnosenja", required = true)
    XMLGregorianCalendar priznatiDatumPodnosenja;
    @XmlElement(name = "status", required = true)
    Status status;
    @XmlElement(name = "nazivPronalaska", required = true)
    NazivPronalaska nazivPronalaska;
    @XmlElement(name = "podnosilac", required = true)
    TPodnosilac podnosilac;

    public ObrazacP1SearchResponse() {}

    public ObrazacP1SearchResponse(ObrazacP1 obrazacP1) {
        this.brojPrijave = obrazacP1.getPopunjavaZavod().getBrojPrijave();
        this.priznatiDatumPodnosenja = obrazacP1.getPopunjavaZavod().getPriznatiDatumPodnosenja();
        this.status = obrazacP1.getPopunjavaZavod().getStatus();
        this.nazivPronalaska = obrazacP1.getZahtevZaPriznanjePatenta().getNazivPronalaska();
        this.podnosilac = obrazacP1.getZahtevZaPriznanjePatenta().getPodnosilac();
    }
}
