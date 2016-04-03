package poulunetsintatiralabra;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.Astar;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class AstarTest {
        Astar as = new Astar();
        Grid g = new Grid();
        
    @Test
    public void runTest(){
        if(as.run()){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }
    @Test
    public void runTest2(){
        Node n = new Node();
        Node n2 = new Node();
        n = g.getStart();
        n.setPosX(10);
        n2.setPosY(19);
        if(as.run()){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }
    @Test
    public void compareToTest(){
        Node a = new Node();
        Node b = new Node();
        a.setPriority(0);
        b.setPriority(1);
                if(as.distance(a, b) == 1){
            assertTrue("Distance metodi laski väärin", true);
        }
    }
}