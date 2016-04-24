/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polunetsintatiralabra.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import polunetsintatiralabra.algorithms.Astar;
import polunetsintatiralabra.algorithms.JumpPointSearch;
import static polunetsintatiralabra.gui.Grid.getLabelCoords;
import static polunetsintatiralabra.gui.Grid.updateLabel;

/**
 *
 * @author Peter
 */
public class Mouse implements MouseListener {

    private static boolean startSet = false;
    private static boolean endSet = false;
    private static Node startLabel;
    private static Node endLabel;

    @Override
    /**
     * ctrl+mouse1 = alkupaikka ctrl+mouse3 = maali shift+mouse1 = aja astar
     * shift+mouse3 = aja JPS mouse1 = piirrä seinä mouse2 = tyhjennä ruutu
     *
     * @param arg0 ruutu jonka päällä hiiri on tapahtuman hetkellä
     */
    public void mouseClicked(MouseEvent arg0) {
        Node source = (Node) arg0.getSource();
        if (!(source.getBackground().equals(Color.DARK_GRAY))) {
            if (arg0.getModifiers() == MouseEvent.BUTTON1_MASK + MouseEvent.CTRL_MASK) {
                if (startSet) {
                    startLabel.setBackground(Color.white);
                    startLabel = source;
                    Grid.setStart(source);
                }
                source.setBackground(Color.green);
                startLabel = source;
                startSet = true;
                Grid.setStart(source);
            }
            if (arg0.getModifiers() == MouseEvent.BUTTON3_MASK + MouseEvent.CTRL_MASK) {
                if (endSet) {
                    endLabel.setBackground(Color.white);
                    endLabel = source;
                    Grid.setEnd(source);
                }
                source.setBackground(Color.red);
                endLabel = source;
                endSet = true;
                Grid.setEnd(source);
            } else if (arg0.getModifiers() == MouseEvent.BUTTON1_MASK) {
                source.setBackground(Color.DARK_GRAY);
                source.setPass(false);
                int[] t = getLabelCoords(source);
                System.out.println("x: " + t[0] + " y: " + t[1]);
            }
        } else if (arg0.getModifiers() == MouseEvent.BUTTON3_MASK) {
            source.setBackground(Color.white);
            source.setPass(true);
        }
        if (arg0.getModifiers() == MouseEvent.BUTTON1_MASK + MouseEvent.SHIFT_MASK) {
            Grid.flush();
            Astar as = new Astar();
            double t = System.nanoTime();
            System.out.println("Astar start");
            as.run(Grid.getStart(), Grid.getEnd());
            System.out.println("Astar end, run time: ");
            System.out.println((System.nanoTime() - t) / 1000000 + "ms");
            
        }
        if (arg0.getModifiers() == MouseEvent.BUTTON3_MASK + MouseEvent.SHIFT_MASK) {
            Grid.flush();
            JumpPointSearch jps = new JumpPointSearch();
            double t = System.nanoTime();          
            System.out.println("JPS start");
            jps.search(Grid.getStart(), Grid.getEnd());
            System.out.println("JPS end: run time: ");
            System.out.println((System.nanoTime() - t) / 1000000 + "ms");
        }
        updateLabel(source);
    }

    /**
     * jos hiiren vasen eli mouse1 on pohjassa niin piirretään seinää hiiren
     * liikkuessa, jos oikea niin tyhjennetään.
     *
     * @param arg0 ruutu jonka päällä hiiri on
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {
        Node source = (Node) arg0.getSource();
        if (!(source.getBackground().equals(Color.red)) && !(source.getBackground().equals(Color.green))) {
            if (arg0.getModifiersEx() == MouseEvent.getMaskForButton((MouseEvent.BUTTON1))) {
                source.setBackground(Color.DARK_GRAY);
                source.setPass(false);
            }
            if (arg0.getModifiersEx() == MouseEvent.getMaskForButton((MouseEvent.BUTTON3))) {
                source.setBackground(Color.white);
                source.setPass(true);
            }
        }
        updateLabel(source);
    }
    public void mouseExited(MouseEvent arg0) {
    }
    public void mousePressed(MouseEvent arg0) {
    }
    public void mouseReleased(MouseEvent arg0) {
    }
}
