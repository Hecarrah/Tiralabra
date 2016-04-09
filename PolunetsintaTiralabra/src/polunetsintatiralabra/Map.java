package polunetsintatiralabra;

import polunetsintatiralabra.gui.Node;

public class Map<K, V> {

    private Object[] a = new Object[512];
    private Object[] b = new Object[512];
    int index = 0;

    private int contains(Object haku) {
        for (int i = 0; i < index; i++) {
            if (a[i] == haku) {
                return i;
            }
        }
        return -1;
    }

    public Object get(Object haku) {
        if (contains(haku) != -1) {
            return b[contains(haku)];
        } else {
            return null;
        }
    }

    public void put(Object a, Object b) {
        if (contains(a) != -1) {
            this.b[contains(a)] = b;
        } else {
            this.a[index] = a;
            this.b[index] = b;
            index++;
        }
    }
}
