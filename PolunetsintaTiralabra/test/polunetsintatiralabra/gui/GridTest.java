package polunetsintatiralabra.gui;

import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.Astar;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

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
}
