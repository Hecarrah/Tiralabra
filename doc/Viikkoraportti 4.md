Viikkoraportti 4

Viikolla olen aloittanut optimoimaan koodia, ja siivonnut koodista jonkin verran vanhentunutta, ja käyttämätöntä koodia pois. Samalla myös pilkoin joitain pidempiä metodeja, siinämäärin missä se tuntui vielä kannattavalta tehdä.

Queue olion perustoiminnalisuuden tein uusiksi, Queue olio toimii nyt keko periaattella, jonka tulisi nostaa tämän tehokkuutta melko paljon, huomasin myös että nykyisellä Queue:n toteutuksella Astar algoritmi ei jää jumiin samoissa tilanteissa missä se silloin tällöin tuppasi.

Map olion hashCode metodi toimii nyt javan HashCode kutsulla, josta otetaan yksinkertaisesti vain modulo.

Suurin muutos JumpPointSearchin koodissa on se että PruneNeighbors metodi on nyt jaettuna neljään, ensin on itse pruneNeighbors, ja sitten selkeyden vuoksi getDiagonals, getHorizontal, ja getVertical metodit ovat luotu, ja nämä toteuttavat sen osion alkuperäisestä metodista joissa tarkistetaan onko tietty node mahdollinen kohde, ja jos on niin tallennetaan se neighbors taulukkoon.

Itse ohjelman toiminnan ulkopuolelle tein yksinkertaisen TestUtility käyttöliittymän jonka tarkoituksena on yksinkertaistaa testaamista, ja tarkemmin saada laskettua tilastollisesti pätevä keskiarvo algoritmien suoritusnopeudesta. TestUtility:llä voidaan ajaa A* ja JPS algoritmit 100000 kertaa jonka jälkeen käyttöliittymä tulostaa näiden ajojen suoritusaikojen keskiarvon konsoliin. Koska algoritmit ajetaan niin monta kertaa peräkkäin ilman erillisiä taukoja, niin graafinen käyttöliittymä ehtii päivittyä vasta kun algoritmit ovat suoritetttu. Tämä puolestaan taas johtaa siihen että algoritmit tulee ajettua ns. päättömässä tilassa, jolloin graafinen käyttöliittymä ei myöskään hidasta algoritmien toimintaa, ja täten saadaan tarkempi suorituskyky arvio algoritmien todellisesta nopeudesta, koska Astar muunmoassa käyttää huomattavasti enemmän aikaa käyttöliittymä päivityksiin kuin JPS.

Myös testaus- ja toteutusdokumentaatiota on kirjoitettu viikon aikana. Testausdokumentissa on tehty suoritukykytestausta erilaisilla syötteillä, ja ajettu molemmat algoritmit TestUtilityn avulla moneen kertaan samalle syötteelle.

JUnit testikattavuuden sain nostettua 93%:iin algoritmien osalta, ja samaan 93% tietorakenteiden osalta. JUnit testauksista puuttuu enään käytännössä jokunen harvinainen tilanne että jotain menee rikki, tai että polkua ei löytynyt tietyssä kohdassa.

Aikaa viikolla on käytetty noin 18h.