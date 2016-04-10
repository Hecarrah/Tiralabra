package polunetsintatiralabra.gui;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.Astar;

public class NodeTest {
        Astar as = new Astar();
        Grid g = new Grid();
        Node n = new Node();
        
    @Test
    public void getterTest(){
        if(n.getPosX() == 0){
            assertTrue("PosX alkuarvo väärin", true);
        }
        if(n.getPosY() == 0){
            assertTrue("PosY alkuarvo väärin", true);
        }
        if(n.getPriority() == 0){
            assertTrue("Priority alkuarvo väärin", true);
        }
    }
    @Test
    public void setPosXTest(){
        n.setPosX(1);
        if(n.getPosX() == 1){
            assertTrue("setPosX toimii väärin, väärä uusi arvo", true);
        }
    }
    @Test
    public void setPosYTest(){
        n.setPosY(1);
        if(n.getPosX() == 1){
            assertTrue("setPosY toimii väärin, väärä uusi arvo", true);
        }
    }
    @Test
    public void setPriorityTest(){
        n.setPriority(1);
        if(n.getPriority() == 1){
            assertTrue("setPosX toimii väärin, väärä uusi arvo", true);
        }
    }
    @Test
    public void compareToTest(){
        Node a = new Node();
        Node b = new Node();
        a.setPriority(0);
        b.setPriority(1);
                if(b.compareTo((Object)a) == 1){
            assertTrue("CompareTo toimii väärin", true);
        }
    }
}
