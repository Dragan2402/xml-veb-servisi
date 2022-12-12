package com.euprava.p1.mapper;

import com.euprava.p1.model.ObrazacP1;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Map;

public class ObrazacP1Mapper {
    private ObrazacP1Mapper() {}

    public static ObrazacP1 map(Map<?, ?> obrazacP1Map) throws DatatypeConfigurationException, ParseException {
        ObrazacP1 obrazacP1 = new ObrazacP1();

        obrazacP1.setPopunjavaZavod(PopunjavaZavodMapper.map((Map<?, ?>) obrazacP1Map.get("popunjavaZavod")));
        obrazacP1.setZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatentaMapper.map((Map<?, ?>) obrazacP1Map.get("zahtevZaPriznanjePatenta")));

        return obrazacP1;
    }
}
