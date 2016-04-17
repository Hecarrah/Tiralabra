package polunetsintatiralabra.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.junit.Test;
import static org.junit.Assert.*;

public class MouseTest {

    @Test
    public void mouseClickedTest() throws AWTException {
        Node n = new Node();
        Grid g = new Grid();
        Robot r = new Robot();
        r.mouseMove((512 / 32) * 30, (512 / 32) * 30);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        n = Grid.getLabelAtCoords(30, 30);
        if (n.getBackground() != Color.DARK_GRAY) {
            assertTrue("MouseTest ei toiminut oikein.", true);
        }
        r.mouseRelease(InputEvent.BUTTON3_MASK);
        if (n.getBackground() != Color.white) {
            assertTrue("MouseTest ei toiminut oikein.", true);
        }
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.keyRelease(KeyEvent.VK_CONTROL);
        if (n.getBackground() != Color.green) {
            assertTrue("MouseTest ei toiminut oikein.", true);
        }
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.keyRelease(KeyEvent.VK_SHIFT);
        if (n.getBackground() != Color.red) {
            assertTrue("MouseTest ei toiminut oikein.", true);
        }
    }
}
