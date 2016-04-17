Viikkoraportti 3

Viikolla olen refaktoroinut koko koodin uudelleen maven projektiksi. Alun perin projekti oli vapaamuotoinen javaprojekti. Tämä tapahtui kuitenkin melko helposti luomalla uusi projekti ja kopioimalla kaikki luokat uuteen projektiin jonka jälkeen siirsin vanhan projektin vain pois tieltä github reposta. Aloitin myös toteuttamaan testaus- ja toteutus dokumentaatiota.

Itse koodissa olen tehnyt myös kohtuullisesti refaktorointia, aloitin korjaamaan aikaisemmin tehtyjä valintoja toteuttamaan tarkemmin single-responsibility periaatetta ja siirsin mahdollisimman algoritmeissa olleita kehitysvaiheen käyttöliittymä metodeja Grid olioon. Samalla tuli yhdistettyä colorNodes ja DrawPath A* ja JPS algoritmeille.
Testejä olen viilannut melko paljon ja tein myös käyttöliittymälle Robot testin joka siis simuloi javan robotilla käyttäjän syötettä, jostain syystä Pit kylläkin väittää että kyseisessä metodissa ei ole kattavuutta. Tulen aika varmasti laajentamaan vastaavaa testausta vielä pidemmälle jatkossa.

JumpPointSearchin sain toimimaan jokseenkin, ainakin löytää nyt maalin paikoista joista ei viime viikolla vielä osannut hakea.
Omien metodien toteutusta on jatkettu ja Map olio on toteutettu hash-tyyliseksi, joten sen pitäisi toimia vakioajassa, lukuunottamatta itse hash-koodin muodostamista, jossa ei yleisesti ottaen kuuluisi mennä paljoa mitään aikaa vaikka toimiikin lineaarisesti. Omista luokista on toteutettu myös Queue korvaamaan javan PriorityQueue olion. Queue olio tuntuu toimivn melko hyvin. Astar algoritmin ajaminen paljastaa kuitenkin aina silloin tällöin ongelman queue:n "priority" osuuden toiminnallisuudessa, mutta ydintoiminta toimii kuitenkin niinkuin sen tuleekin.

Seuraavalla viikolla aion jatkaa Queue metodin viilaamista sekä toteuttaa vielä kattavempaa testausta ohjelmistolle. Erityisesti empiirisellä tasolla, ja miksei myös JUnit testeillä. Seuraavalla viikolla jatkan myös toteutus- ja testausdokumentin laatimista.

Viikolla käytetty aikaa noin 20h
