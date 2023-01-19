package com.euprava.euprava.controller.Responses;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "numberResponse", propOrder = {
        "podnesenih",
        "odobrenih",
        "odbijenih"
})
@XmlRootElement
public class NumberResponse {

    @XmlElement(name = "podnesenih", required = true)
    private int podnesenih;

    @XmlElement(name = "odobrenih", required = true)
    private int odobrenih;

    @XmlElement(name = "odbijenih", required = true)
    private int odbijenih;

    public NumberResponse(){}

    public NumberResponse(int podnesenih, int odbijenih, int odobrenih) {
        this.podnesenih=podnesenih;
        this.odobrenih= odobrenih;
        this.odbijenih = odbijenih;
    }

}
