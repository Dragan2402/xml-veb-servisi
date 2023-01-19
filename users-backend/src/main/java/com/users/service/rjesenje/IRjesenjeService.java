package com.users.service.rjesenje;

import com.users.controller.Responses.CreateRjesenjeResponse;
import com.users.controller.Responses.RjesenjeResponse;
import com.users.model.rjesenje.Rjesenje;
import org.springframework.core.io.ByteArrayResource;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface IRjesenjeService {
    CreateRjesenjeResponse create(Rjesenje rjesenje) throws Exception;

    Rjesenje findById(long id) throws Exception;

    ByteArrayResource getRjesenjeStringByRequestId(long requestId) throws Exception;
}
