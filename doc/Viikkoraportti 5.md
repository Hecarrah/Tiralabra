Viikkoraportti 5.

Viikolla olen pääasiassa pyrkinyt etsimään koodista vanhentunutta koodia, ja siivoamaan niitä pois. Samalla kun koodia olen penkonut läpi olen yrittänyt saada joitain metodeja toimimaan paremmin. Paransin muunmoassa getNeighbors metodia siten että, kun liikutaan vino suunnissa niin tarkistetaan että ollaanko hyppäämässä kahden vinosti vierekkäin olevan seinän välistä, ja jos ollaan niin palautetaankin null. Tämä oli tarkoitus tehdä jo alunperin mutta oli ilmeisesti jäänyt tekemättä.

Dokumentaation kannalta olen pyrkinyt tarkentamaan määritelmiä toteutusdokumentissa. Tein myös mielenkiinnosta suorituskyky testausta suuremmalla ruudukon resoluutiolla. Eli nostin nodejen lukumäärän 128x128:aan 32x32:stä ja ajoin TestUtilityllä molemmat algoritmit siinä sen 100000 kertaa. Tästä enemmän testausdokumentissa, mutta ajat olivat aika paljon sitä mitä odotinkin. Testin ajamisessa vain meni jo reippaasti aikaa. Yrittänyt myös miettiä millä muilla tavoilla algoritmien suoritusta voisi vertailla kuin yksinkertaisesti ajamalla niitä samassa kentässä ja laskemalla suoritusten keskiarvon.

Tietorakenteiden testikattavuuden sain takaisin 90%. Map oliolla on 100% kattavuus ja Queue:sta puuttuu vain neljä riviä täydestä kattavuudesta, joista kolme on heapify metodin lopussa. Näitä varten tulee vielä kehittää jokunen testimetodi.

Yleisesti mitä ohjelman toimintaa voisi vielä parantaa on mahdollisesti optimoida algoritmien toimintaa, ja ehkä keksiä miten saada käyttöliittymään piirrettyä selkeä polku JPS:n polulle, kun nyt joutuu hieman arvailemaan missä polku menee, kun ei ole yhtä selkeä kuin A* algoritmissa. Toisaalta näin jälkikäteen totesin että käyttöliittymä olisi kannattanut tehdä melko eritavalla sitä varten.

Viikolla käytetty aika noin 12h.