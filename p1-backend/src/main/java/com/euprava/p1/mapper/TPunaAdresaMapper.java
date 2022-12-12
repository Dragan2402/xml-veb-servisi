package com.euprava.p1.mapper;

import com.euprava.p1.model.TPunaAdresa;

import java.util.Map;

public class TPunaAdresaMapper {
    private TPunaAdresaMapper() {}


    public static TPunaAdresa map(Map<?,?> adresaMap) {
        TPunaAdresa tPunaAdresa = new TPunaAdresa();

        tPunaAdresa.setUlica((String) adresaMap.get("ulica"));
        tPunaAdresa.setBroj((int) adresaMap.get("broj"));
        tPunaAdresa.setPostanskiBroj((int) adresaMap.get("postanskiBroj"));
        tPunaAdresa.setMesto((String) adresaMap.get("mesto"));
        tPunaAdresa.setDrzava((String) adresaMap.get("drzava"));

        return tPunaAdresa;
    }
}
