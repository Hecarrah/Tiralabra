package polunetsintatiralabra.gui;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.Astar;

public class GridTest {
        Astar as = new Astar();
        Grid g = new Grid();
        
    @Test
    public void ConstructorTest(){
        if(g.isActive()){
            assertTrue("käyttöliittymä ei ole aktiivinen", true);
        }
        if(g.isVisible()){
            assertTrue("käyttöliittymä ei ole näkyvillä", true);
        }
        if(g.isResizable()){
            assertTrue("käyttöliittymän koko ei ole säädettävissä", true);
        }
        if(g.getStart() != null || g.getEnd() != null){
            assertTrue("Maalipisteet ei ole asetettu", true);
        }
    }
    @Test
    public void getLabelAtCoordsTest(){
        if(g.getLabelAtCoords(0,0) == g.getStart()){
            assertTrue("Ohjelma ei hakenut oikeaa nodea", true);
        }
    }
    @Test
    public void getLabelCoordsTest(){
        Node a = new Node();
        a = g.getStart();
                if(g.getLabelCoords(a)[0] == a.getPosX() && g.getLabelCoords(a)[1] == a.getPosY()){
            assertTrue("Koordinaatit haettiin väärin", true);
        }
    }
    @Test
    public void getNeighboursTest(){
        Node a = new Node();
        a = g.getStart();
        
                if(g.getNeighbours(a)[0] == null && g.getNeighbours(a)[7] != null && g.getNeighbours(a)[2] == null){
            assertTrue("naapurit väärin", true);
        }
    }
    @Test
    public void getPassableTest(){
        if(Grid.passable(-1, -1)){
            assertTrue("passable toimii väärin", false);
        }
    }
    @Test
    public void getPassableTest2(){
        Node a = new Node();
        a.setPosX(10);
        a.setPosY(10);
        if(Grid.passable(a.getPosX(), a.getPosY())){
            assertTrue("passable toimii väärin", true);
        }
    }
    @Test
    public void getPassableTest3(){
        Node a = new Node();
        a.setPosX(10);
        a.setPosY(10);
        a.setBackground(Color.DARK_GRAY);
        if(Grid.passable(a)){
            assertTrue("passable toimii väärin", false);
        }
    }
    @Test
    public void getPassableTest4(){
        Node a = new Node();
        a.setPosX(10);
        a.setPosY(10);
        a.setBackground(Color.DARK_GRAY);
        Grid.updateLabel(a);
        if(Grid.passable(a.getPosX(),a.getPosY())){
            assertTrue("passable toimii väärin", false);
        }
    }
        @Test
    public void colorNodesTest(){
        Node a = new Node();
        Grid.colorNodes(a);
        if(a.getBackground() == Color.LIGHT_GRAY){
            assertTrue("colorNodes ei toiminut oikein", true);
        }
    }
        @Test
    public void colorNodesTest2(){
        Node n = new Node();
        n.setPosX(1);
        n.setPosY(1);
        n.setBackground(Color.WHITE);
        Grid.colorNodes(n);
        if(n.getBackground() != Color.WHITE){
            assertTrue("ColorNodes ei toiminut oikein.", true);
        }
    }
    @Test
    public void drawPathTest(){
        Node n = new Node();
        n.setPosX(1);
        n.setPosY(1);
        n.setBackground(Color.WHITE);
        Grid.drawPath(n);
        if(n.getBackground() != Color.pink){
            assertTrue("drawPath ei värittänyt oikein.", true);
        }
    }
}
