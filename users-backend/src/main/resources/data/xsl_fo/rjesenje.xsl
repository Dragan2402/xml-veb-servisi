<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://users.com/model/rjesenje"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
                xmlns:xsi="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="rjesenje-page">
                    <fo:region-body margin="0.4in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="rjesenje-page" >
                <fo:flow flow-name="xsl-region-body" >
                    <fo:block-container border="1pt solid black" padding="2%">
                        <fo:block-container border-bottom="1pt solid black">
                            <fo:block text-align="center" text-decoration="underline" margin-top="1%">
                                <fo:inline>
                                    Resenje <xsl:value-of select="b:rjesenje/b:id"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block margin-top="1%">
                                <fo:inline>Sluzbenik: </fo:inline> <fo:inline margin-right="1%"><xsl:value-of select="b:rjesenje/b:ime_sluzbenika"/>  </fo:inline>  <fo:inline><xsl:value-of select="b:rjesenje/b:prezime_sluzbenika"/></fo:inline>
                            </fo:block>
                            <fo:block margin-top="1%" >
                                Tip zahtjeva: <xsl:value-of select="b:rjesenje/b:tip_zahtjeva"/>
                            </fo:block>
                            <fo:block margin-top="1%" >
                                Slucaj: <xsl:value-of select="b:rjesenje/b:slucaj"/>
                            </fo:block>
                            <xsl:if test="b:rjesenje/b:sifra">
                                <fo:block margin-top="1%" >
                                    Sifra dokumenta: <xsl:value-of select="b:rjesenje/b:sifra"/>
                                </fo:block>
                            </xsl:if>
                            <xsl:if test="b:rjesenje/b:obrazlozenje">
                                <fo:block margin-top="1%" >
                                    Obrazlozenje: <xsl:value-of select="b:rjesenje/b:obrazlozenje"/>
                                </fo:block>
                            </xsl:if>
                            <fo:block margin-top="1%" >
                                Referenca zahteva: <xsl:value-of select="b:rjesenje/b:id_zahtjeva"/>
                            </fo:block>
                        </fo:block-container>

                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>