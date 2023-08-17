<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [<!ENTITY nbsp "&#160;">]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p1="http://p1.euprava.com/model"
                version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>P1</title>
                <style type="text/css">
                    body {
                        font-family: Arial, Helvetica, sans-serif;
                    }

                    table {
                        margin-left: auto;
                        margin-right: auto;
                        width: 85%;
                    }

                    td, th {
                        padding-left: 10px;
                        font-size: 16px;
                        padding-top: 5px;
                        padding-bottom: 5px;
                        text-align: left;
                    }

                    tr {
                        width: 100%;
                    }

                    td {
                        vertical-align: top;
                        padding: 10px;
                    }

                    table, tr, td, th {
                        border: 1px solid black;
                        border-collapse: collapse;
                    }

                    .padded {
                        padding: 10px;
                    }

                    .flex-container {
                        display: flex;
                    }

                    .flex-child {
                        flex: 1;
                        flex-grow: 2;
                    }

                    .flex-child-sm {
                        flex: 1;
                    }
                </style>
            </head>

            <body>
                <div class="margin-bottom: 40px">
                    <div style="display: inline-block; width: 60%;">
                        <table style="margin-left: 10px; float: left;">
                            <thead>
                                <tr>
                                    <th style="text-align: center" colspan="2">Popunjava zavod</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="padded">
                                        <span style="margin-right: 20px;">Broj prijave</span>
                                        <span style="font-weight: bold; font-size: 20px;">P-<xsl:value-of select="p1:Obrazac_P1/p1:Popunjava_zavod/p1:Broj_prijave"/></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="padded" style="width: 50%">
                                        <span style="margin-top: 10px">Datum prijema:</span><br/>
                                        <span><xsl:value-of select="p1:Obrazac_P1/p1:Popunjava_zavod/p1:Datum_prijema"/></span>
                                    </td>
                                    <td class="padded" style="width: 50%">
                                        <span style="margin-top: 10px">Priznati datum podnošenja:</span><br/>
                                        <span><xsl:value-of select="p1:Obrazac_P1/p1:Popunjava_zavod/p1:Priznati_datum_podnosenja"/></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div style="margin-left: 21px; margin-top: 10px">
                        Republika Srbija<br/>
                        Zavod za intelektualnu svojinu<br/>
                        Kneginje Ljubice broj 5<br/>
                        11000 Beograd
                    </div>
                </div>

                <h3 style="text-align: center;">ZAHTEV ZA PRIZNANJE PATENTA</h3>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th>
                                <strong style="margin-right: 20px">Polje broj I</strong><strong>NAZIV PRONALASKA</strong>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <p style="padding-top: 10px">
                                    <span style="margin-right: 20px;">Na srpskom jeziku:</span>
                                    <span><xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Naziv_pronalaska/p1:Na_srpskom"/></span>
                                </p>
                                <p style="padding-bottom: 10px">
                                    <span style="margin-right: 20px;">Na engleskom jeziku:</span>
                                    <span><xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Naziv_pronalaska/p1:Na_engleskom"/></span>
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table" >
                    <thead>
                        <tr>
                            <th colspan="3">
                                <strong style="margin-right: 20px">Polje broj II</strong>
                                <strong style="margin-right: 20px">PODNOSILAC PRIJAVE</strong>
                                <span style="font-weight: normal;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/@pronalazac='true'">[x]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    Podnosilac prijave je i pronalazač
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="2">
                                <p>Ime i prezime / Poslovno ime:</p>
                                <p style="text-align: center;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Ime">
                                            <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Ime"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Prezime"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Poslovno_ime"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </p>
                            </td>
                            <td rowspan="2">
                                <p style="margin-top: 0; padding-top: 0;">Ulica i broj, poštanski broj, mesto i država:</p>
                                <p style="text-align: center;">
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Adresa/p1:Ulica"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Adresa/p1:Broj"/><br/>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Adresa/p1:Postanski_broj"/><br/>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Adresa/p1:Mesto"/><br/>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Adresa/p1:Drzava"/>
                                </p>
                            </td>
                            <td>
                                Broj telefona:
                                <p>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Kontakt/p1:Broj_telefona"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Broj faksa:
                                <p>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Kontakt/p1:Broj_faksa"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Državljanstvo: <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Drzavljanstvo"/>
                            </td>
                            <td>
                                E-pošta:
                                <p>
                                    <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Podnosilac/p1:Kontakt/p1:E_posta"/>
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th colspan="3">
                                <strong style="margin-right: 20px">Polje broj III</strong>
                                <strong style="margin-right: 20px">PRONALAZAČ</strong>
                                <span style="font-weight: normal;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">[&nbsp;&nbsp;]</xsl:when>
                                        <xsl:otherwise>[x]</xsl:otherwise>
                                    </xsl:choose>
                                    Pronalazač ne želi da bude naveden u prijavi
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="3">
                                <p>Ime i prezime:</p>
                                <p style="text-align: center;">
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Ime"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Prezime"/>
                                    </xsl:if>
                                </p>
                            </td>
                            <td rowspan="3">
                                <p style="margin-top: 0; padding-top: 0;">Ulica i broj, poštanski broj, mesto i država:</p>
                                <p style="text-align: center;">
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Adresa/p1:Ulica"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Adresa/p1:Broj"/><br/>
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Adresa/p1:Postanski_broj"/><br/>
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Adresa/p1:Mesto"/><br/>
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Adresa/p1:Drzava"/>
                                    </xsl:if>
                                </p>
                            </td>
                            <td>
                                Broj telefona:
                                <p>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Kontakt/p1:Broj_telefona"/>
                                    </xsl:if>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Broj faksa:
                                <p>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Kontakt/p1:Broj_faksa"/>
                                    </xsl:if>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                E-pošta:
                                <p>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Pronalazac/p1:Kontakt/p1:E_posta"/>
                                    </xsl:if>
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th colspan="3">
                                <strong style="margin-right: 20px;">Polje broj IV</strong>
                                <span style="margin-right: 20px;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik and p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/@tip='Punomocnik_za_zastupanje'">[X]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    PUNOMOĆNIK ZA ZASTUPANJE
                                </span>
                                <span style="margin-right: 20px;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik and p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/@tip='Punomocnik_za_prijem_pismena'">[X]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    PUNOMOĆNIK ZA PRIJEM PISMENA
                                </span>
                                <span style="margin-right: 20px;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik and p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/@tip='Zajednicki_predstavnik'">[X]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    PUNOMOĆNIK ZA PRIJEM PISMENA
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="2">
                                <p>Ime i prezime / Poslovno ime:</p>
                                <p style="text-align: center;">
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik">
                                        <xsl:choose>
                                            <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Ime">
                                                <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Ime"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Prezime"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Poslovno_ime"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:if>
                                </p>
                            </td>
                            <td rowspan="2">
                                <p style="margin-top: 0; padding-top: 0;">Ulica i broj, poštanski broj i mesto:</p>
                                <p style="text-align: center;">
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Adresa/p1:Ulica"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Adresa/p1:Broj"/><br/>
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Adresa/p1:Postanski_broj"/><br/>
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Adresa/p1:Mesto"/>
                                    </xsl:if>
                                </p>
                            </td>
                            <td>
                                Broj telefona:
                                <p>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Kontakt/p1:Broj_telefona"/>
                                    </xsl:if>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                E-pošta:
                                <p>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Punomocnik/p1:Kontakt/p1:E_posta"/>
                                    </xsl:if>
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th colspan="3">
                                <strong style="margin-right: 20px;">Polje broj V</strong>
                                <strong>ADRESA ZA DOSTAVLJANJE</strong>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <span style="margin-right: 20px;">Ulica i broj, poštanski broj i mesto:</span>
                                <span>
                                    <xsl:if test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Adresa_za_dostavljanje">
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Adresa_za_dostavljanje/p1:Ulica"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Adresa_za_dostavljanje/p1:Broj"/>,&nbsp;
                                        <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Adresa_za_dostavljanje/p1:Postanski_broj"/>&nbsp;<xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Adresa_za_dostavljanje/p1:Mesto"/>
                                    </xsl:if>
                                </span>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th>
                                <strong style="margin-right: 20px;">Polje broj VI</strong>
                                <strong>NAČIN DOSTAVLJANJA</strong>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Nacin_dostavljanja='Elektronska_forma'">[X]</xsl:when>
                                    <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                </xsl:choose>
                                Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena isključivo elektronskim putem u formi
                                elektronskog dokumenta (u ovom slučaju neophodna je registracija na portalu "eUprave")<br/>
                                <xsl:choose>
                                    <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Nacin_dostavljanja='Papirna_forma'">[X]</xsl:when>
                                    <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                </xsl:choose>
                                Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena u papirnoj formi
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th>
                                <strong style="margin-right: 20px;">Polje broj VII</strong>
                                <span style="margin-right: 20px;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava and p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava/@tip='Dopunska_prijava'">[X]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    DOPUNSKA PRIJAVA
                                </span>
                                <span style="margin-right: 20px;">
                                    <xsl:choose>
                                        <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava and p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava/@tip='Izdvojena_prijava'">[X]</xsl:when>
                                        <xsl:otherwise>[&nbsp;&nbsp;]</xsl:otherwise>
                                    </xsl:choose>
                                    IZDVOJENA PRIJAVA
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <span>
                                    Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta: <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava/p1:Broj_prijave"/>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>
                                    Datum podnošenja prvobitne prijave / osnovne prijave: <xsl:value-of select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Povezana_prijava/p1:Datum_podnosenja"/>
                                </span>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="center-table">
                    <thead>
                        <tr>
                            <th colspan="4">
                                <strong style="margin-right: 20px;">Polje broj VIII</strong>
                                <span>ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th style="width: 40%;" colspan="2">Datum podnošenja ranije prijave</th>
                            <th style="width: 30%;">Broj ranije prijave</th>
                            <th style="width: 30%;">Dvoslovna oznaka države, regionalne ili međunarodne organizacije</th>
                        </tr>
                        <xsl:choose>
                            <xsl:when test="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Zahtev_za_priznanje_prava_prvenstva">
                                <xsl:for-each select="p1:Obrazac_P1/p1:Zahtev_za_priznanje_patenta/p1:Zahtev_za_priznanje_prava_prvenstva/p1:Ranija_prijava">
                                    <tr>
                                        <td style="width: 10%;">
                                            <strong><xsl:value-of select="position()"/>.</strong>
                                        </td>
                                        <td style="text-align: center;">
                                            <xsl:value-of select="p1:Datum_podnosenja"/>
                                        </td>
                                        <td style="text-align: center;">
                                            <xsl:value-of select="p1:Broj_prijave"/>
                                        </td>
                                        <td style="text-align: center;">
                                            <xsl:value-of select="@oznaka_drzave"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </xsl:when>

                            <xsl:otherwise>
                                <tr>
                                    <td style="width: 10%;">
                                        <strong><xsl:value-of select="position()+1"/>.</strong>
                                    </td>
                                    <td style="text-align: center;">&nbsp;</td>
                                    <td style="text-align: center;">&nbsp;</td>
                                    <td style="text-align: center;">&nbsp;</td>
                                </tr>
                            </xsl:otherwise>
                        </xsl:choose>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>