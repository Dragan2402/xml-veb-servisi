package com.euprava.p1.mapper;

import com.euprava.p1.model.Pronalazac;

import java.util.Map;

public class PronalazacMapper {
    private PronalazacMapper() {}


    public static Pronalazac map(Map<?,?> pronalazacMap) {
        Pronalazac pronalazac = new Pronalazac();
        if (pronalazacMap == null) {
            return pronalazac;
        }

        pronalazac.setPronalazacNijeNaveden((boolean) pronalazacMap.get("pronalazacNijeNaveden"));
        pronalazac.setLice(
                TFizickiPodnosilacMapper.map(
                        (Map<?, ?>) pronalazacMap.get("lice")));

        return pronalazac;
    }
}
