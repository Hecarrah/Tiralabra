package polunetsintatiralabra.gui;

import polunetsintatiralabra.JumpPointSearch;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import polunetsintatiralabra.*;

/**
 * Käyttöliittymä reitinhakualgoritmeille.
 *
 * @author Peter
 */
public class Grid extends JFrame implements MouseListener {

    private static Node[][] label;
    private static int size = 32;
    boolean startSet = false;
    boolean endSet = false;
    private static Node startLabel;
    private static Node endLabel;

    public Grid() {
        /**
         * Rakennetaan ruudukko ja laitetaan siihen valmiiksi alkuarvo alku ja
         * loppupisteelle. Ruudukko täyttyy Node:illa jotka ovat JLabelin
         * aliluokkia. Jokaiselle nodelle lisätään myös MouseListener olio jotta
         * taulukkoon voidaan piirtää seiniä ja siirtää aloitus ja
         * loppupisteitä.
         */
        super("Field");
        JPanel panel = new JPanel(new GridLayout(size, size));
        label = new Node[size][size];

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                label[i][j] = init(label[i][j], i, j);
                panel.add(label[i][j]);
                
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    label[i][j].setBackground(Color.DARK_GRAY);
                }
                if (i == 1 && j == 1) {
                    label[i][j].setBackground(Color.green);
                    startLabel = label[i][j];
                    startSet = true;
                }
                if (i == 30 && j == 30) {
                    label[i][j].setBackground(Color.red);
                    endLabel = label[i][j];
                    endSet = true;
                }
            }
        }
        this.add(panel, BorderLayout.CENTER);
    }
/**
 * initialisointi metodi nodeille.
 * @param n käsiteltävä node
 * @param x noden sijainti x koordinaatissa.
 * @param y noden sijainti y koordinaatissa.
 */
    private Node init(Node n, int x, int y) {
        n = new Node();
        n.setOpaque(true);
        n.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        n.setBackground(Color.WHITE);
        n.addMouseListener(this);
        n.setPosX(x);
        n.setPosY(y);
        return n;
    }

    @Override
    /**
     * ctrl+mouse1 = alkupaikka 
     * ctrl+mouse3 = maali 
     * shift+mouse1 = aja astar
     * shift+mouse3 = aja JPS
     * mouse1 = piirrä seinä
     * mouse2 = tyhjennä ruutu
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
                }
                source.setBackground(Color.green);
                startLabel = source;
                startSet = true;

            }
            if (arg0.getModifiers() == MouseEvent.BUTTON3_MASK + MouseEvent.CTRL_MASK) {
                if (endSet) {
                    endLabel.setBackground(Color.white);
                    endLabel = source;
                }
                source.setBackground(Color.red);
                endLabel = source;
                endSet = true;
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
            Astar as = new Astar();
            System.out.println("Astar start");
            as.run();
            System.out.println("Astar end");
        }
        if (arg0.getModifiers() == MouseEvent.BUTTON3_MASK + MouseEvent.SHIFT_MASK) {
            JumpPointSearch jps = new JumpPointSearch();
            System.out.println("JPS start");
            jps.search();
            System.out.println("JPS end");
        }
    }

    /**
     * jos hiiren vasen eli mouse1 on pohjassa niin piirretään seinää hiiren
     * liikkuessa, jos oikea niin tyhjennetään.
     *
     * @param arg0 ruutu jonka päällä hiiri on
     */
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
    }
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    /**
     * Haetaan tietty node jostain koordinaatista
     *
     * @param a x koordinaatti
     * @param b y koordinaatti
     * @return palauttaa noden.
     */
    public static Node getLabelAtCoords(int a, int b) {
        //System.out.println(a+" "+b);
        if(a < 0 || b < 0 || a > size-1 || b > size-1){
            return null;
        }
        else if (label[a][b].getBackground() == Color.DARK_GRAY || label[a][b].getForeground() == Color.DARK_GRAY) {
            //System.out.println("seinä");
            return null;
        }
        return label[a][b];
    }

    /**
     * hakee noden koordinaatit
     *
     * @param l node jonka koodinaatit haetaan
     * @return koordinaatit taulukossa jossa [0] on x koordinaatti ja [1] y
     * koordinaatti.
     */
    public static int[] getLabelCoords(Node l) {
        int a = l.getPosX();
        int b = l.getPosY();
        int[] c = new int[2];
        c[0] = a;
        c[1] = b;
        return c;
    }
    /**
     * haetaan jonkin noden naapurit 8 suunnassa.
     *
     * @param current node jonka naapurit haetaan
     * @return palautetaan naapurit taulukossa jossa n[0] on ensimmäinen naapuri
     * n[1] toinen .. n[7] viimeinen.
     */
    public static Node[] getNeighbours(Node current) {
        Node[] n = new Node[8];
        n[0] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 0);
        n[1] = getLabelAtCoords(current.getPosX() + 0, current.getPosY() - 1);
        n[2] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 0);
        n[3] = getLabelAtCoords(current.getPosX() + 0, current.getPosY() + 1);

        n[4] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() - 1);
        n[5] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() - 1);
        n[6] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 1);
        n[7] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 1);
        return n;
    }
    /**
     * tarkisteaan onko node tietyssä pisteessä vapaa.
     * tulisi palauttaa false jos node on joko null, tai seinää.
     * @param x x koordinaatti
     * @param y y koordinaatti
     * @return boolean arvo, onko node mahdollinen paikka.
     */
    public static boolean passable(int x, int y){
        if(getLabelAtCoords(x,y) == null){
            return false;
        }
        if((getLabelAtCoords(x,y).getBackground() == Color.DARK_GRAY)){
            return false;
        }else{
         return false;
        }
    }

    /**
     * haetaan alkupiste
     * @return palauttaa alkupisteen
     */
    public static Node getStart() {
        return startLabel;
    }

    /**
     *haetaan loppupiste
     * @return palauttaa loppupisteen
     */
    public static Node getEnd() {
        return endLabel;
    }

    /**
     * tyhjentää taululta kaiken paitsi seinät ja maalipisteet.
     */
    public static void flush() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (label[i][j].getBackground() == Color.DARK_GRAY || label[i][j].getBackground() == Color.GREEN || label[i][j].getBackground() == Color.RED) {

                } else {
                    label[i][j].setBackground(Color.white);
                }
            }
        }
    }
    public static void main(String[] arg) {
        Grid cb = new Grid();
        cb.setSize(512, 512);
        cb.setVisible(true);
        cb.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
