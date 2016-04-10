Viikkoraportti 2.

Viikolla olen tehnyt jump point searchia ja aloittanut omien metodien tekemistä. Map metodi on tehty korvaamaana HashMap metodi, ja ainakin Astar algoritmin kanssa se toimii ihan oikein, JPS:n kanssa en ole vielä Map metodia kokeillut koska JPS ei vielä toimi täysin oikein.

Ohjelma on edistynyt ihan hyvin. Jump Point Searchin perusidea on kasassa, mutta itse algoritmin toiminnassa on jotain korjaamista.

Viikolla on tullut opittua miten JPS toimii ja miten yksinkertainen Map metodi saadaan tehtyä.

Viikolla selvästi eniten vaikeuksia on tuottanut Jump Point Searchin toteuttaminen. Itse algoritmi näyttäisi toimivan jokseenkin, ainakin jos visualisointiin on uskominen. Ongelmat kuitenkin ilmenevät kun laittaa maali pisteen yksinkertaisen seinän taakse piiloon. Algoritmi näyttäisi kyllä yrittävän etsiä sitä, mutta maali jää silti pois etsityltä alueelta. Mitä nyt olen varsin kauan yrittänyt tarkastella ja säätää arvoja pruneNeighbors ja Jump metodeissa niin joko algoritmi jää aina ikuiseen silmukkaan tai sitten se ei löydä maalia kaikissa tapauksissa.
Huomasin kanssa että algoritmi ei syystä tai toisesta saa myöskään pakotettua naapureita, kun laitoin kyseiseen kohtaan sout("forced") niin ei konsoliin ilmesestynyt tätä lähes kertaakaan. Sain Grid.passable metodia testauksen yhteydessä muutettua hieman paremmaksi, huomasin että Node saattaa joissain tapauksissa olla eri kuin mitä käyttöliittymä väittää, tämän pitäisi olla nyt kuitenkin korjattu. Ei kuitenkaan ratkaissut JPS:n ongelmaa. 
Tarkemmin katsottuna JPS:n ongelma tuntuu olevan siinä että jokainen seinä aiheuttaa "sokean alueen" algoritmille jota ei koskaan löydetä.

Seuraavalla viikolla aion jatkaa JPS toteutusta ja toivottavasti saada sen toimimaan kunnolla jotta voin keskittyä omien metodien luomiseen ja parantamiseen, sekä testaamiseen.

Viikolla käytetty aika noin 30h.