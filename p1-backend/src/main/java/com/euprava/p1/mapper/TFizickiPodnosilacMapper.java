package com.euprava.p1.mapper;

import com.euprava.p1.model.TFizickiPodnosilac;

import java.util.Map;

public class TFizickiPodnosilacMapper {
    private TFizickiPodnosilacMapper() {}

    public static TFizickiPodnosilac map(Map<?,?> tFizickiPodnosilacMap) {
        TFizickiPodnosilac tFizickiPodnosilac = new TFizickiPodnosilac();

        tFizickiPodnosilac.setAdresa(
                TPunaAdresaMapper.map(
                        (Map<?, ?>) tFizickiPodnosilacMap.get("adresa")));
        tFizickiPodnosilac.setKontaktInformacije(
                TPodnosilacKontaktMapper.map(
                        (Map<?, ?>) tFizickiPodnosilacMap.get("kontaktInformacije")));
        tFizickiPodnosilac.setIme(
                (String) tFizickiPodnosilacMap.get("ime"));
        tFizickiPodnosilac.setPrezime(
                (String) tFizickiPodnosilacMap.get("prezime"));

        return tFizickiPodnosilac;
    }
}
