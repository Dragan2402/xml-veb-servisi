package com.users.controller.Responses;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
        "id",
        "rola",
        "email",
        "firstName",
        "lastName"
})
@XmlRootElement(name = "login")
public class LoginResponse {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "rola", required = true)
    private String rola;

    @XmlElement(name = "email", required = true)
    private String email;

    @XmlElement(name = "firstName", required = true)
    private String firstName;

    @XmlElement(name = "lastName", required = true)
    private String lastName;

    public LoginResponse(){};

    public LoginResponse(long id, String rola, String email, String firstName, String lastName){
        this.id=id;
        this.rola=rola;
        this.email=email;
        this.firstName =firstName;
        this.lastName = lastName;
    }
}
