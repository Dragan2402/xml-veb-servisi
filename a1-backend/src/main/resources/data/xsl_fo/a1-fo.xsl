<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://euprava.euprava.com/model/a1Sertifikat"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
                xmlns:xsi="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="obrazac-a1-page">
                    <fo:region-body margin="0.4in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="obrazac-a1-page" >
                <fo:flow flow-name="xsl-region-body" >
                    <fo:block-container border="1pt solid black" padding="2%">
                        <fo:block-container border-bottom="1pt solid black">
                            <fo:block text-align="center" text-decoration="underline" margin-top="1%">
                                <fo:inline>
                                    ZAVOD ZA INTELEKTUALNU SVOJINU
                                </fo:inline>
                                <fo:inline margin-left="5%">OBRAZAC A-1</fo:inline>
                            </fo:block>
                            <fo:block margin-top="1%">
                                Beograd, Kneginje Ljubice 5
                            </fo:block>
                            <fo:block text-align="center" text-decoration="underline" margin-top="2%" margin-bottom="5%" >
                                ZAHTEV ZA UNOSENJE I EVIDENCIJU I DEPONOVANJE AUTORSKIH DJELA
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container margin-top="2%">
                            <fo:block text-decoration="underline" margin-bottom="1%">
                                1) Podnosilac
                            </fo:block>
                            <fo:block margin-left="4%" margin-bottom="1%">
                                <xsl:if test="b:obrazacA1/b:Podnosilac/b:Poslovno_Ime">
                                    Tip podnosioca: Pravni
                                    <fo:inline margin-bottom="1%">
                                        Poslovno ime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Poslovno_Ime"/>
                                    </fo:inline>
                                    <fo:inline margin-bottom="1%">
                                        Telefon:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Telefon"/>
                                    </fo:inline>
                                    <fo:inline margin-bottom="1%">
                                        Email:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Email"/>
                                    </fo:inline>
                                    <fo:inline margin-bottom="1%">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Adresa"/>
                                    </fo:inline>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba">
                                    Tip podnosioca: Fizicki
                                    <fo:block margin-bottom="1%">
                                        Telefon:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Telefon"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Email:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Email"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Ime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Ime"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Prezime:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Prezime"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Adresa"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo/b:Jmbg">
                                            JMBG:
                                        </xsl:if>
                                        <xsl:if test="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo/b:Broj_Pasosa">
                                            Broj Pasosa:
                                        </xsl:if>
                                        <xsl:value-of select="b:obrazacA1/b:Podnosilac/b:Podaci_Osoba/b:Drzavljanstvo"/>
                                    </fo:block>
                                </xsl:if>
                            </fo:block>
                        </fo:block-container>
                        <xsl:if test="b:obrazacA1/b:Punomocnik">
                            <fo:block-container>
                                <fo:block margin-bottom="1%" text-decoration="underline">
                                    1.2) Punomocnik
                                </fo:block>
                                <fo:block margin-bottom="1%">
                                    <fo:block margin-bottom="1%">
                                        Ime:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Ime"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Prezime:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Prezime"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        Adresa:
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Adresa"/>
                                    </fo:block>
                                    <fo:block margin-bottom="1%">
                                        <xsl:if test="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo/b:Jmbg">
                                            JMBG:
                                        </xsl:if>
                                        <xsl:if test="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo/b:Broj_Pasosa">
                                            Broj Pasosa:
                                        </xsl:if>
                                        <xsl:value-of select="b:obrazacA1/b:Punomocnik/b:Drzavljanstvo"/>
                                    </fo:block>
                                </fo:block>
                            </fo:block-container>
                        </xsl:if>
                        <fo:block-container margin-top="2%">
                            <fo:block text-decoration="underline" margin-bottom="1%" margin-top="2%">
                                2) Djelo
                            </fo:block>
                            <fo:block margin-left="4%">
                                <fo:block margin-bottom="1%">
                                    Naslov: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Naslov" />
                                </fo:block >
                                <fo:block margin-bottom="1%">
                                    Vrsta djela: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Vrsta_Djela" />
                                </fo:block>
                                <fo:block margin-bottom="1%">
                                    Forma zapisa: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Forma_Zapisa" />
                                </fo:block>
                                <xsl:if test="contains(b:obrazacA1/b:Djelo/b:Stvoreno_U_Radnom_Odnosu,'true')">
                                    <fo:block margin-bottom="1%">
                                        Stvoreno u radnom odnosu
                                    </fo:block>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Nacin_koriscenja">
                                    <fo:block margin-bottom="1%">
                                        Nacin koriscenja: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Nacin_koriscenja"/>
                                    </fo:block>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Autor">
                                    <fo:block text-decoration="underline" margin-left="2%" margin-bottom="1%">
                                        2.1) Autor
                                    </fo:block>
                                    <fo:block margin-bottom="1%" margin-left="4%">
                                        <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Autor/b:Nepoznati_Autor">
                                            Nepoznat autor
                                        </xsl:if>
                                        <xsl:for-each select="b:obrazacA1/b:Djelo/b:Podaci_Autor/b:Poznati_Autor">

                                            <xsl:if test="b:Podaci_Autor">
                                                <fo:block >
                                                    <fo:block margin-bottom="1%">
                                                        <fo:inline padding-right="2%">
                                                            <xsl:value-of select="position()"/>.
                                                        </fo:inline>
                                                        <fo:inline padding-right="1%">
                                                            <xsl:value-of select="b:Podaci_Autor/b:Ime"/>
                                                        </fo:inline>
                                                        <fo:inline padding-right="2%">
                                                            <xsl:value-of select="b:Podaci_Autor/b:Prezime"/>
                                                        </fo:inline>
                                                        <xsl:if test="b:Pseudonim_Znak_Autora">
                                                            <fo:inline margin-bottom="1%">
                                                                pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                            </fo:inline>
                                                        </xsl:if>
                                                    </fo:block>
                                                    <fo:block margin-bottom="1%" margin-left="4%">
                                                        Adresa:
                                                        <xsl:value-of select="b:Podaci_Autor/b:Adresa"/>
                                                    </fo:block>
                                                    <fo:block margin-bottom="2%" margin-left="4%">
                                                        <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Jmbg">
                                                            JMBG:
                                                        </xsl:if>
                                                        <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Broj_Pasosa">
                                                            Broj Pasosa:
                                                        </xsl:if>
                                                        <xsl:value-of select="b:Podaci_Autor/b:Drzavljanstvo"/>
                                                    </fo:block>
                                                </fo:block>
                                            </xsl:if>
                                            <xsl:if test="b:Datum_Smrti">
                                                <fo:block margin-bottom="1%">
                                                    <fo:inline padding-right="2%">
                                                        <xsl:value-of select="position()"/>.
                                                    </fo:inline>
                                                    <fo:inline padding-right="1%">
                                                        <xsl:value-of select="b:Ime"/>
                                                    </fo:inline>
                                                    <fo:inline padding-right="2%">
                                                        <xsl:value-of select="b:Prezime"/>
                                                    </fo:inline>
                                                    <xsl:if test="b:Pseudonim_Znak_Autora">
                                                        <fo:inline margin-bottom="1%" margin-left="4%">
                                                            pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                        </fo:inline>
                                                    </xsl:if>
                                                </fo:block>
                                                <fo:block margin-bottom="2%" margin-left="4%">
                                                    Datum smrti:
                                                    <xsl:value-of select="b:Datum_Smrti"/>
                                                </fo:block>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:block>
                                </xsl:if>
                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo">
                                    <fo:block margin-left="2%" margin-bottom="1%">
                                        <fo:block text-decoration="underline" margin-bottom="1%">
                                            2.2) Originalno djelo
                                        </fo:block>
                                        <fo:block margin-left="4%">
                                            <fo:block margin-bottom="1%">
                                                Naslov originalnog djela: <xsl:value-of select="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Naslov_Originalnog_Djela" />
                                            </fo:block>
                                            <fo:block text-decoration="underline" margin-left="2%" margin-bottom="1%">
                                                2.2.1) Autor originalnog djela
                                            </fo:block>
                                            <fo:block margin-left="4%" margin-bottom="1%">

                                                <xsl:if test="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Nepoznati_Autor">
                                                    <fo:block margin-bottom="1%">
                                                        Nepoznat autor
                                                    </fo:block>
                                                </xsl:if>
                                                <xsl:for-each select="b:obrazacA1/b:Djelo/b:Podaci_Originalno_Djelo/b:Poznati_Originalni_Autor">
                                                    <xsl:if test="b:Podaci_Autor">
                                                        <fo:block>
                                                            <fo:block margin-bottom="1%">
                                                                <fo:inline padding-right="2%">
                                                                    <xsl:value-of select="position()"/>.
                                                                </fo:inline>
                                                                <fo:inline padding-right="1%">
                                                                    <xsl:value-of select="b:Podaci_Autor/b:Ime"/>
                                                                </fo:inline>
                                                                <fo:inline padding-right="2%">
                                                                    <xsl:value-of select="b:Podaci_Autor/b:Prezime"/>
                                                                </fo:inline>
                                                                <xsl:if test="b:Pseudonim_Znak_Autora">
                                                                    <fo:inline margin-bottom="1%">
                                                                        pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                                    </fo:inline>
                                                                </xsl:if>
                                                            </fo:block>
                                                            <fo:block margin-bottom="1%">
                                                                Adresa:
                                                                <xsl:value-of select="b:Podaci_Autor/b:Adresa"/>
                                                            </fo:block>
                                                            <fo:block margin-bottom="2%" >
                                                                <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Jmbg">
                                                                    JMBG:
                                                                </xsl:if>
                                                                <xsl:if test="b:Podaci_Autor/b:Drzavljanstvo/b:Broj_Pasosa">
                                                                    Broj Pasosa:
                                                                </xsl:if>
                                                                <xsl:value-of select="b:Podaci_Autor/b:Drzavljanstvo"/>
                                                            </fo:block>
                                                        </fo:block>
                                                    </xsl:if>
                                                    <xsl:if test="b:Datum_Smrti">
                                                        <fo:block margin-bottom="1%">
                                                            <fo:inline padding-right="2%">
                                                                <xsl:value-of select="position()"/>.
                                                            </fo:inline>
                                                            <fo:inline padding-right="1%">
                                                                <xsl:value-of select="b:Ime"/>
                                                            </fo:inline>
                                                            <fo:inline padding-right="2%">
                                                                <xsl:value-of select="b:Prezime"/>
                                                            </fo:inline>
                                                            <xsl:if test="b:Pseudonim_Znak_Autora">
                                                                <fo:block margin-bottom="1%" margin-left="4%">
                                                                    pza.-<xsl:value-of select="b:Pseudonim_Znak_Autora"/>
                                                                </fo:block>
                                                            </xsl:if>
                                                        </fo:block>
                                                        <fo:block margin-bottom="2%" >
                                                            Datum smrti:
                                                            <xsl:value-of select="b:Datum_Smrti"/>
                                                        </fo:block>
                                                    </xsl:if>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
                                </xsl:if>
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container >
                            <xsl:if test="b:obrazacA1/b:Sifra_Opisa">
                                <fo:block margin-bottom="1%" margin-top="3%">
                                    Sifra opisa: <xsl:value-of select="b:obrazacA1/b:Sifra_Opisa"/>
                                </fo:block>
                            </xsl:if>
                            <xsl:if test="b:obrazacA1/b:Sifra_Primjera">
                                <fo:block margin-bottom="1%">
                                    Sifra primjera: <xsl:value-of select="b:obrazacA1/b:Sifra_Primjera"/>
                                </fo:block>
                            </xsl:if>
                            <fo:block margin-bottom="1%">
                                Status: <xsl:value-of select="b:obrazacA1/b:Status"/>
                            </fo:block>
                            <fo:block text-align="center">
                                <xsl:if test="b:obrazacA1/b:Broj_Prijave">
                                    <fo:inline>
                                        Broj prijave: <xsl:value-of select="b:obrazacA1/b:Broj_Prijave"/>
                                    </fo:inline>
                                </xsl:if>
                                <fo:inline padding-left="10%" padding-right="10%">
                                    Potpis: <xsl:value-of select="b:obrazacA1/b:Potpis"/>
                                </fo:inline>
                                <fo:inline >
                                    Datum podnosenja: <xsl:value-of select="b:obrazacA1/b:Datum_Podnosenja"/>
                                </fo:inline>
                            </fo:block>
                        </fo:block-container>

                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>