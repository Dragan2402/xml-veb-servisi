package com.euprava.p1.mapper;

import com.euprava.p1.model.RanijaPrijava;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Map;

public class RanijaPrijavaMapper {
    private RanijaPrijavaMapper() {}


    public static RanijaPrijava map(Map<?,?> ranijaPrijavaMap) throws DatatypeConfigurationException, ParseException {
        RanijaPrijava ranijaPrijava = new RanijaPrijava();

        ranijaPrijava.setDatumPodnosenjaRanijePrijave(
                XMLGregorianCalendarMapper.map(
                        (String) ranijaPrijavaMap.get("datumPodnosenjaRanijePrijave")));
        ranijaPrijava.setBrojRanijePrijave((String) ranijaPrijavaMap.get("brojRanijePrijave"));
        ranijaPrijava.setOznakaDrzaveIliOrganizacije((String) ranijaPrijavaMap.get("oznakaDrzaveIliOrganizacije"));

        return ranijaPrijava;
    }
}
