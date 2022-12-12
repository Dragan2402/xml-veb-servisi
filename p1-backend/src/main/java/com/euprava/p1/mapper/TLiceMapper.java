package com.euprava.p1.mapper;

import com.euprava.p1.model.TLice;

import java.util.Map;

public class TLiceMapper {
    private TLiceMapper() {}


    public static TLice map(Map<?,?> tLiceMap) {
        if (tLiceMap.containsKey("poslovnoIme")) {
            return TPravnoLiceMapper.map(tLiceMap);
        }

        if (tLiceMap.containsKey("ime") && tLiceMap.containsKey("prezime")) {
            return TFizickoLiceMapper.map(tLiceMap);
        }

        throw new ClassCastException("Invalid child of TLice");
    }
}
