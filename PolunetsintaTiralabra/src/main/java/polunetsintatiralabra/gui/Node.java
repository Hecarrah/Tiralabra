package polunetsintatiralabra.gui;

import javax.swing.JLabel;

/**
 * Node luokka reitinhakua varten
 *
 * @author Peter
 */
public class Node extends JLabel implements Comparable {

    public Node parent;
    int posx = 0;
    int posy = 0;
    int priority = Integer.MAX_VALUE;
    public float g, h, f;
    public boolean pass = true;

    public void updateGHFP(float g, float h, Node parent) {
        this.parent = parent;
        this.g = g;
        this.h = h;
        f = g + h;
    }

    /**
     * @param x asettaa noden x koordinaatin
     */
    public void setPosX(int x) {
        posx = x;
    }

    /**
     * @param y asettaa noden y koordinaatin
     */
    public void setPosY(int y) {
        posy = y;
    }

    /**
     * @return palauttaa noden x koordinaatin
     */
    public int getPosX() {
        return posx;
    }

    /**
     * @return palauttaa noden y koordinaatin
     */
    public int getPosY() {
        return posy;
    }

    /**
     * @return palauttaa noden prioriteetti arvon
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param c asettaa noden prioriteetti arvon
     */
    public void setPriority(int c) {
        priority = c;
    }

    /**
     * Asettaa vanhemman.
     *
     * @param p vanhempi.
     */
    public void setParent(Node p) {
        this.parent = p;
    }

    /**
     * asettaa passable arvon.
     *
     * @param b totuusarvo.
     */
    public void setPass(boolean b) {
        this.pass = b;
    }

    /**
     * Vertailee kahta nodea keskenään.
     *
     * @param o vertailtava node
     * @return int arvo joka kertoo kumpi on parempi. positiivinen jos nykyinen on parempi, negatiivinen jos vertailtava on parempi.
     */
    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        Node node = (Node) o;
        return priority - node.getPriority();
    }
}
