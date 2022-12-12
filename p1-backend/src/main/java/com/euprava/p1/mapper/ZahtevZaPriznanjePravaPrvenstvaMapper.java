package com.euprava.p1.mapper;

import com.euprava.p1.model.RanijaPrijava;
import com.euprava.p1.model.ZahtevZaPriznanjePravaPrvenstva;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class ZahtevZaPriznanjePravaPrvenstvaMapper {
    private ZahtevZaPriznanjePravaPrvenstvaMapper() {}


    public static ZahtevZaPriznanjePravaPrvenstva map(Map<?, ?> zahtevZaPriznanjePravaPrvenstvaMap) throws DatatypeConfigurationException, ParseException {
        ZahtevZaPriznanjePravaPrvenstva zahtevZaPriznanjePravaPrvenstva = new ZahtevZaPriznanjePravaPrvenstva();
        if (zahtevZaPriznanjePravaPrvenstvaMap == null) {
            return zahtevZaPriznanjePravaPrvenstva;
        }

        List<?> ranijaPrijavaList = (List<?>) zahtevZaPriznanjePravaPrvenstvaMap.get("ranijaPrijava");
        for (Object ranijaPrijavaListItem : ranijaPrijavaList) {
            Map<?, ?> ranijaPrijavaMap = (Map<?, ?>) ranijaPrijavaListItem;
            RanijaPrijava ranijaPrijava = RanijaPrijavaMapper.map(ranijaPrijavaMap);
            zahtevZaPriznanjePravaPrvenstva.getRanijaPrijava().add(ranijaPrijava);
        }

        return zahtevZaPriznanjePravaPrvenstva;
    }
}
