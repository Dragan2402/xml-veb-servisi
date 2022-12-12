package com.euprava.p1.mapper;

import com.euprava.p1.model.TPodnosilacKontakt;
import com.euprava.p1.model.TPravniPodnosilac;

import java.util.Map;

public class TPravniPodnosilacMapper {
    private TPravniPodnosilacMapper() {}


    public static TPravniPodnosilac map(Map<?,?> tPravniPodnosilacMap) {
        TPravniPodnosilac tPravniPodnosilac = new TPravniPodnosilac();

        tPravniPodnosilac.setAdresa(
                TPunaAdresaMapper.map(
                    (Map<?, ?>) tPravniPodnosilacMap.get("adresa")));
        tPravniPodnosilac.setKontaktInformacije(
                TPodnosilacKontaktMapper.map(
                        (Map<?, ?>) tPravniPodnosilacMap.get("kontaktInformacije")));
        tPravniPodnosilac.setPoslovnoIme(
                (String) tPravniPodnosilacMap.get("poslovnoIme"));

        return tPravniPodnosilac;
    }
}
