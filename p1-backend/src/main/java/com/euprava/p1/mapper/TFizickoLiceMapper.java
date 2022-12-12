package com.euprava.p1.mapper;

import com.euprava.p1.model.TFizickoLice;
import com.euprava.p1.model.TLice;
import com.euprava.p1.model.TPravnoLice;

import java.util.Map;

public class TFizickoLiceMapper {
    private TFizickoLiceMapper() {}


    public static TFizickoLice map(Map<?,?> tFizickoLiceMap) {
        TFizickoLice tFizickoLice = new TFizickoLice();

        tFizickoLice.setAdresa(
                TAdresaUnutarDrzaveMapper.map(
                        (Map<?, ?>) tFizickoLiceMap.get("adresa")));
        tFizickoLice.setKontaktInformacije(
                TLiceKontaktMapper.map(
                        (Map<?, ?>) tFizickoLiceMap.get("kontaktInformacije")));
        tFizickoLice.setIme(
                (String) tFizickoLiceMap.get("ime"));
        tFizickoLice.setPrezime(
                (String) tFizickoLiceMap.get("prezime"));

        return tFizickoLice;
    }
}
