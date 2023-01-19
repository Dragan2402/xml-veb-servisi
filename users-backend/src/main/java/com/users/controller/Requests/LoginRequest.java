package com.users.controller.Requests;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "password"
})
@XmlRootElement(name = "login")
public class LoginRequest {

    @XmlElement(name = "email", required = true)
    String email;

    @XmlElement(name = "password", required = true)
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
