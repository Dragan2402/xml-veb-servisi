xquery version "3.1";
for $document in collection('/db/z1')
    where fn:contains($document//*:Id_Resenja/text(),"%1$s")
    return $document