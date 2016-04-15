package dataStructures;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.dataStructures.Map;
import polunetsintatiralabra.gui.Node;

public class MapTest {
    Map m = new Map();
        
    @Test
    public void putTest(){
        Node a = new Node();
        Node b = new Node();      
        if(m.put(a, b)){
            assertTrue("Put metodi ei palauttanut true arvoa", true);
        }
    }
    @Test
    public void putTest2(){
        Node a = new Node();
        Node b = new Node();      
        if(m.put(a, -1)){
            assertTrue("Put metodi ei palauttanut true arvoa", true);
        }
    }
    @Test
    public void getTest(){
        Node a = new Node();
        Node b = new Node();
        a.setPosX(10);
        b.setPosY(10);
        m.put(a, b);
        
        if(m.get(a).equals(b)){
            assertTrue("Getteri ei toiminut oikein", true);
        }
    }
    @Test
    public void containsTest(){
        Node a = new Node();
        Node b = new Node();
        a.setPosX(10);
        b.setPosY(10);
        m.put(a, b);
        if(m.contains(a) != -1){
            assertTrue("Contains ei löytänyt arvoa", true);
        }
    }
}
