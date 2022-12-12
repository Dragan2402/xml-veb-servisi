package com.euprava.p1.mapper;

import com.euprava.p1.model.TLiceKontakt;
import com.euprava.p1.model.TPodnosilacKontakt;

import java.util.Map;

public class TLiceKontaktMapper {
    private TLiceKontaktMapper() {}


    public static TLiceKontakt map(Map<?,?> tLiceKontaktMap) {
        TLiceKontakt tLiceKontakt = new TLiceKontakt();

        tLiceKontakt.setBrojTelefona((String) tLiceKontaktMap.get("brojTelefona"));
        tLiceKontakt.setEPosta((String) tLiceKontaktMap.get("ePosta"));

        return tLiceKontakt;
    }
}
