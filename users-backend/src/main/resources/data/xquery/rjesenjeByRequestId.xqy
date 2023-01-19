xquery version "3.1";
for $document in collection('/db/rjesenje')
    where fn:contains($document//*:id_zahtjeva/text(),"%1$s")
    return $document