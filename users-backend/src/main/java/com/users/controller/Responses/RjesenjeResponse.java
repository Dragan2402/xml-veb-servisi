package com.users.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rjesenjeResponse", propOrder = {
        "id",
        "submitterName",
        "type",
        "submitDate",
        "status"
})
@XmlRootElement
public class RjesenjeResponse {

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

    public RjesenjeResponse(){};

    public RjesenjeResponse(long id,String submitterName, String type, String submitDate, String status) {
        this.id = id;
        this.submitterName = submitterName;
        this.type = type;
        this.submitDate = submitDate;
        this.status = status;
    }
}
