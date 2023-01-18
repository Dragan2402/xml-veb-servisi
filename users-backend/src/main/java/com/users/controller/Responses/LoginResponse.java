package com.users.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
        "id",
        "rola",
        "email"
})
@XmlRootElement(name = "login")
public class LoginResponse {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "rola", required = true)
    private String rola;

    @XmlElement(name = "email", required = true)
    private String email;

    public LoginResponse(){};

    public LoginResponse(long id, String rola, String email){
        this.id=id;
        this.rola=rola;
        this.email=email;
    }
}
