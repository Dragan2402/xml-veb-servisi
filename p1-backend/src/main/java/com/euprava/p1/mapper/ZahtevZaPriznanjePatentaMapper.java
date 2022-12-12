package com.euprava.p1.mapper;

import com.euprava.p1.model.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Map;

public class ZahtevZaPriznanjePatentaMapper {
    private ZahtevZaPriznanjePatentaMapper() {}

    public static ZahtevZaPriznanjePatenta map(Map<?, ?> zahtevZaPriznanjePatentaMap) throws DatatypeConfigurationException, ParseException {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = new ZahtevZaPriznanjePatenta();

        zahtevZaPriznanjePatenta.setNazivPronalaska(
                NazivPronalaskaMapper.map(
                        (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("nazivPronalaska")));
        zahtevZaPriznanjePatenta.setPodnosilacPrijave(
                PodnosilacPrijaveMapper.map(
                        (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("podnosilacPrijave")));

        if (zahtevZaPriznanjePatentaMap.containsKey("pronalazac")) {
            zahtevZaPriznanjePatenta.setPronalazac(
                    PronalazacMapper.map(
                            (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("pronalazac")));
        }

        if (zahtevZaPriznanjePatentaMap.containsKey("punomocnikIliPredstavnik")) {
            zahtevZaPriznanjePatenta.setPunomocnikIliPredstavnik(
                    PunomocnikIliPredstavnikMapper.map(
                            (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("punomocnikIliPredstavnik")));
        }

        if (zahtevZaPriznanjePatentaMap.containsKey("adresaZaDostavljanje")) {

            zahtevZaPriznanjePatenta.setAdresaZaDostavljanje(
                    TAdresaUnutarDrzaveMapper.map(
                            (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("adresaZaDostavljanje")));
        }

        zahtevZaPriznanjePatenta.setNacinDostavljanja(
                (String) zahtevZaPriznanjePatentaMap.get("nacinDostavljanja"));

        if (zahtevZaPriznanjePatentaMap.containsKey("povezanaPrijava")) {
            zahtevZaPriznanjePatenta.setPovezanaPrijava(
                    PovezanaPrijavaMapper.map(
                            (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("povezanaPrijava")));
        }

        if (zahtevZaPriznanjePatentaMap.containsKey("zahtevZaPriznanjePravaPrvenstva")) {
            zahtevZaPriznanjePatenta.setZahtevZaPriznanjePravaPrvenstva(
                    ZahtevZaPriznanjePravaPrvenstvaMapper.map(
                            (Map<?, ?>) zahtevZaPriznanjePatentaMap.get("zahtevZaPriznanjePravaPrvenstva")));
        }

        return zahtevZaPriznanjePatenta;
    }
}
