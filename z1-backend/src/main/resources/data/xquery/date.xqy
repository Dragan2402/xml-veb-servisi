xquery version "3.1";
for $document in collection('/db/z1')
    where xs:date("%1$s") <= xs:date($document//*:Datum/text()) and xs:date("%2$s") >= xs:date($document//*:Datum/text())
    return $document