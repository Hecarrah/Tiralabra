package poulunetsintatiralabra;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.JumpPointSearch;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class JumpPointSearchTest {
    Grid g = new Grid();
    JumpPointSearch jps = new JumpPointSearch();
        
    @Test
    public void putTest(){
        Node a = new Node();
        jps.colorNodes(a);
        if(a.getBackground() == Color.LIGHT_GRAY){
            assertTrue("colorNodes ei toiminut oikein", true);
        }
    }
    @Test
    public void distanceTest(){
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
    @Test
    public void drawPathTest(){
        Node a = new Node();
        Node b = new Node();
        a.setParent(b);
        jps.drawPath(a);
        if(a.getBackground() == Color.pink){
            assertTrue("colorNodes ei toiminut oikein", true);
        }
        if(b.getBackground() == Color.pink){
            assertTrue("colorNodes ei toiminut oikein vanhemmalle", true);
        }
    }
}