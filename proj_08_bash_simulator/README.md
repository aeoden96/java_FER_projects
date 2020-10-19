#Bash simulator 

Simulator ljuske ,tu su samo neke od primjena (funkcija):  

>rmtree STAZA  
>cptree STAZA1 STAZA2  
>massrename DIR1 DIR2 filter "slika\d+-[^.]+\.jpg"  

>massrename DIR1 DIR2 groups slika(\d+)-([^.]+)\.jpg  

>massrename DIR1 DIR2 show slika(\d+)-([^.]+)\.jpg gradovi-${2}-${1,03}.jpg  
