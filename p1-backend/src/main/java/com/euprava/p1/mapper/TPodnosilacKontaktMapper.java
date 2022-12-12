package com.euprava.p1.mapper;

import com.euprava.p1.model.TPodnosilacKontakt;

import java.util.Map;

public class TPodnosilacKontaktMapper {
    private TPodnosilacKontaktMapper() {}

    public static TPodnosilacKontakt map(Map<?, ?> tPodnosilacKontaktMap) {
        TPodnosilacKontakt tPodnosilacKontakt = new TPodnosilacKontakt();

        tPodnosilacKontakt.setBrojTelefona((String) tPodnosilacKontaktMap.get("brojTelefona"));
        tPodnosilacKontakt.setEPosta((String) tPodnosilacKontaktMap.get("ePosta"));
        tPodnosilacKontakt.setBrojFaksa((String) tPodnosilacKontaktMap.get("brojFaksa"));

        return tPodnosilacKontakt;
    }
}
