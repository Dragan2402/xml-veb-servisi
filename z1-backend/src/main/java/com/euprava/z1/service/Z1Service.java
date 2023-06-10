package com.euprava.z1.service;

import com.euprava.z1.model.Z1;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Z1Service {
    Z1 getZ1(String fileName) throws JAXBException, IOException;
}
