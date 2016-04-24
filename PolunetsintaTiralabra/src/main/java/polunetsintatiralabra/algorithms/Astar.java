package polunetsintatiralabra.algorithms;

import polunetsintatiralabra.dataStructures.Map;
import polunetsintatiralabra.dataStructures.Queue;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

/**
 * Astar polunetsintäalgoritmi.
 */
public class Astar {

    private Queue<Node> frontier = new Queue<Node>();
    private Map<Node, Integer> cost_so_far = new Map<Node, Integer>();
    private boolean[][] visited = new boolean[128][128];
    private Node end = null;
    private Node start = null;

    /**
     * Run metodi ajaa itse algoritmin. Aluksi tyhjennetään vanhan algoritmin
     * jäljet, ja laitetaan aloituspiste jonoon. Sitten käydään naapurit läpi ja
     * valitaan niistä paras vaihtoehto maaliinpääsemisen kannalta. Samalla
     * käydään läpi reittiä taaksepäin ja merkitään käyttöliittymään selkeämmin,
     * pinkillä värillä itse polku Käyttöliittymään merkitään myös kaikki
     * käsitellyt solut muuttuvalla värillä
     *
     * @param s Alkupiste.
     * @param e Loppupiste.
     * @return boolean että algoritmi on suoritettu onnistuneesti
     */
    public boolean run(Node s, Node e) {
        end = e;
        start = s;
        frontier.add(start);

        while (end != null && start != null && !frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current == null) {
                return false;
            }
            cost_so_far.put(current, 1);
            if (current == end) { //Algoritmi on suoritettu loppuun
                System.out.println("Path found");
                return true;
            }
            Node[] Neighbours = Grid.getNeighbours(current);
            for (Node next : Neighbours) {
                if (next != null) {
                    int new_cost = (int) cost_so_far.get(current) + 1;
                    if (visited[next.getPosX()][next.getPosY()] == false || new_cost < (int) cost_so_far.get(next)) {
                        visited[next.getPosX()][next.getPosY()] = true; //merkitään paikka käydyksi

                        if (isEnd(next, current)) { //Algoritmi on suoritettu loppuun, maali löydetty
                            return true;
                        }

                        next.setPriority(new_cost + distance(end, next)); //prioriteetti maalin läheisyyden perusteella.
                        cost_so_far.put(next, new_cost);
                        frontier.add(next);
                        next.parent = current;

                        Grid.colorNodes(next); //maalataan nodet.
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tarkistetaan onko node maalipiste. Jos on maalipiste niin piirretään
     * reitti takaisin alkuun.
     *
     * @param node tarkistettava node.
     * @param current Nykyinen node.
     * @return boolean, tosi jos on loppupiste, false muuten.
     */
    public boolean isEnd(Node node, Node current) {
        if (node == end) { //Algoritmi on suoritettu loppuun, maali löydetty
            node.parent = current;
            Grid.drawPath(node); //piirretään itse polku
            //System.out.println("done");
            return true;
        } else {
            return false;
        }
    }

    /**
     * distance metodi kertoo pituuden kahden noden välillä.
     *
     * @param a kohde node
     * @param b node josta halutaan kohteeseen.
     * @return matka sijainnista kohteeseen
     */
    public int distance(Node a, Node b) {
        int dx = Math.abs(a.getPosX() - b.getPosX());
        int dy = Math.abs(a.getPosY() - b.getPosY());
        int r = (dx + dy);
        return r;
    }
}
