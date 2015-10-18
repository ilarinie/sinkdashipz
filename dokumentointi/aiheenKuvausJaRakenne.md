# sinkdashipz

### AIHE : Laivanupotus
Yhden pelaajan laivanupotuspeli, jossa pelaaja pelaa tietokoneen yksinkertaista tekoälyä vastaan. Sääntöinä on alkuperäisen laivanupotuspelin säännöt (Milton Bradley), sillä poikkeuksella, että laivoja ei saa asettaa vierekkäin. Pelaaja yrittää voittaa pelin mahdoillisimman vähillä hutilaukauksilla, jotta loppupisteet ovat mahdollisimman korkeat. Osumasta saa 200 pistettä, hutilaukauksesta menettää 20. Koska peli on yksinpeli tietokonetta vastaan, pelaajan ei tarvitse asettaa omia laivojaan, vaan ne asetetaan automaattisesti joka pelin alussa.

### Käyttäjät: Pelaaja

### Pelaajan toiminnot:

* Uuden pelin aloitus
* Uuden pelin aloitus (vanha peli kesken)
* Ampuminen koordinaatteihin
* High Scorejen tarkastelu
* Vaikeusasteen valinta

###Rakennekuvaus
Game -luokka pitää sisällään pelaajat (käyttäjän ja tekoälyn) ja ampumiseen liittyvät työkalut. Käyttäjät (Actor, AI, Player) pitävät sisällään käyttäjien laivat, sekä toiminallisuuden osumien tarkistukseen. Ship luokka esittää laivaa, joka koostuu sijaintipalasista (Location). ShipCreator -luokalla voi luoda käyttäjille laivoja ja laivastoja, ja se pitää sisällään toiminallisuuden jolla voi estää päällekkäisten laivojen syntymisen.

Pelin kulku: Game -luokka luo pelaajat (Player ja AI) sekä näille laivastot ShipCreator -luokalla. Kun pelaaja ampuu, tarkistetaan osuma. Jos osuma havaitaan, ei tehdä muuta (pelaaja saa ampua uudestaan). Jos osumaa ei havaita, saa AI ampua. AI -luokassa on kohteen määrittämiseen tarvittava toiminallisuus. Onnistuneen ampumisen jälkeen tarkistetaan viholliselle jäljelle jäänyt laivamäärä, ja jos se on 0, on ampunut pelaaja voittanut.
