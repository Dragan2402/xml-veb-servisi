package com.euprava.p1.mapper;

import com.euprava.p1.model.TPravniPodnosilac;
import com.euprava.p1.model.TPravnoLice;

import java.util.Map;

public class TPravnoLiceMapper {
    private TPravnoLiceMapper() {}

    public static TPravnoLice map(Map<?,?> tPravnoLiceMap) {
        TPravnoLice tPravnoLice = new TPravnoLice();

        tPravnoLice.setAdresa(
                TAdresaUnutarDrzaveMapper.map(
                        (Map<?, ?>) tPravnoLiceMap.get("adresa")));
        tPravnoLice.setKontaktInformacije(
                TLiceKontaktMapper.map(
                        (Map<?, ?>) tPravnoLiceMap.get("kontaktInformacije")));
        tPravnoLice.setPoslovnoIme(
                (String) tPravnoLiceMap.get("poslovnoIme"));

        return tPravnoLice;
    }
}
