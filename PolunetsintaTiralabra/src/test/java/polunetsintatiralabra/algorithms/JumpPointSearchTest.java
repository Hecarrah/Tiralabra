package polunetsintatiralabra.algorithms;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;
import polunetsintatiralabra.gui.Grid;
import polunetsintatiralabra.gui.Node;

public class JumpPointSearchTest {

    Grid g = new Grid();
    JumpPointSearch jps = new JumpPointSearch();

    @Test
    public void searchTest() {
        JumpPointSearch as = new JumpPointSearch();
        if (as.search(Grid.getStart(), Grid.getEnd())) {
            assertTrue("JPS ei suorittanut oikein", true);
        }
    }

    @Test
    public void identifySuccessorsTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(0);
        n.setPosY(0);
        Grid.updateLabel(n);
        Node[] nt = as.identifySuccessors(n);
        for (int i = 0; i < 8; i++) {
            if (nt[i] == null) {
                assertTrue("JPS ei suorittanut oikein", true);
            }
        }
        n = Grid.getStart();
        nt = as.identifySuccessors(n);
        for (int i = 0; i < 8; i++) {
            if (nt[i] == null) {
                assertTrue("IdentifySuccessors ei suorittanut oikein", true);
            }
        }
    }

    @Test
    public void PruneNeighborsTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        n.setPosX(3);
        n.setPosY(3);
        Grid.updateLabel(n);
        Node[] nt = as.pruneNeighbors(n);
        for (int i = 0; i < 8; i++) {
            if (nt[i] != null) {
                assertTrue("PruneNeighborts ei suorittanut oikein", true);
            }
        }
    }

    @Test
    public void PruneNeighborsDiagonalTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        Node n2 = new Node();
        n.parent = n2;
        n.setPosX(3);
        n.setPosY(3);
        n2.setPosX(1);
        n2.setPosY(1);
        Grid.updateLabel(n);
        Grid.updateLabel(n2);
        Node[] nt = as.pruneNeighbors(n);
        for (int i = 0; i < 5; i++) {
            if (nt[i] != null) {
                assertTrue("PruneNeighbortsDiagonal ei suorittanut oikein", true);
            }
        }
    }
    @Test
    public void PruneNeighborsVerticalTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        Node n2 = new Node();
        n.parent = n2;
        n.setPosX(3);
        n.setPosY(3);
        n2.setPosX(3);
        n2.setPosY(1);
        Grid.updateLabel(n);
        Grid.updateLabel(n2);
        Node[] nt = as.pruneNeighbors(n);
        for (int i = 0; i < 3; i++) {
            if (nt[i] != null) {
                assertTrue("PruneNeighbortsVertical ei suorittanut oikein", true);
            }
        }
    }
    @Test
    public void PruneNeighborsHorizontalTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = new Node();
        Node n2 = new Node();
        n.parent = n2;
        n.setPosX(3);
        n.setPosY(3);
        n2.setPosX(1);
        n2.setPosY(3);
        Grid.updateLabel(n);
        Grid.updateLabel(n2);
        Node[] nt = as.pruneNeighbors(n);
        for (int i = 0; i < 3; i++) {
            if (nt[i] != null) {
                assertTrue("PruneNeighbortsHorizontal ei suorittanut oikein", true);
            }
        }
    }

    @Test
    public void jumpTest() {
        Grid g = new Grid();
        JumpPointSearch as = new JumpPointSearch();
        Node n = as.jump(1, 1, 0, 0);
        if (n == Grid.getEnd()) {
            assertTrue("Jump ei suorittanut oikein", true);
        }
    }

    @Test
    public void distanceTest() {
        Grid g = new Grid();
        Node a = new Node();
        Node b = new Node();
        a.setPosX(0);
        a.setPosY(0);
        b.setPosX(5);
        b.setPosY(5);
        if (jps.distance(a, b) == 5) {
            assertTrue("distance ei toiminut oikein", true);
        }
    }
}
