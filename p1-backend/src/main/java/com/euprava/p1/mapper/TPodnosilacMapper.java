package com.euprava.p1.mapper;

import com.euprava.p1.model.TPodnosilac;
import com.euprava.p1.model.TPravniPodnosilac;

import java.util.Map;

public class TPodnosilacMapper {
    private TPodnosilacMapper() {}


    public static TPodnosilac map(Map<?,?> tPodnosilacMap) {
        if (tPodnosilacMap.containsKey("poslovnoIme")) {
            return TPravniPodnosilacMapper.map(tPodnosilacMap);
        }

        if (tPodnosilacMap.containsKey("ime") && tPodnosilacMap.containsKey("prezime")) {
            return TFizickiPodnosilacMapper.map(tPodnosilacMap);
        }

        throw new ClassCastException("Invalid child of TPodnosilac");
    }
}
