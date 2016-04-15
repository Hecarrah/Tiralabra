package poulunetsintatiralabra;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.JumpPointSearch;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class JumpPointSearchTest {
    Grid g = new Grid();
    JumpPointSearch jps = new JumpPointSearch();
        
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
}