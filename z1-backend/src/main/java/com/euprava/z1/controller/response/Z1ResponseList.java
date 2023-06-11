package com.euprava.z1.controller.response;



import com.euprava.z1.model.Z1;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Z1.class})
public class Z1ResponseList {

    List<Z1> responses;

    public Z1ResponseList(){responses = new ArrayList<>();}

    public Z1ResponseList(List<Z1> documents){this.responses = documents;}

    @XmlAnyElement
    public List<Z1> getResponses(){return this.responses;}
}
