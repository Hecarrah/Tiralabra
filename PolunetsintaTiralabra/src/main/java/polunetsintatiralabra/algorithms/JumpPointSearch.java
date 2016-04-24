package polunetsintatiralabra.algorithms;

import polunetsintatiralabra.dataStructures.Queue;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

/**
 * @author Peter
 */
public class JumpPointSearch {

    Node[] neighbors = new Node[8];
    float ng;
    Node cur;
    Node start;
    Node end;
    Node[] successors, possibleSuccess;
    private final Queue<Node> frontier = new Queue<Node>();
    private final boolean[][] visited = new boolean[128][128];

    /**
     * Käydään läpi mahdolliset sijainnit
     *
     * @param s Alkupiste.
     * @param e Loppupiste.
     * @return palautetaan tosi jos maali löytyi, false muuten.
     */
    public boolean search(Node s, Node e) {
        end = e;
        start = s;
        Grid.flush();
        frontier.add(start);
        frontier.add(start);
        if (cur == end) {
            return true;
        }
        while (true) { //jos nykyinen node ei ole vielä maali.
            cur = frontier.poll();
            possibleSuccess = identifySuccessors(cur);
            if (possibleSuccess == null) {
                break;
            }

            iteratePossibilities();

            if (frontier.isEmpty()) {  //maalia ei löytynyt
                //System.out.println("No Path Found");
                break;
            }
            if (cur == end) { //maali löytyi
                //System.out.println("Path Found");
                Grid.drawPath(cur);
                break;
            }
        }
        return false;
    }

    private void iteratePossibilities() {
        for (Node possibleSucces : possibleSuccess) {//käydään läpi kaikki mahdolliset sijainnit
            Grid.updateLabel(possibleSucces);
            if (possibleSucces != null) {
                frontier.add(possibleSucces);
                if (possibleSucces == end) {// maali löytyi
                    cur = end;
                    break;
                }
                Grid.colorNodes(possibleSucces); //väritetään käydyt nodet
            }
        }
    }

    /**
     * haetaan kaikki nodet joihin hypättiin nodesta
     *
     * @param cur node josta lähdetään
     * @return kaikki nodet joihin hypättiin
     */
    public Node[] identifySuccessors(Node cur) {
        successors = new Node[8];
        neighbors = pruneNeighbors(cur);
        if (neighbors == null) { //jos naapureita ei ole niin palautetaan null
            return null;
        }
        for (int i = 0; i < neighbors.length; i++) { //käydään kaikki naapurit läpi
            if (neighbors[i] != null) { //tarkistetaan nullien varalta
                if (neighbors[i].pass) {
                    int a = neighbors[i].getPosX();
                    int b = neighbors[i].getPosY();
                    Node tmpnode = jump(a, b, cur.getPosX(), cur.getPosY()); //hypätään nodesta
                    if (tmpnode != null) { //tarkistetaan nullien varalta
                        int x = tmpnode.getPosX();
                        int y = tmpnode.getPosY();
                        ng = distance(start, cur); //lasketaan etäisyys aloituspisteestä.
                        if (visited[x][y] == false || distance(start, Grid.getLabelAtCoords(x, y)) > ng) { // jos ei olla tarkistettu jotain nodea, tai etäisyys on suurempi.
                            visited[x][y] = true; //merkitään käydyksi
                            Grid.getLabelAtCoords(x, y).setParent(cur);//merkitään mistä nodesta tultiin nykyiseen
                            successors[i] = Grid.getLabelAtCoords(x, y); //ja lisätään se seuraajiin.
                        }
                    }
                }
            }
        }
        return successors;
    }

    /**
     * Haetaan naapurit joihin tulisi hypätä sen perusteella mistä päin ollaan
     * tulossa.
     *
     * @param node node jonka naapurit haetaan
     * @return node[] joka sisältää kaikki nodet joihin tulisi hypätä.
     */
    public Node[] pruneNeighbors(Node node) {
        Grid.updateLabel(node);
        Node parent;
        if (node == null) { //jos node on null niin ei haeta yhtään mitään
            return null;
        } else {
            parent = node.parent; //haetaan vanhempi
        }
        int x = node.getPosX();
        int y = node.getPosY();
        int px, py, dx, dy;
        if (parent != null) { //jos tiedetään mistä päin tullaa.
            px = parent.getPosX();
            py = parent.getPosY();
            dx = (x - px) / Math.max(Math.abs(x - px), 1); //mistä päin ollaan tultu x koordinaatissa. Arvo -1..1
            dy = (y - py) / Math.max(Math.abs(y - py), 1); //mistä päin ollaan tultu y koordinaatissa. Arvo -1..1
            if (dx != 0 && dy != 0) { //diagonal
                getDiagonals(x, y, dx, dy);
            } else if (dx == 0) { //horizontal
                getHorizontals(x, y, dx, dy);
            } else if (Grid.passable(x + dx, y)) { //vertical
                getVerticals(x, y, dx, dy);
            }
        } else {//return all neighbors
            return Grid.getNeighbours(node);
        }
        for (Node n : neighbors) { //päivitetään nodet
            Grid.updateLabel(n);
        }
        return neighbors;
    }

    /**
     * Haetaan vinosti olevat naapurit.
     *
     * @param x paikka koordinaatissa x.
     * @param y paikka koordinaatissa y.
     * @param dx tulosuunta koordinaatissa x.
     * @param dy tulosuunta koordinaatissa y.
     */
    private void getDiagonals(int x, int y, int dx, int dy) {
        if (Grid.passable(x, y + dy)) {
            neighbors[0] = Grid.getLabelAtCoords(x, y + dy);
        }
        if (Grid.passable(x + dx, y)) {
            neighbors[1] = Grid.getLabelAtCoords(x + dx, y);
        }
        if (Grid.passable(x, y + dy) || Grid.passable(x + dx, y)) {
            neighbors[2] = Grid.getLabelAtCoords(x + dx, y + dy);
        }
        if (Grid.passable(x - dx, y) || Grid.passable(x, y + dy)) {
            neighbors[3] = Grid.getLabelAtCoords(x - dx, y + dy);
        }
        if (Grid.passable(x, y - dy) || Grid.passable(x + dx, y)) {
            neighbors[4] = Grid.getLabelAtCoords(x + dx, y - dy);
        }
    }

    /**
     * Haetaan sivusuunnassa olevat naapurit.
     *
     * @param x paikka koordinaatissa x.
     * @param y paikka koordinaatissa y.
     * @param dx tulosuunta koordinaatissa x.
     * @param dy tulosuunta koordinaatissa y.
     */
    private void getHorizontals(int x, int y, int dx, int dy) {
        if (Grid.passable(x, y + dy)) {
            if (Grid.passable(x, y + dy)) {
                neighbors[0] = Grid.getLabelAtCoords(x, y + dy);
            }
            if (Grid.passable(x + 1, y)) {
                neighbors[1] = Grid.getLabelAtCoords(x + 1, y + dy);
            }
            if (Grid.passable(x - 1, y)) {
                neighbors[2] = Grid.getLabelAtCoords(x - 1, y + dy);
            }
        }
    }

    /**
     * Haetaan pystysuunnassa olevat naapurit.
     *
     * @param x paikka koordinaatissa x.
     * @param y paikka koordinaatissa y.
     * @param dx tulosuunta koordinaatissa x.
     * @param dy tulosuunta koordinaatissa y.
     */
    private void getVerticals(int x, int y, int dx, int dy) {
        if (Grid.passable(x, y + dy)) {
            neighbors[0] = Grid.getLabelAtCoords(x, y + dy);
        }
        if (Grid.passable(x + dx, y)) {
            neighbors[1] = Grid.getLabelAtCoords(x + dx, y);
        }
        if (Grid.passable(x, y + dy) || Grid.passable(x + dx, y)) {
            neighbors[2] = Grid.getLabelAtCoords(x + dx, y + dy);
        }
        if (Grid.passable(x - dx, y) || Grid.passable(x, y + dy)) {
            neighbors[3] = Grid.getLabelAtCoords(x - dx, y + dy);
        }
        if (Grid.passable(x, y - dy) || Grid.passable(x + dx, y)) {
            neighbors[4] = Grid.getLabelAtCoords(x + dx, y - dy);
        }
    }

    /**
     * Etsitään rekursiivisesti suuntaan joka saadaan siitä millä puolella
     * vanhempaa(px,py) lapsi(x,y) on. palauttaa arvon jos löydetty node on
     * maali, pakotettu naapuri, tai väliarvo edellämainittujen saavuttamiseksi.
     *
     * @param x Nykyinen x Koordinaatti
     * @param y Nykyinen y Koordinaatti
     * @param px Vanhemman x koordinaatti
     * @param py Vanhemman y koordinaatti
     * @return node johon hypättiin, tai null jos ei löytynyt sopivaa
     */
    public Node jump(int x, int y, int px, int py) {
        Node jx = null;
        Node jy = null;
        Node node = Grid.getLabelAtCoords(x, y);
        Grid.updateLabel(node);
        if (node == null || visited[x][y] == true) {
            return null;
        }
        int dx = (x - px) / Math.max(Math.abs(x - px), 1);
        int dy = (y - py) / Math.max(Math.abs(y - py), 1);

        if (!node.pass) { //node ei ole mahdollinen sijainti
            return null;
        }
        if (node == end) { //ollaanko maalissa
            return node;
        }

        if (dx != 0 && dy != 0) { //diagonal, Tarkistetaan onko noden vieressä seinä ja jos on niin haetaan vinosti.
            //jälkimmäisten nodejen tulisi olla !(false) eli true kun kyseessä on seinä, mutta jostain syystä ei tässä kohtaa tunnisteta seinäksi tai muuten vaan temppuilee koska antaa jatkuvasti kaikilla arvoilla !(true) eli falsen jolloin ei päästä koskaan pakottamaan naapuria.
            if ((Grid.passable((x - dx), (y + dy)) && !(Grid.passable((x + dx), y))) //syystä tai toisesta ei melkein koskaan saa arvoa true, vaikka pitäisi
                    || (Grid.passable((x + dx), (y - dy)) && !(Grid.passable(x, (y - dy))))) { //ja siten algoritmi ei useasti löydä maalia jos on jonkin seinän takana, ja tarpeeksi lähellä sitä.
                //System.out.println("forced");//15.4 toimii jotenkuten nyt.
                return node;
            }
        } else //horizontal / vertical
         if (dx != 0) { //x
                if ((Grid.passable(x + dx, y + 1) && !Grid.passable(x, y + 1))
                        || (Grid.passable(x + dx, y - 1) && !Grid.passable(x, y + 1))) {
                    return node;
                }
            } else //y
             if ((Grid.passable(x + 1, y + dy) && !Grid.passable(x + 1, y))
                        || (Grid.passable(x - 1, y + dy) && !Grid.passable(x - 1, y))) {
                    return node;
                }
        if (dx != 0 && dy != 0) { //vinosti liikuttaessa etsitään sivu/pystysuunnissa olevat pisteet
            jx = jump(x + dx, y, x, y);
            jy = jump(x, y + dx, x, y);
            if (jx != null || jy != null) {
                return node;
            }
        }
        if (Grid.passable(x + dx, y) || Grid.passable(x, y + dy)) { //tarkistetaan että sivu ja pysty suunnassa pääsee liikkumaan
            return jump(x + dx, y + dy, x, y);  //tässä jostain syystä aina välillä tulee stack overflowia, meneekö rekursio liian korkeaksi tai jotain?
        } else {
            return null; //palautetaan null jos ei löytynyt sopivaa arvoa.
        }
    }

    /**
     * haetaan kahden pisteen etäisyys.
     *
     * @param a ensimmäinen node.
     * @param b toinen node.
     * @return etäisyys floattina.
     */
    public float distance(Node a, Node b) {
        int x = a.getPosX();
        int y = a.getPosY();
        int tx = b.getPosX();
        int ty = b.getPosY();
        return (float) Math.sqrt(Math.pow(Math.abs(x - tx), 2) + Math.pow(Math.abs(y - ty), 2)); //etäisyys
    }
}
