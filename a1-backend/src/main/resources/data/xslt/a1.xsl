<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://euprava.euprava.com/model/a1Sertifikat"
                version="2.0" >
    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    body { font-family: "Times New Roman";font-size:18; padding-right:10%;padding-left:10%;padding-top:5% }

                    .main-div{
                        display:flex;
                        flex-direction:column;
                        border: 1px solid black;
                    }

                    .title-div{
                        display:flex;
                        flex-direction:column;
                        border-bottom: 1px solid black;
                        align-items:center;
                        width:100%;
                    }

                    .content-div{
                        margin-left:2%;
                        margin-top:2%;
                    }

                    .section-div{
                        text-decoration:underline;
                        margin-bottom:2%;
                    }

                    .end-row{
                        display:flex;
                        flex-direction:row;
                        justify-content:space-between;
                        margin-bottom:5%;
                    }

                    .normal-row{
                        display:flex;
                        flex-direction:row;
                        margin-bottom:2%;
                    }

                    .normal-div{
                        margin-bottom:2%;
                    }

                    .margin-left-div{
                        margin-bottom:2%;
                        margin-left:4%;
                    }

                    .row-div{
                        margin-right:2%;
                    }


                </style>
                <title>Obrazac A1</title>
            </head>
            <body>
                <div class="main-div">
                        <div class="title-div">
                            <div style="display:flex;flex-direction:row; margin-bottom:2%;width:600px;">
                                <div>
                                    ZAVOD ZA INTELEKTUALNU SVOJINU
                                </div>
                                <div style="margin-left:2%;">OBRAZAC A-1</div>
                            </div>
                            <div style="margin-bottom:1%;align-self:start;margin-left:2%">
                                Beograd, Kneginje Ljubice 5
                            </div>
                            <div style="font-size:25;margin-bottom:2%">
                                ZAHTEV ZA UNOSENJE I EVIDENCIJU I DEPONOVANJE AUTORSKIH DJELA
                            </div>
                        </div>
                        <div class="content-div">
                            <div class="section-div">
                                1) Podnosilac
                            </div>
                            <div class="margin-left-div" >
                                <xsl:if test="b:obrazacA1/b:Podnosilac/b:Poslovno_Ime">
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Tip podnosioca: Pravni
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Poslovno ime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Poslovno_Ime"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Telefon:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Telefon"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Email:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Email"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Adresa"/>
                                    </div>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba">
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Tip podnosioca: Fizicki
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Telefon:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Telefon"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Email:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Email"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Ime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Ime"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Prezime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Prezime"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Adresa"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo/b:Jmbg">
                                            JMBG:
                                        </xsl:if>
                                        <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo/b:Broj_Pasosa">
                                            Broj Pasosa:
                                        </xsl:if>
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo"/>
                                    </div>
                                </xsl:if>
                            </div>
                        </div>
                        <xsl:if test="b:obrazacA1/b:Punomocnik">
                            <div class="content-div">
                                <div class="section-div">
                                    1.2) Punomocnik
                                </div>
                                <div class="margin-left-div">
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Ime:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Ime"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Prezime:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Prezime"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Adresa"/>
                                    </div>
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        <xsl:if test="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo/b:Jmbg">
                                            JMBG:
                                        </xsl:if>
                                        <xsl:if test="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo/b:Broj_Pasosa">
                                            Broj Pasosa:
                                        </xsl:if>
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo"/>
                                    </div>
                                </div>
                            </div>
                        </xsl:if>
                        <div class="content-div">
                            <div class="section-div">
                                2) Djelo
                            </div>
                            <div class="margin-left-div">
                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                    Naslov: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Naslov" />
                                </div >
                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                    Vrsta djela: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Vrsta_Djela" />
                                </div>
                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                    Forma zapisa: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Forma_Zapisa" />
                                </div>
                                <xsl:if test="contains(b:obrazacA1/b:Djelo/b:Stvoreno_U_Radnom_Odnosu,'true')">
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Stvoreno u radnom odnosu
                                    </div>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Nacin_koriscenja">
                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                        Nacin koriscenja: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Nacin_koriscenja"/>
                                    </div>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Autor">
                                    <div class="section-div">
                                        2.1) Autor
                                    </div>
                                    <div class="margin-left-div" >
                                        <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Autor/b:Nepoznati_Autor">
                                            Nepoznat autor
                                        </xsl:if>
                                        <xsl:for-each select="b:obrazacA1/b:Djelo/b:Podaci_Autor/b:Poznati_Autor">

                                            <xsl:if test="b:Podaci_Autor">
                                                <div >
                                                    <div class="normal-row" style="border-bottom:1px solid black;width:40%;">
                                                        <div class="row-div">
                                                            <xsl:value-of select="position()"/>.
                                                        </div>
                                                        <div class="row-div">
                                                            <xsl:value-of select="b:Podaci_Autor/b:Ime"/>
                                                        </div>
                                                        <div class="row-div">
                                                            <xsl:value-of select="b:Podaci_Autor/b:Prezime"/>
                                                        </div>
                                                        <xsl:if test="b:Pseudonim_Znak_Autora">
                                                            <div margin-bottom="1%">
                                                                pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                            </div>
                                                        </xsl:if>
                                                    </div>
                                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                        Adresa:
                                                        <xsl:value-of select="b:Podaci_Autor/b:Adresa"/>
                                                    </div>
                                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                        <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Jmbg">
                                                            JMBG:
                                                        </xsl:if>
                                                        <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Broj_Pasosa">
                                                            Broj Pasosa:
                                                        </xsl:if>
                                                        <xsl:value-of select="b:Podaci_Autor/b:Drzavljanstvo"/>
                                                    </div>
                                                </div>
                                            </xsl:if>
                                            <xsl:if test="b:Datum_Smrti">
                                                <div class="normal-row" style="border-bottom:1px solid black;width:40%;">
                                                    <div class="row-div">
                                                        <xsl:value-of select="position()"/>.
                                                    </div>
                                                    <div class="row-div">
                                                        <xsl:value-of select="b:Ime"/>
                                                    </div>
                                                    <div class="row-div">
                                                        <xsl:value-of select="b:Prezime"/>
                                                    </div>
                                                    <xsl:if test="b:Pseudonim_Znak_Autora">
                                                        <div class="row-div">
                                                            pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                        </div>
                                                    </xsl:if>
                                                </div>
                                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                    Datum smrti:
                                                    <xsl:value-of select="b:Datum_Smrti"/>
                                                </div>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo">
                                    <div class="margin-left-div">
                                        <div class="section-div">
                                            2.2) Originalno djelo
                                        </div>
                                        <div class="margin-left-div">
                                            <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                Naslov originalnog djela: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Naslov_Originalnog_Djela" />
                                            </div>
                                            <div class="section-div">
                                                2.2.1) Autor originalnog djela
                                            </div>
                                            <div class="margin-left-div">

                                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Nepoznati_Autor">
                                                    <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                        Nepoznat autor
                                                    </div>
                                                </xsl:if>
                                                <xsl:for-each select="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Poznati_Originalni_Autor">
                                                    <xsl:if test="b:Podaci_Autor">
                                                        <div>
                                                            <div class="normal-row" style="border-bottom:1px solid black;width:40%;">
                                                                <div class="row-div">
                                                                    <xsl:value-of select="position()"/>.
                                                                </div>
                                                                <div class="row-div">
                                                                    <xsl:value-of select="b:Podaci_Autor/b:Ime"/>
                                                                </div>
                                                                <div class="row-div">
                                                                    <xsl:value-of select="b:Podaci_Autor/b:Prezime"/>
                                                                </div>
                                                                <xsl:if test="b:Pseudonim_Znak_Autora">
                                                                    <div class="row-div">
                                                                        pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                                    </div>
                                                                </xsl:if>
                                                            </div>
                                                            <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                                Adresa:
                                                                <xsl:value-of select="b:Podaci_Autor/b:Adresa"/>
                                                            </div>
                                                            <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                                <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Jmbg">
                                                                    JMBG:
                                                                </xsl:if>
                                                                <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Broj_Pasosa">
                                                                    Broj Pasosa:
                                                                </xsl:if>
                                                                <xsl:value-of select="b:Podaci_Autor/b:Drzavljanstvo"/>
                                                            </div>
                                                        </div>
                                                    </xsl:if>
                                                    <xsl:if test="b:Datum_Smrti">
                                                        <div class="normal-row" style="border-bottom:1px solid black;width:40%;">
                                                            <div class="row-div">
                                                                <xsl:value-of select="position()"/>.
                                                            </div>
                                                            <div class="row-div">
                                                                <xsl:value-of select="b:Ime"/>
                                                            </div>
                                                            <div pclass="row-div">
                                                                <xsl:value-of select="b:Prezime"/>
                                                            </div>
                                                            <xsl:if test="b:Pseudonim_Znak_Autora">
                                                                <div class="row-div" >
                                                                    pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                                </div>
                                                            </xsl:if>
                                                        </div>
                                                        <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                                            Datum smrti:
                                                            <xsl:value-of select="b:Datum_Smrti"/>
                                                        </div>
                                                    </xsl:if>
                                                </xsl:for-each>
                                            </div>
                                        </div>
                                    </div>
                                </xsl:if>
                            </div>
                        </div>
                        <div class="content-div">
                            <xsl:if test="b:obrazacA1/b:Sifra_Opisa">
                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                    Sifra opisa: <xsl:value-of select="b:obrazacA1/b:Sifra_Opisa"/>
                                </div>
                            </xsl:if>
                            <xsl:if test="b:obrazacA1/b:Sifra_Primjera">
                                <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                    Sifra primjera: <xsl:value-of select="b:obrazacA1/b:Sifra_Primjera"/>
                                </div>
                            </xsl:if>
                            <div class="normal-div" style="border-bottom:1px solid black;width:40%;">
                                Status: <xsl:value-of select="b:obrazacA1/b:Status"/>
                            </div>
                            <div class="end-row" >
                                <div style="border-bottom:1px solid black;">
                                    Broj prijave: <xsl:value-of select="b:obrazacA1/b:Broj_Prijave"/>
                                </div >
                                <div style="border-bottom:1px solid black;">
                                    Potpis: <xsl:value-of select="b:obrazacA1/b:Potpis"/>
                                </div>
                                <div style="border-bottom:1px solid black;margin-right:1%" >
                                    Datum podnosenja: <xsl:value-of select="b:obrazacA1/b:Datum_Podnosenja"/>
                                </div>
                            </div>
                        </div>

                    </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>