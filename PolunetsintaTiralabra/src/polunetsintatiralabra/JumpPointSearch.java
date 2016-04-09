package polunetsintatiralabra;
import java.awt.Color;
import java.util.PriorityQueue;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

/**
 * @author Peter
 */
public class JumpPointSearch {

    private int color = 255;
    int startX, startY, endX, endY;
    Node[] neighbors = new Node[8];
    float ng;
    Node tmpNode, cur;
    Node[] successors, possibleSuccess;
    private PriorityQueue<Node> frontier = new PriorityQueue<Node>();
    private boolean[][] visited = new boolean[128][128];

    
    /**
     * Initialisoidaan ja siivotaan taulu
     */
    public JumpPointSearch() {
        Grid.flush();
        Node start = Grid.getStart();
        this.startX = start.getPosX();
        this.startY = start.getPosY();
        Node end = Grid.getEnd();
        this.endX = end.getPosX();
        this.endY = end.getPosY();

    }
    /**
     * Käydään läpi mahdolliset sijainnit
     * @return palautetaan tosi jos maali löytyi, false muuten
     */
    public boolean search() {
        Grid.flush();
        frontier.add(Grid.getStart());
        frontier.add(Grid.getStart());
        if (cur == Grid.getEnd()) {
            drawPath(cur);
            return true;
        }
        while (true) { //jos nykyinen node ei ole vielä maali.
            cur = frontier.poll();
            colorNodes(cur); //piirretään reitti takaisin
            possibleSuccess = identifySuccessors(cur);
            if(possibleSuccess == null){
                break;
            }
            for (int i = 0; i < possibleSuccess.length; i++) { //käydään läpi kaikki mahdolliset sijainnit
                if (possibleSuccess[i] != null) {
                    frontier.add(possibleSuccess[i]);
                    if (possibleSuccess[i] == Grid.getEnd()) { // maali löytyi
                        cur = Grid.getEnd();
                        break;
                    }
                    colorNodes(possibleSuccess[i]); //väritetään käydyt nodet
                }
            }
            if (frontier.isEmpty()) {  //maalia ei löytynyt
                System.out.println("No Path Found");
                break;
            }
            if (cur == Grid.getEnd()) { //maali löytyi
                System.out.println("Path Found");
                drawPath(cur);
                break;
            }
        }
        return false;
    }
    /**
     * väritetään nodet
     * @param next väritettävä node
     */
    public void colorNodes(Node next) { //maalataan käydyt nodet
        if (next != Grid.getEnd() && next != Grid.getStart()) {
            float[] hsb = Color.RGBtoHSB(100, 100, color, null); // Maalataan kaikki käydyt nodet
            next.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            color = color - 1;
        }
    }
  /**
   * piirretään polku maalista alkuun
   * @param next node josta peruutetaan viiva alkuun.
   */
    public void drawPath(Node next) { //WIP piiretään polku maalista alkupisteeseen
        //System.out.println("path found");
        while (next != Grid.getStart() && next!=null) { //ei maalata loppu- ja aloituspisteitä
            if(next == Grid.getEnd()){
                next = next.parent;
            }
            next.setBackground(Color.pink);
            next = next.parent;
        }

    }
    /**
     * haetaan kaikki nodet joihin hypättiin nodesta
     * @param cur node josta lähdetään
     * @return kaikki nodet joihin hypättiin
     */
    private Node[] identifySuccessors(Node cur) { 
            successors = new Node[8];
            neighbors = pruneNeighbors(cur);
            if(neighbors == null){ //jos naapureita ei ole niin palautetaan null
                return null;
            }
        for (int i = 0; i < neighbors.length; i++) { //käydään kaikki naapurit läpi
            if(neighbors[i] != null){ //tarkistetaan nullien varalta
            if (Grid.passable(neighbors[i].getPosX(),neighbors[i].getPosY())) { 
                int a = neighbors[i].getPosX();
                int b = neighbors[i].getPosY();
                Node tmpnode = jump(a, b, cur.getPosX(), cur.getPosY()); //hypätään nodesta
                if (tmpnode != null) { //tarkistetaan nullien varalta
                    int x = tmpnode.getPosX();
                    int y = tmpnode.getPosY();
                    ng = distance(Grid.getStart(),cur); //lasketaan etäisyys aloituspisteestä.
                    if (visited[x][y] == false || distance(Grid.getStart(), Grid.getLabelAtCoords(x, y)) > ng) { // jos ei olla tarkistettu jotain nodea, tai etäisyys on suurempi.
                        visited[x][y] = true; //merkitään käydyksi
                        Grid.getLabelAtCoords(x, y).setParent(cur);//merkitään mistä nodesta tultiin nykyiseeb
                        successors[i] = Grid.getLabelAtCoords(x, y); //ja lisätään se seuraajiin.
                    }
                }
            }
            }
        }
        return successors;
    }
    /**
     * Haetaan naapurit joihin tulisi hypätä sen perusteella mistä päin ollaan tulossa.
     * @param node node jonka naapurit haetaan
     * @return node[] joka sisältää kaikki nodet joihin tulisi hypätä.
     */
    public Node[] pruneNeighbors(Node node) { 
        Node parent;
        if(node == null){ //jos node on null niin ei haeta yhtään mitään
            return null;
        }else{
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
                if (Grid.passable(x,y+dy)) {
                    neighbors[0] = Grid.getLabelAtCoords(x,y+dy);
                }
                if (Grid.passable(x+dx,y)) {
                    neighbors[1] = Grid.getLabelAtCoords(x+dx,y);
                }
                if (Grid.passable(x,y+dy) || Grid.passable(x+dx,y) ) { 
                    neighbors[2] = Grid.getLabelAtCoords(x+dx,y+dy);
                }
                if (Grid.passable(x-dx,y) || Grid.passable(x,y+dy) ) { 
                    neighbors[3] = Grid.getLabelAtCoords(x-dx,y+dy);
                }
                if (Grid.passable(x,y-dy) || Grid.passable(x+dx,y) ) { 
                    neighbors[4] = Grid.getLabelAtCoords(x+dx,y-dy); 
                }
            } else if (dx == 0) { //horizontal
                if (Grid.passable(x,y+dy)) {
                    if (Grid.passable(x,y+dy)) {
                        neighbors[0] = Grid.getLabelAtCoords(x,y+dy);
                    }                
                    if (Grid.passable(x+1,y)) { 
                        neighbors[1] = Grid.getLabelAtCoords(x+1,y+dy);
                    }                
                    if (Grid.passable(x-1,y)) {
                        neighbors[2] = Grid.getLabelAtCoords(x-1,y+dy);
                    }
                }
            } 
            else if(Grid.passable(x+dx,y)) { //vertical
                    if (Grid.passable(x+dx,y)) {
                        neighbors[0] = Grid.getLabelAtCoords(x+dx,y);
                    }                
                    if (Grid.passable(x, y+1)) { 
                        neighbors[1] = Grid.getLabelAtCoords(x+dx,y+1);
                    }                
                    if (Grid.passable(x, y-1)) {
                        neighbors[2] = Grid.getLabelAtCoords(x+dx,y-1);
                    }
                }
            }  else {//return all neighbors
            return Grid.getNeighbours(cur);
        }
        return neighbors;
    }
    /**
     * Etsitään rekursiivisesti suuntaan joka saadaan siitä millä puolella vanhempaa(px,py) lapsi(x,y) on.
     * palauttaa arvon jos löydetty node on maali, pakotettu naapuri, tai väliarvo edellämainittujen saavuttamiseksi.
     * @param x Nykyinen x Koordinaatti
     * @param y Nykyinen y Koordinaatti
     * @param px Vanhemman x koordinaatti
     * @param py Vanhemman y koordinaatti
     * @return node johon hypättiin, tai null jos ei löytynyt sopivaa
     */
    private Node jump(int x, int y, int px, int py) {
        Node jx = null;
        Node jy = null;
        Node node = Grid.getLabelAtCoords(x, y);
        if(node != null && node != Grid.getEnd() && node != Grid.getStart()){ //merkitään käydyt nodet keltaiseksi visualisointia varten
             node.setBackground(Color.yellow);
        }
        int dx = (x - px) / Math.max(Math.abs(x - px), 1);
        int dy = (y - py) / Math.max(Math.abs(y - py), 1);

        if (!Grid.passable(x, y)) { //node ei ole mahdollinen sijainti
            return null;
        }
        if (node == Grid.getEnd()) { //ollaanko maalissa
            return Grid.getLabelAtCoords(x, y);
        }
        if (dx != 0 && dy != 0) { //diagonal, Tarkistetaan onko noden vieressä seinä ja jos on niin haetaan vinosti.
            //jälkimmäisten nodejen tulisi olla !(false) eli true kun kyseessä on seinä, mutta jostain syystä ei tässä kohtaa tunnisteta seinäksi tai muuten vaan temppuilee koska antaa jatkuvasti kaikilla arvoilla !(true) eli falsen jolloin ei päästä koskaan pakottamaan naapuria.
            if ((Grid.passable((x-dx),(y+dy)) && !(Grid.passable((x-dx),y))) //syystä tai toisesta ei melkein koskaan saa arvoa true, vaikka pitäisi
                    || (Grid.passable((x+dx),(y-dy)) && !(Grid.passable(x,(y-dy))))) { //ja siten algoritmi ei useasti löydä maalia jos on jonkin seinän takana.
                System.out.println("forced");
                return Grid.getLabelAtCoords(x, y);
            }
        } 
        else{ //horizontal / vertical
        if (dx != 0) { //x
            if ((Grid.passable(x+dx,y+1) && !Grid.passable(x,y+1))
                    || (Grid.passable(x+dx,y-1) && !Grid.passable(x,y-1))) {
                return Grid.getLabelAtCoords(x, y);
            }
        } else{ //y
            if ((Grid.passable(x+1,y+dy) && !Grid.passable(x+1,y))
                || (Grid.passable(x-1,y+dy) && !Grid.passable(x-1,y))) {
            return Grid.getLabelAtCoords(x, y);
        }
        }
    }
        if (dx != 0 && dy != 0) { //vinosti liikuttaessa etsitään sivu/pystysuunnissa olevat pisteet
            jx = jump(x + dx, y, x, y);
            jy = jump(x, y + dx, x, y);
            if (jx != null || jy != null) {
                return Grid.getLabelAtCoords(x, y);
            }
        }
        if (Grid.passable(x + dx, y) || Grid.passable(x, y + dy)) { //tarkistetaan että sivu ja pysty suunnassa pääsee liikkumaan
            try{
            return jump(x + dx, y + dy, x, y);  //tässä jostain syystä aina välillä tulee stack overflowia, meneekö rekursio liian korkeaksi tai jotain?
            }
            catch(StackOverflowError e){
                System.out.println(e);
            }
        } else {
            return null; //palautetaan null jos ei löytynyt sopivaa arvoa.
        }
        return null;
    }
/**
 * haetaan kahden pisteen etäisyys.
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
