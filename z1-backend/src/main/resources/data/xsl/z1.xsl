<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [<!ENTITY nbsp "&#160;">]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:z1="http://euprava.com/z1/model"
                version="2.0" >
    <xsl:template match="/">
        <html>
            <head>
                <title>Z1</title>
                <style type="text/css">
                    body {
                    font-family: Arial, Helvetica, sans-serif;
                    }
                    .padded {
                    padding: 10px;
                    }
                    .border-bot {
                    border-bottom: 1px solid black;
                    }
                    .border-r {
                    border-right: 1px solid black;
                    }
                    h1, h2, h3, h4 {
                    text-align: center;
                    }
                    table {
                    width:100%;
                    }
                    table, td, th {
                    border-collapse: collapse;
                    }
                    table td, table th {
                    border: 1px solid black;
                    }
                    table tr:first-child th {
                    border-top: 0;
                    }
                    table tr:last-child td {
                    border-bottom: 0;
                    }
                    table tr td:first-child,
                    table tr th:first-child {
                    border-left: 0;
                    }
                    table tr td:last-child,
                    table tr th:last-child {
                    border-right: 0;
                    }
                    .shifted {
                    margin-top: 40px;
                    }
                    td {
                    padding: 10px;
                    font-size: 16px;
                    }
                    th {
                    font-size: 16px;
                    padding: 10px;
                    }
                    td.name {
                    text-align: center;
                    font-size: 18px;
                    }
                    .aligned-right {
                    text-align: right;
                    padding-right: 20px;
                    }
                    p {
                    margin: 0;
                    }
                </style>
            </head>
            <body>
                <h3 style="margin-top: 20px">ZAHTEV ZA PRIZNANJE ZIGA</h3>
                <h4>Zavod za intelektualnu svojinu, Kneginje Ljubice 5, 11000 Beograd</h4>

                <div style="border-top: 1px solid black; border-right: 1px solid black; border-left: 1px solid black; width: 100%; margin: auto">
                    <div class="border-bot">
                        <p class="border-bot padded">
                            <strong>
                                1. Podnosilac prijave:
                            </strong>
                        </p>
                        <table>
                            <thead>
                                <th style="width: 25%">Ime i prezime</th>
                                <th style="width: 35%">Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="name" rowspan="3" style="border-bottom: 0px">
                                        <xsl:choose>
                                            <xsl:when test="string-length(//z1:Podnosilac/z1:Poslovno_ime) != 0">
                                                <xsl:value-of select="//z1:Podnosilac/z1:Poslovno_ime"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="//z1:Podnosilac/z1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//z1:Podnosilac/z1:Prezime"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td>
                                        <xsl:value-of select="//z1:Podnosilac/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                            select="//z1:Podnosilac/z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel:
                                        <xsl:value-of select="//z1:Podnosilac/z1:Kontakt/z1:Broj_telefona"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Podnosilac/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                            select="//z1:Podnosilac/z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email:
                                        <xsl:value-of select="//z1:Podnosilac/z1:Kontakt/z1:E_posta"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Podnosilac/z1:Adresa/z1:Drzava"/>
                                    </td>
                                    <td>faks:
                                        <xsl:value-of select="//z1:Podnosilac/z1:Kontakt/z1:Broj_faksa"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="border-bot">
                        <p class="border-bot padded">
                            <strong>
                                2. Punomocnik:
                            </strong>
                        </p>
                        <table>
                            <thead>
                                <th style="width: 25%">Ime i prezime</th>
                                <th style="width: 35%">Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="name" rowspan="3" style="border-bottom: 0px">
                                        <xsl:choose>
                                            <xsl:when test="string-length(//z1:Punomocnik/z1:Poslovno_ime) != 0">
                                                <xsl:value-of select="//z1:Punomocnik/z1:Poslovno_ime"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="//z1:Punomocnik/z1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//z1:Punomocnik/z1:Prezime"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td>
                                        <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                            select="//z1:Punomocnik/z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel:
                                        <xsl:value-of select="//z1:Punomocnik/z1:Kontakt/z1:Broj_telefona"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                            select="//z1:Punomocnik/z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email:
                                        <xsl:value-of select="//z1:Punomocnik/z1:Kontakt/z1:E_posta"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Drzava"/>
                                    </td>
                                    <td>faks:
                                        <xsl:value-of select="//z1:Punomocnik/z1:Kontakt/z1:Broj_faksa"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="border-bot">
                        <p class="border-bot padded">
                            <strong>
                                3. Zajednicki predstavnik:
                            </strong>
                        </p>
                        <table>
                            <thead>
                                <th style="width: 25%">Ime i prezime</th>
                                <th style="width: 35%">Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <xsl:choose>
                                        <xsl:when test="string-length(//z1:Zajednicki_predstavnik/z1:Poslovno_ime) != 0">
                                            <td class="name" rowspan="3" style="border-bottom: 0px">
                                                <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Poslovno_ime"/>
                                            </td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td class="name" rowspan="3" style="border-bottom: 0px">
                                                <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//z1:Zajednicki_predstavnik/z1:Prezime"/>
                                            </td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                            select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel:
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Kontakt/z1:Broj_telefona"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of
                                                select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                            select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email:
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Kontakt/z1:E_posta"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Drzava"/>
                                    </td>
                                    <td>faks:
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Kontakt/z1:Broj_faksa"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <table style="width: 100%">
                        <tbody>
                            <tr>
                                <td>
                                    <strong>
                                        4. Prijava se podnosi za:
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <strong>a)</strong>&nbsp;<xsl:value-of select="//z1:Tip_ziga"/> ZIG
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <strong>b)</strong>&nbsp;<xsl:value-of select="//z1:Tip_znaka"/> ZIG
                                </td>
                            </tr>
                            <tr>
                                <td style="border-bottom: 1px solid black">
                                    <strong>
                                        5. Naznacenje boja iz kojih se znak sastoji:
                                    </strong>
                                    <p class="padded">
                                    <xsl:value-of select="//z1:Naznacenje_boje"/>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="padded border-bot">
                        <p>
                            <strong>
                                8. Opis znaka:
                            </strong>
                        </p>
                        <p class="padded">
                            <xsl:value-of select="//z1:Opis_znaka"/>
                        </p>
                    </div>

                    <div class="padded border-bot">
                        <p>
                            <strong>
                                9. Brojevi klasa robe:
                            </strong>
                        </p>
                        <p class="padded">
                            <xsl:for-each select="//z1:Brojevi_klasa_robe/z1:Broj_klase_robe">
                                <xsl:if test="position() > 1">, </xsl:if>
                                <xsl:value-of select="." />
                            </xsl:for-each>
                        </p>
                    </div>

                    <div class="padded border-bot">
                        <p>
                            <strong>
                                10. Zatrazeno pravo prvenstva i osnov:
                            </strong>
                        </p>
                        <p class="padded">
                            <xsl:value-of select="//z1:Pravo_prvenstva"/>
                        </p>
                    </div>
                </div>

                <table class="shifted" style="border: 1px solid black">
                    <thead>
                        <th style="text-align: left" class="padded">
                            <strong>11. Placene takse
                            </strong>
                        </th>
                        <th class="padded">
                            <strong>Dinara</strong>
                        </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="padded">
                                <strong>Osnovna taksa</strong>
                            </td>
                            <td class="aligned-right padded">
                                <xsl:value-of select="//z1:Osnovna_taksa"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="padded">
                                <strong>Graficko resenje</strong>
                            </td>
                            <td class="aligned-right padded">
                                <xsl:value-of select="//z1:Graficko_resenje"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="padded">
                                <strong>Ukupno</strong>
                            </td>
                            <td class="aligned-right padded">
                                <strong>
                                    <xsl:value-of select="//z1:Ukupno"/>
                                </strong>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="shifted" style="border: 1px solid black">
                    <thead>
                        <th colspan="3">
                            <h3>Popunjava zavod</h3>
                        </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <strong>Prilozi uz zahtev</strong>
                            </td>
                            <td rowspan="9">
                                <div style="displat: flex; justify-content: center; align-items: center; text-align: center;">
                                    <div>Broj prijave ziga:</div>
                                    <div><xsl:value-of select="//z1:Broj_prijave"/></div>
                                    <div><strong>Datum podnosenja:</strong></div>
                                    <div><strong><xsl:value-of select="//z1:Datum"/></strong></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Primerak znaka</td>
                            <td>
                                <xsl:value-of select="//z1:Primerak_znaka"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Spisak robe i usluga</td>
                            <td>
                                <xsl:value-of select="//z1:Spisak_robe_i_usluga"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Punomocje</td>
                            <td>
                                <xsl:value-of select="//z1:Punomocje"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Generalno punomocje ranije prilozeno</td>
                            <td>
                                <xsl:value-of select="//z1:Generalno_punomocje"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Punomocje ce biti naknadno dostavljeno</td>
                            <td>
                                <xsl:value-of select="//z1:Punomocje_naknadno_dostavljeno"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Opsti akt o kolektivnom zigu/zigu garancije</td>
                            <td>
                                <xsl:value-of select="//z1:Opsti_akt_o_kolektivnom_zigu"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Dokaz o pravu prvenstva</td>
                            <td>
                                <xsl:value-of select="//z1:Dokaz_o_pravu_prvenstva"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Dokaz o uplati takse</td>
                            <td>
                                <xsl:value-of select="//z1:Dokaz_o_uplati_takse"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
