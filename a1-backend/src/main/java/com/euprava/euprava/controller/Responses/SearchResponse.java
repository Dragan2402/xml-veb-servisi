package com.euprava.euprava.controller.Responses;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ObrazacA1.class})
public class SearchResponse {

    private List<ObrazacA1> documents;

    public SearchResponse(){documents = new ArrayList<>();}

    public SearchResponse(List<ObrazacA1> documents){this.documents = documents;}

    @XmlAnyElement
    public List<ObrazacA1> getDocuments(){return this.documents;}
}
