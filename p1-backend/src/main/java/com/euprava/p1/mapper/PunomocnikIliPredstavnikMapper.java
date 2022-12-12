package com.euprava.p1.mapper;

import com.euprava.p1.model.PunomocnikIliPredstavnik;

import java.util.Map;

public class PunomocnikIliPredstavnikMapper {
    private PunomocnikIliPredstavnikMapper() {}


    public static PunomocnikIliPredstavnik map(Map<?,?> punomocnikIliPredstavnikMap) {
        PunomocnikIliPredstavnik punomocnikIliPredstavnik = new PunomocnikIliPredstavnik();
        if (punomocnikIliPredstavnikMap == null) {
            return punomocnikIliPredstavnik;
        }

        punomocnikIliPredstavnik.setTip((String) punomocnikIliPredstavnikMap.get("tip"));
        punomocnikIliPredstavnik.setLice(
                TLiceMapper.map(
                        (Map<?, ?>) punomocnikIliPredstavnikMap.get("lice")));

        return punomocnikIliPredstavnik;
    }
}
