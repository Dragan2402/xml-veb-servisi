package com.euprava.euprava.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "a1response", propOrder = {
        "id",
        "submitterName",
        "type",
        "submitDate",
        "status"
})
@XmlRootElement
public class A1Response {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "submitterName", required = true)
    private String submitterName;

    @XmlElement(name = "type", required = true)
    private String type;

    @XmlElement(name = "submitDate", required = true)
    private String submitDate;

    @XmlElement(name = "status", required = true)
    private String status;

    public A1Response(){};

    public A1Response(long id,String submitterName, String type, String submitDate, String status) {
        this.id = id;
        this.submitterName = submitterName;
        this.type = type;
        this.submitDate = submitDate;
        this.status = status;
    }
}
