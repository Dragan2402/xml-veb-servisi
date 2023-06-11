xquery version "3.1";
for $document in collection('/db/a1')
    where fn:contains($document//*:Id_Klijenta/text(),"%1$s")
    return $document