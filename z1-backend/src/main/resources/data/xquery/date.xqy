xquery version "3.1";
for $document in collection('/db/a1')
    where xs:date("%1$s") <= xs:date($document//*:Datum_Podnosenja/text()) and xs:date("%2$s") >= xs:date($document//*:Datum_Podnosenja/text())
    return $document