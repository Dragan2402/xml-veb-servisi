package com.euprava.p1.mapper;

import com.euprava.p1.model.PovezanaPrijava;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Map;

public class PovezanaPrijavaMapper {
    private PovezanaPrijavaMapper() {}


    public static PovezanaPrijava map(Map<?,?> povezanaPrijavaMap) throws DatatypeConfigurationException, ParseException {
        PovezanaPrijava povezanaPrijava = new PovezanaPrijava();
        if (povezanaPrijavaMap == null) {
            return povezanaPrijava;
        }

        povezanaPrijava.setTip(
                (String) povezanaPrijavaMap.get("tip"));
        povezanaPrijava.setBrojRanijePrijave(
                (String) povezanaPrijavaMap.get("brojRanijePrijave"));
        povezanaPrijava.setDatumPodnosenjaRanijePrijave(
                XMLGregorianCalendarMapper.map(
                        (String) povezanaPrijavaMap.get("datumPodnosenjaRanijePrijave")));

        return povezanaPrijava;
    }
}
