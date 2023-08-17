package com.euprava.z1.controller.response;



import com.euprava.z1.model.Z1;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Z1Response.class})
public class Z1ResponseList {

    List<Z1Response> responses;

    public Z1ResponseList(){responses = new ArrayList<>();}

    public Z1ResponseList(List<Z1Response> list){this.responses = list;}

    @XmlAnyElement
    public List<Z1Response> getResponses(){return this.responses;}

    @Override
    public String toString() {
        return "Z1ResponseList{" +
                "responses=" + responses +
                '}';
    }
}
