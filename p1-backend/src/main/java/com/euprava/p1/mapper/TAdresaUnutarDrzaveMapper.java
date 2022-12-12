package com.euprava.p1.mapper;

import com.euprava.p1.model.TAdresaUnutarDrzave;
import com.euprava.p1.model.TPunaAdresa;

import java.util.Map;

public class TAdresaUnutarDrzaveMapper {
    private TAdresaUnutarDrzaveMapper() {}


    public static TAdresaUnutarDrzave map(Map<?,?> tAdresaUnutarDrzaveMap) {
        TAdresaUnutarDrzave tAdresaUnutarDrzave = new TAdresaUnutarDrzave();
        if (tAdresaUnutarDrzaveMap == null) {
            return tAdresaUnutarDrzave;
        }


        tAdresaUnutarDrzave.setUlica((String) tAdresaUnutarDrzaveMap.get("ulica"));
        tAdresaUnutarDrzave.setBroj((int) tAdresaUnutarDrzaveMap.get("broj"));
        tAdresaUnutarDrzave.setPostanskiBroj((int) tAdresaUnutarDrzaveMap.get("postanskiBroj"));
        tAdresaUnutarDrzave.setMesto((String) tAdresaUnutarDrzaveMap.get("mesto"));

        return tAdresaUnutarDrzave;
    }
}
