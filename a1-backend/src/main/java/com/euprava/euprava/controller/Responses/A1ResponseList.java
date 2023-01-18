package com.euprava.euprava.controller.Responses;



import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({A1Response.class})
public class A1ResponseList {

    List<A1Response> responses;

    public A1ResponseList(){responses = new ArrayList<>();}

    public A1ResponseList(List<A1Response> documents){this.responses = documents;}

    @XmlAnyElement
    public List<A1Response> getResponses(){return this.responses;}
}
