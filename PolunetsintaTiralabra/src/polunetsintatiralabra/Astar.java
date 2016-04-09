package polunetsintatiralabra;

import java.awt.Color;
import java.util.HashMap;
import java.util.PriorityQueue;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

/**
 * Astar polunetsintäalgoritmi.
 */
public class Astar {

    private int smallest = Integer.MAX_VALUE;
    private int color = 255;
    private PriorityQueue<Node> frontier = new PriorityQueue<Node>();
    private Map<Node, Integer> cost_so_far = new Map<Node, Integer>();
    private boolean[][] visited = new boolean[128][128];
    private Map<Node,Node> came_from = new Map<Node,Node>();

    /**
     * Run metodi ajaa itse algoritmin. Aluksi tyhjennetään vanhan algoritmin
     * jäljet, ja laitetaan aloituspiste jonoon. Sitten käydään naapurit läpi ja
     * valitaan niistä paras vaihtoehto maaliinpääsemisen kannalta. Samalla
     * käydään läpi reittiä taaksepäin ja merkitään käyttöliittymään selkeämmin,
     * pinkillä värillä itse polku Käyttöliittymään merkitään myös kaikki
     * käsitellyt solut muuttuvalla värillä
     *
     * @return boolean että algoritmi on suoritettu onnistuneesti
     */
    public boolean run() {
        Grid.flush();
        frontier.add(Grid.getStart());

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            cost_so_far.put(current, 1);

            if (current == Grid.getEnd() || current == null) { //Algoritmi on suoritettu loppuun
                return true;
            }

            Node[] Neighbours = Grid.getNeighbours(current);
            for (Node next : Neighbours) {
                if (next != null) {
                    cost_so_far.put(next, 1);
                    int new_cost = (int)cost_so_far.get(current) + 1;
                    if (visited[next.getPosX()][next.getPosY()] == false || new_cost < (int)cost_so_far.get(next)) {
                        visited[next.getPosX()][next.getPosY()] = true; //merkitään paikka käydyksi

                        if (next == Grid.getEnd()) { //Algoritmi on suoritettu loppuun, maali löydetty
                            came_from.put(next, current);
                            drawPath(next); //piirretään itse polku
                            System.out.println("done");
                            return true;
                        }
                        cost_so_far.put(next, new_cost);
                        int priority = new_cost + distance(Grid.getEnd(), next); //annetaan tärkeys sen perusteela kuinka lähellä on maalia.
                        next.setPriority(priority);
                        frontier.add(next);
                        came_from.put(next, current);

                        colorNodes(next); //maalataan nodet.
                    }
                }
            }

        }
        return false;
    }
 /**
  * Väritetääb käydyt nodet.
  * @param next väritettävä node.
  */
    public void colorNodes(Node next) {
        smallest = Math.min(smallest, next.getPriority());  // Maalataan kaikki käydyt nodet
        float[] hsb = Color.RGBtoHSB(100, 100, color, null);
        next.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        color = color - 1;
    }

    /**
     * Piirretään polku taaksepäin maalista alkuun.
     *
     * @param next node mistä aloitetaan.
     */
    public void drawPath(Node next) {
        while (next != Grid.getStart()) { //maalataan itse polku käytyjen nodejen päälle
            if (next != Grid.getEnd()) {
                next.setBackground(Color.pink);
            }
            next = (Node)came_from.get(next);

        }
    }

    /**
     * distance metodi kertoo pituuden kahden noden välillä, tässä algoritmissa
     * sitä käytetään kun lasketaan mikä node on lähinpänä maalia.
     *
     * @param a kohde node
     * @param b node josta halutaan kohteeseen.
     * @return matka sijainnista kohteeseen
     */
    public int distance(Node a, Node b) {
        int dx = Math.abs(b.getPosX() - a.getPosX());
        int dy = Math.abs(b.getPosY() - a.getPosY());
        return Math.abs(dx + dy);
    }
}
