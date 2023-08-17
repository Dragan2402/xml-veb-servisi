package com.euprava.z1.controller.response;
import com.euprava.z1.model.Z1;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "z1Response", propOrder = {
        "id",
        "podnosilac",
        "punomocnik",
        "zajednickiPredstavnik",
        "status"
})
@XmlRootElement
public class Z1Response {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "podnosilac", required = true)
    private String podnosilac;

    @XmlElement(name = "punomocnik", required = true)
    private String punomocnik;

    @XmlElement(name = "zajednickiPredstavnik", required = true)
    private String zajednickiPredstavnik;

    @XmlElement(name = "status", required = true)
    private String status;

    public Z1Response(){};

    public Z1Response(Z1 z1, long id) {
        this.podnosilac = z1.getPodnosilac().getIme() + " " + z1.getPodnosilac().getPrezime();
        this.punomocnik = z1.getPunomocnik().getIme() + " " + z1.getPunomocnik().getPrezime();
        this.zajednickiPredstavnik = z1.getZajednickiPredstavnik().getIme() + " " + z1.getZajednickiPredstavnik().getPrezime();
        this.status = z1.getStatus();
        this.id = id;
    }

    @Override
    public String toString() {
        return "Z1Response{" +
                "id=" + id +
                ", podnosilac='" + podnosilac + '\'' +
                ", punomocnik='" + punomocnik + '\'' +
                ", zajednickiPredstavnik='" + zajednickiPredstavnik + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

