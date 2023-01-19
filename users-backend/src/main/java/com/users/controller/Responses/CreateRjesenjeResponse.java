package com.users.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createRjesenjeResponse", propOrder = {
        "code",
        "id"
})
@XmlRootElement(name = "createRjesenjeResponse")
public class CreateRjesenjeResponse {
    @XmlElement(name = "code", required = true)
    String code;

    @XmlElement(name = "id", required = true)
    long id;

    public CreateRjesenjeResponse(){}

    public CreateRjesenjeResponse(String code, long id){
        this.code = code;
        this.id = id;
    }
}
