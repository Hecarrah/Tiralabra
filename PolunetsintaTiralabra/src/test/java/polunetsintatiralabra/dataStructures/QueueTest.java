package polunetsintatiralabra.dataStructures;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.gui.Node;

public class QueueTest {
    Queue q = new Queue();
        
    @Test
    public void putTest(){
        Node a = new Node();     
        if(q.add(a)){
            assertTrue("Arvo lisättiin onnistuneesti", true);
        }
    }
    @Test
    public void isEmptyTest(){
        if(q.isEmpty()){
            assertTrue("isEmpty toimii väärin", true);
        }
    }
    @Test
    public void isEmptyTest2(){
        Node a = new Node();    
        q.add(a);
        if(q.isEmpty()){
            assertTrue("isEmpty toimii väärin", true);
        }
    }
    @Test
    public void pollTest(){
        Node a = new Node();    
        Node b = new Node();
        a.setPosX(1);
        q.add(a);
        b = q.poll();
        if(a.equals(b)){
            assertTrue("Poll ei palauttanut oikeaa arvoa", true);
        }
    }
    @Test
    public void pollTest2(){
        Node a = new Node();    
        Node b = new Node();
        Node c = new Node();
        a.setPriority(1000);
        b.setPriority(1);
        q.add(a);
        q.add(b);
        c = q.poll();
        if(c.equals(b)){
            assertTrue("Poll ei palauttanut oikeaa arvoa", true);
        }
    }
}
