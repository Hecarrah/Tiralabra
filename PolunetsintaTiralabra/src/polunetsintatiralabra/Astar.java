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
    /**
     * Run metodi ajaa itse algoritmin.
     * Aluksi tyhjennetään vanhan algoritmin jäljet, ja laitetaan aloituspiste jonoon.
     * Sitten käydään naapurit läpi ja valitaan niistä paras vaihtoehto maaliinpääsemisen kannalta.
     * Samalla käydään läpi reittiä taaksepäin ja merkitään käyttöliittymään selkeämmin, pinkillä värillä itse polku
     * Käyttöliittymään merkitään myös kaikki käsitellyt solut muuttuvalla värillä
     * @return boolean että algoritmi on suoritettu onnistuneesti
     */

    public boolean run() {
        Grid.flush();
        int color = 255;
        int smallest = Integer.MAX_VALUE;
        PriorityQueue<Node> frontier = new PriorityQueue<Node>();
        frontier.add(Grid.getStart());
        HashMap<Node, Integer> cost_so_far = new HashMap<Node, Integer>();
        boolean[][] visited = new boolean[128][128];
        HashMap<Node, Node> came_from = new HashMap<Node, Node>();
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
                    int new_cost = cost_so_far.get(current) +1;
                    if (visited[next.getPosX()][next.getPosY()] == false || new_cost < cost_so_far.get(next)) {
                        visited[next.getPosX()][next.getPosY()] = true; //merkitään paikka käydyksi
                        
                        if (next == Grid.getEnd()) { //Algoritmi on suoritettu loppuun, maali löydetty
                            came_from.put(next, current);
                            
                            while(next != Grid.getStart()){ //maalataan itse polku käytyjen nodejen päälle
                                if(next != Grid.getEnd()){
                                next.setBackground(Color.pink);
                                }
                                next = came_from.get(next);
                                
                        }
                            System.out.println("done");  
                            return true;
                        }
                        cost_so_far.put(next, new_cost);
                        int priority = new_cost + distance(Grid.getEnd(),next); //annetaan tärkeys sen perusteela kuinka lähellä on maalia.
                        next.setPriority(priority);
                        frontier.add(next);
                        came_from.put(next, current);


                        smallest = Math.min(smallest, next.getPriority());  // Maalataan kaikki käydyt nodet
                        float[] hsb = Color.RGBtoHSB(100, 100, color, null);
                        next.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                        color = color - 1;
                    }
                }
            }
            
        }
        return false;
    }
    /**
     * distance metodi kertoo pituuden kahden noden välillä, tässä algoritmissa sitä käytetään kun lasketaan mikä node on lähinpänä maalia.
     * @param a kohde node
     * @param b node josta halutaan kohteeseen.
     * @return matka sijainnista kohteeseen
     */
    public int distance(Node a, Node b){
        int dx = Math.abs(b.getPosX() - a.getPosX());
        int dy = Math.abs(b.getPosY() - a.getPosY());
        return Math.abs(dx+dy);
    }
}
