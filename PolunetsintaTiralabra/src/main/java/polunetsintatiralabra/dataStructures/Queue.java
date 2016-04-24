package polunetsintatiralabra.dataStructures;

import java.util.Arrays;
import polunetsintatiralabra.gui.Node;

/**
 * Queue olio jonka idea on korvata Javan PriorityQueue projektissa.
 *
 * @author Peter
 * @param <E> Olion tyyppi.
 */
public class Queue<E> {

    Node[] table = new Node[0];

    /**
     * Vanhempi
     * @param i heapin indeksi jonka vanhempi haetaan
     * @return vanhemman indeksi
     */
    private int parent(int i) {
        return Math.floorDiv(i, 2);
    }
    /**
     * Vasen lapsi
     * @param i heapin indeksi jonka lapsi haetaan
     * @return lapsen indeksi
     */
    private int left(int i) {
        return 2 * i;
    }
    /**
     * Oikea lapsi
     * @param i heapin indeksi jonka lapsi haetaan
     * @return lapsen indeksi
     */
    private int right(int i) {
        return (2 * i) + 1;
    }
    /**
     * Heapify metodi järjestää, ja varmistaa että taulu on oikeassa muodossa toiminnan kannalta.
     * @param i heapin indeksi jonka perusteella tarkistetaan heappi.
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if (r < table.length - 1) {
            if (table[l].compareTo(table[r]) < 1) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (table[i].compareTo(table[smallest]) > 1) {
                Node temp = table[smallest];
                table[smallest] = table[i];
                table[i] = temp;
                heapify(smallest);
            } else if (l == table.length - 1 && table[i].compareTo(table[l]) > 1) {
                Node temp = table[l];
                table[l] = table[i];
                table[i] = temp;
            }
        }
    }
    /**
     * Muutetaan heapin kokoa.
     * @param n kuinka paljon, ja miten heapin kokoa muutetaan
     */
    private void resize(int n) {
        Node[] table2;
        table2 = Arrays.copyOf(table, table.length + n);
        table = table2;
    }
    /**
     * poistetaan heapista pienin arvo.
     * @return pienin arvo.
     */
    private Node heap_del_min() {
        Node min = table[0];
        table[0] = table[table.length - 1];
        resize(-1);
        heapify(0);
        return min;
    }
    /**
     * Lisätään heappiin arvo.
     * @param k lisättävä arvo.
     */
    private void heap_insert(Node k) {
        resize(1);
        int i = table.length - 1;
        while (i > 0 && table[parent(i)].compareTo(k) < 1) {
            table[i] = table[parent(i)];
            i = parent(i);
        }
        table[i] = k;
    }
    /**
     * Tarkistetaan onko jono tyhjä.
     * @return totuusarvo tyhjyydestä.
     */
    public boolean isEmpty() {
        return (table[0] == null);
    }

    /**
     * Lisätään arvo jonoon.
     *
     * @param o Lisättävä node
     * @return totuusarvo metodin suorituksesta
     */
    public boolean add(Node o) {
        heap_insert(o);
        return true;
    }

    /**
     * Haetaan arvo jonosta sen tärkeyden perusteella. 
     *
     * @return palautettava node.
     */
    public Node poll() {
        return heap_del_min();
    }
}
