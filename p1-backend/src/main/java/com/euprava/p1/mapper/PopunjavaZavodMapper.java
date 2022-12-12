package com.euprava.p1.mapper;

import com.euprava.p1.model.PopunjavaZavod;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Map;

public class PopunjavaZavodMapper {
    private PopunjavaZavodMapper() {}

    public static PopunjavaZavod map(Map<?, ?> popunjavaZavodMap) throws DatatypeConfigurationException, ParseException {
        PopunjavaZavod popunjavaZavod =  new PopunjavaZavod();

        popunjavaZavod.setBrojPrijave(
                (String) popunjavaZavodMap.get("brojPrijave"));

        popunjavaZavod.setDatumPrijema(
                XMLGregorianCalendarMapper.map(
                        (String) popunjavaZavodMap.get("datumPrijema")));
        popunjavaZavod.setPriznatiDatumPodnosenja(
                XMLGregorianCalendarMapper.map(
                        (String) popunjavaZavodMap.get("priznatiDatumPodnosenja")));
        return popunjavaZavod;
    }
}
