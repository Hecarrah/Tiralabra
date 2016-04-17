package polunetsintatiralabra.algorithms;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import polunetsintatiralabra.algorithms.Astar;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class AstarTest {
    @Test
    public void runTest(){
        Astar as = new Astar();
        if(as.run() == true){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }
    @Test(timeout=3000)
    public void runTest2(){
        Astar as = new Astar();
        Grid g = new Grid();
        Grid.setStart(Grid.getEnd());
        if(as.run() == true){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }
    @Test(timeout=2000)
    public void runTest3(){
        Astar as = new Astar();
        Grid g = new Grid();
        Node n = new Node();
        Node n2 = new Node();
        n = g.getStart();
        n.setPosX(10);
        n2.setPosY(19);
        if(as.run() == true){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }  
    @Test
    public void runTest4(){
        Astar as = new Astar();
        Grid g = new Grid();
        Node n = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        n.setPosX(1);
        n.setPosY(1);
        n2.setPosX(1);
        n3.setPosY(1);
        Grid.updateLabel(n);
        Grid.updateLabel(n2);
        Grid.updateLabel(n3);
        if(as.run() == true){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }  
    @Test
    public void runTest5(){
        Astar as = new Astar();
        Grid g = new Grid();
        Node n = new Node();
        Node n2 = new Node();
        n.setPosX(1);
        n.setPosY(1);
        n.setBackground(Color.yellow);
        n2.setBackground(Color.yellow);
        Grid.flush();
        if(n.getBackground() != Color.yellow){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    } 
    @Test
    public void isEndTest(){
        Astar as = new Astar();
        Grid g = new Grid();
        Node n = new Node();
        Node n2 = new Node();
        n = Grid.getEnd();
        if(as.isEnd(n, n2)){
            assertTrue("isEnd ei toiminut oikein", true);
        }
    }
    @Test
    public void distanceTest(){
        Grid g = new Grid();
        Astar as = new Astar();
        Node a = new Node();
        Node b = new Node();
        a.setPosX(0);
        b.setPosX(1);
        if(as.distance(a, b) == 1){
            assertTrue("Distance metodi laski väärin", true);
        }
    }
}
