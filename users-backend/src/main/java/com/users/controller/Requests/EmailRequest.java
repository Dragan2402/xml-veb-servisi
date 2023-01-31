package com.users.controller.Requests;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "id"
})
@XmlRootElement(name = "emailRequest")
public class EmailRequest {

    @XmlElement(name = "email", required = true)
    private String email;

    @XmlElement(name = "id", required = true)
    private String id;

    public EmailRequest(){}

    public EmailRequest(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

