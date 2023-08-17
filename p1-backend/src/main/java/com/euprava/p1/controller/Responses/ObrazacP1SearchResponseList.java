package com.euprava.p1.controller.Responses;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ObrazacP1SearchResponse.class})
public class ObrazacP1SearchResponseList {
    List<ObrazacP1SearchResponse> searchResponses;

    public ObrazacP1SearchResponseList() {
        searchResponses = new ArrayList<>();
    }

    public ObrazacP1SearchResponseList(List<ObrazacP1SearchResponse> searchResponses) {
        this.searchResponses = searchResponses;
    }

    @XmlAnyElement
    public List<ObrazacP1SearchResponse> getSearchResponses() {
        return searchResponses;
    }
}
