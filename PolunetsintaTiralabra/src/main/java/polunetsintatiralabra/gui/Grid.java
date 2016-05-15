package polunetsintatiralabra.gui;

import java.awt.*;
import javax.swing.*;

/**
 * Käyttöliittymä reitinhakualgoritmeille.
 *
 * @author Peter
 */
public class Grid extends JFrame {

    private static Node[][] label;
    private static final int size = 32;
    private static Node startLabel;
    private static Node endLabel;
    private static JPanel panel;

    public Grid() {
        /**
         * Rakennetaan ruudukko ja laitetaan siihen valmiiksi alkuarvo alku ja
         * loppupisteelle. Ruudukko täyttyy Node:illa jotka ovat JLabelin
         * aliluokkia. Jokaiselle nodelle lisätään myös MouseListener olio jotta
         * taulukkoon voidaan piirtää seiniä ja siirtää aloitus ja
         * loppupisteitä.
         */
        super("Field");
        panel = new JPanel(new GridLayout(size, size));
        label = new Node[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                label[i][j] = init(label[i][j], i, j);
                panel.add(label[i][j]);

                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    label[i][j].setBackground(Color.DARK_GRAY);
                }
                if (i == 1 && j == 1) {
                    startLabel = label[i][j];
                    setStart(label[i][j]);
                }
                if (i == 30 && j == 30) {
                    endLabel = label[i][j];
                    setEnd(label[i][j]);
                }
            }
        }
        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * initialisointi metodi nodeille.
     *
     * @param n käsiteltävä node.
     * @param x noden sijainti x koordinaatissa.
     * @param y noden sijainti y koordinaatissa.
     */
    private Node init(Node n, int x, int y) {
        n = new Node();
        n.setOpaque(true);
        n.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        n.setBackground(Color.WHITE);
        n.addMouseListener(new Mouse());
        n.setPosX(x);
        n.setPosY(y);
        return n;
    }

    /**
     * Haetaan tietty node jostain koordinaatista.
     *
     * @param a x koordinaatti.
     * @param b y koordinaatti.
     * @return palauttaa noden.
     */
    public static Node getLabelAtCoords(int a, int b) {
        if (a < 0 || b < 0 || a > size - 1 || b > size - 1) {
            return null;
        } else if (label[a][b].getBackground() == Color.DARK_GRAY) {
            return null;
        }
        return label[a][b];
    }

    /**
     * hakee noden koordinaatit.
     *
     * @param l node jonka koodinaatit haetaan.
     * @return koordinaatit taulukossa jossa [0] on x koordinaatti ja [1] y
     * koordinaatti.
     */
    public static int[] getLabelCoords(Node l) {
        updateLabel(l);
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
     * @param current node jonka naapurit haetaan.
     * @return palautetaan naapurit taulukossa jossa n[0] on ensimmäinen naapuri
     * n[1] toinen .. n[7] viimeinen.
     */
    public static Node[] getNeighbours(Node current) {
        updateLabel(current);
        Node[] n = new Node[8];
        n[0] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 0);
        n[1] = getLabelAtCoords(current.getPosX() + 0, current.getPosY() - 1);
        n[2] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 0);
        n[3] = getLabelAtCoords(current.getPosX() + 0, current.getPosY() + 1);

        if (getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 0) != null && getLabelAtCoords(current.getPosX() + 0, current.getPosY() - 1) != null) {
            n[4] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() - 1);
        }
        if (getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 0) != null && getLabelAtCoords(current.getPosX() + 0, current.getPosY() - 1) != null) {
            n[5] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() - 1);
        }
        if (getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 0) != null && getLabelAtCoords(current.getPosX() + 0, current.getPosY() + 1) != null) {
            n[6] = getLabelAtCoords(current.getPosX() - 1, current.getPosY() + 1);
        }
        if (getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 0) != null && getLabelAtCoords(current.getPosX() + 0, current.getPosY() + 1) != null) {
            n[7] = getLabelAtCoords(current.getPosX() + 1, current.getPosY() + 1);
        }
        return n;
    }

    /**
     * tarkisteaan onko node tietyssä pisteessä vapaa. tulisi palauttaa false
     * jos node on joko null, tai seinää.
     *
     * @param x x koordinaatti.
     * @param y y koordinaatti.
     * @return boolean arvo, onko node mahdollinen paikka.
     */
    public static boolean passable(int x, int y) {
        //updateLabel(getLabelAtCoords(x,y));
        if (getLabelAtCoords(x, y) == null) {
            return false;
        } else {
            return (getLabelAtCoords(x, y).getBackground() != Color.DARK_GRAY);
        }
    }

    /**
     * tarkisteaan onko node tietyssä pisteessä vapaa. tulisi palauttaa false
     * jos node on joko null, tai seinää.
     *
     * @param a tarkistettava node.
     * @return boolean arvo, onko node mahdollinen paikka.
     */
    public static boolean passable(Node a) {
        updateLabel(a);
        if (a == null) {
            return false;
        } else {
            //System.out.println(a.getBackground().toString());
            return (a.getBackground() != (Color.DARK_GRAY));
        }
    }

    /**
     * haetaan alkupiste.
     *
     * @return palauttaa alkupisteen.
     */
    public static Node getStart() {
        return startLabel;
    }

    /**
     * haetaan loppupiste.
     *
     * @return palauttaa loppupisteen.
     */
    public static Node getEnd() {
        return endLabel;
    }

    /**
     * Asettaa loppupaikan tietyksi nodeksi.
     *
     * @param n node joka asetetaan loppupaikaksi.
     */
    public static void setEnd(Node n) {
        if (n == null) {
        } else {
            endLabel.setBackground(Color.white);
            n.setBackground(Color.red);
            endLabel = n;
            //endSet = true;
        }
    }

    /**
     * Asettaa aloituspaikan tietyksi nodeksi.
     *
     * @param n node joka asetetaan aloituspaikaksi
     */
    public static void setStart(Node n) {
        if (n == null) {
        } else {
            startLabel.setBackground(Color.white);
            n.setBackground(Color.green);
            startLabel = n;
            //startSet = true;
        }
    }

    /**
     * tyhjentää taululta kaiken paitsi seinät ja maalipisteet.
     */
    public static void flush() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (label[i][j].getBackground() == Color.DARK_GRAY || label[i][j].getBackground() == Color.GREEN || label[i][j].getBackground() == Color.RED) {
                    label[i][j].parent = null;
                } else {
                    label[i][j].setBackground(Color.WHITE);
                    label[i][j].parent = null;
                }
            }
        }
    }

    /**
     * piirretään polku maalista alkuun.
     *
     * @param next node josta peruutetaan viiva alkuun.
     */
    public static void drawPath(Node next) {
        while (next != getStart() || next != null) {//ei maalata loppu- ja aloituspisteitä
            if (next.parent == null) {
                break;
            }
            if (next.parent == getStart()) {
                next.setBackground(Color.pink);
                break;
            }
            if (next == getEnd()) {
                next = next.parent;
            } else {
                next.setBackground(Color.pink);
                next = next.parent;
            }
        }
    }
    /**
     * väritetään nodet.
     *
     * @param next väritettävä node.
     */
    public static  void colorNodes(Node next) { //maalataan käydyt nodet
        if (next != getEnd() && next != getStart()) {
            next.setBackground(Color.gray);
        }
    }

    /**
     * Synkronoidaan labelin arvo varmuuden varalta.
     *
     * @param n päivitettävä node.
     */
    public static void updateLabel(Node n) {
        if (n != null) {
            int i = n.getPosX();
            int j = n.getPosY();
            label[i][j] = n;
        }
    }

    public static void main(String[] arg) {
        TestUtility tu = new TestUtility();
        tu.setVisible(true);
        Grid cb = new Grid();
        cb.setSize(512, 512);
        cb.setVisible(true);
        cb.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
