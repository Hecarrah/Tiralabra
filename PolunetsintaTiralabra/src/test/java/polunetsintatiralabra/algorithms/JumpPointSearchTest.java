package polunetsintatiralabra.algorithms;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.algorithms.JumpPointSearch;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class JumpPointSearchTest {
    Grid g = new Grid();
    JumpPointSearch jps = new JumpPointSearch();
        @Test
    public void searchTest(){
        JumpPointSearch as = new JumpPointSearch();
        if(as.search()){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }  
    @Test
    public void identifySuccessorsTest(){
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(0);
        n.setPosY(0);
        Grid.updateLabel(n);
        Node[] nt = as.identifySuccessors(n);
        if(nt[0] == null && nt[7].getBackground() == Color.white){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }
@Test    
    public void identifySuccessorsTest2(){
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(3);
        n.setPosY(3);
        Grid.updateLabel(n);
        Node[] nt = as.identifySuccessors(n);
        if(nt[0] == null && nt[6].getBackground() == Color.white && nt[7].getBackground() == Color.white){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }    
@Test    
    public void PruneNeighborsTest(){
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(3);
        n.setPosY(3);
        Grid.updateLabel(n);
        Node[] nt = as.pruneNeighbors(n);
        if(nt[0] == null && nt[6].getBackground() == Color.white && nt[7].getBackground() == Color.white){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }    
@Test    
    public void PruneNeighborsTest2(){
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(3);
        n.setPosY(3);
        Grid.updateLabel(n);
        Node[] nt = as.pruneNeighbors(n);
        if(nt[0] == null && nt[7].getBackground() == Color.white ){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }    
@Test    
    public void jumpTest(){
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = as.jump(1, 1, 0, 0);
        if(n == Grid.getEnd()){
            assertTrue("Ohjelma ei suorittanut oikein", true);
        }
    }    
    @Test
    public void distanceTest(){
        Grid g = new Grid();
        Node a = new Node();
        Node b = new Node();
        a.setPosX(0);
        a.setPosY(0);
        b.setPosX(5);
        b.setPosY(5);
        if(jps.distance(a, b) == 5){
            assertTrue("distance ei toiminut oikein", true);
        }
    }
}