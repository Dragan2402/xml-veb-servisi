package com.euprava.p1.mapper;

import com.euprava.p1.model.NazivPronalaska;

import java.util.Map;

public class NazivPronalaskaMapper {
    private NazivPronalaskaMapper() {}

    public static NazivPronalaska map(Map<?,?> nazivPronalaskaMap) {
        NazivPronalaska nazivPronalaska = new NazivPronalaska();

        nazivPronalaska.setNaSrpskom((String) nazivPronalaskaMap.get("naSrpskom"));
        nazivPronalaska.setNaEngleskom((String) nazivPronalaskaMap.get("naEngleskom"));

        return nazivPronalaska;
    }
}
