package polunetsintatiralabra.gui;

import javax.swing.JLabel;
/**
 * Node luokka reitinhakua varten
 * @author Peter
 */
public class Node extends JLabel implements Comparable{
    public Node parent;
    int posx = 0;
    int posy = 0;
    int priority = Integer.MAX_VALUE;
    public float g,h,f;
    public boolean pass = true;
    
    public void updateGHFP(float g, float h, Node parent){
		this.parent = parent;
		this.g = g;
		this.h = h;
		f = g+h;
    }
    /** @param x asettaa noden x koordinaatin */
    public void setPosX(int x){
        posx = x;
    }
    /** @param y asettaa noden y koordinaatin */
    public void setPosY(int y){
        posy = y;
    }
    /** @return palauttaa noden x koordinaatin */
    public int getPosX(){
        return posx;
    }
     /** @return palauttaa noden y koordinaatin */
    public int getPosY(){
        return posy;
    }
   /** @return palauttaa noden prioriteetti arvon */
    public int getPriority(){
        return priority;
    }
     /** @param c asettaa noden prioriteetti arvon */
    public void setPriority(int c){
        priority = c;
    }
    public void setParent(Node p){
        this.parent = p;
    }    
    public void setPass(boolean b){
        this.pass = b;
    }    
    /**
     * Vertailee kahta nodea keskenään.
     * @param o vertailtava node */
    @Override
    public int compareTo(Object o) {
        Node node = (Node)o;
        return priority - node.getPriority();
    }
}
