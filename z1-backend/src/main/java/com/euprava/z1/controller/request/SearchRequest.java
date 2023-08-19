package com.euprava.z1.controller.request;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "param",
})
@XmlRootElement(name = "searchRequest")
public class SearchRequest {

    @XmlElement(name = "param", required = true)
    private String param;

    public SearchRequest(){}

    public SearchRequest(String param){this.param=param;}

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
