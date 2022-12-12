package com.euprava.p1.mapper;

import com.euprava.p1.model.PodnosilacPrijave;

import java.util.Map;

public class PodnosilacPrijaveMapper {
    private PodnosilacPrijaveMapper() {}

    public static PodnosilacPrijave map(Map<?,?> podnosilacPrijaveMap) {
        PodnosilacPrijave podnosilacPrijave = new PodnosilacPrijave();

        podnosilacPrijave.setPodnosilacJePronalazac(
                (boolean) podnosilacPrijaveMap.get("podnosilacJePronalazac"));
        podnosilacPrijave.setLice(
                TPodnosilacMapper.map(
                        (Map<?, ?>) podnosilacPrijaveMap.get("lice")));

        return podnosilacPrijave;
    }
}
