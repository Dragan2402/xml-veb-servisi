package com.users.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codeResponse", propOrder = {
        "code"
})
@XmlRootElement(name = "codeResponse")
public class SifraResponse {
    @XmlElement(name = "code", required = true)
    String code;

    public SifraResponse(){}

    public SifraResponse(String code){
        this.code = code;
    }
}
