package polunetsintatiralabra.dataStructures;

/**
 * Map olio johon voidaan tallentaa arvopari ja hakea jälkimmäinen ensimmäisen
 * perusteella.
 *
 * @author Peter
 * @param <K> avain arvo.
 * @param <V> toinen arvo.
 */
public class Map<K, V> {

    private Object[] key = new Object[10000];
    private Object[] value = new Object[10000];

    /**
     * Generoidaan hashcode oliolle.
     *
     * @param o olio jolle generoidaan.
     * @return palautettava hashcode.
     */
    public int hashC(Object o) {
        int hash = 0;
        hash = o.hashCode() % key.length;
        return hash;
    }

    /**
     * Hakee olion oliosta.
     *
     * @param haku haettava olio.
     * @return palauttaa olion jos löytyi, null jos ei.
     */
    public Object get(Object haku) {
        if (key[hashC(haku)] != null) {
            return value[hashC(haku)];
        } else {
            return null;
        }
    }

    /**
     * Laittaa uuden arvon olioon, tai jos avain arvo on jo olemassa niin
     * päivittää sen pari-arvon.
     *
     * @param a avain arvo.
     * @param b pari-arvo.
     * @return true kun metodi on suoritettu onnistuneesti.
     */
    public boolean put(Object a, Object b) {
        int hash = hashC(a);
        if (key[hash] != null) { //tarkistetaan onko arvo jo oliossa.
            if (key[hash] == a) {
                this.value[hash] = b;
            } else if (key[hash] != a) { openHash(hash, a, b);}
        } else {
            this.key[hash] = a;
            this.value[hash] = b;
        }
        return true;
    }

    /**
     * Törmäys tapahtunut, tehdään avoin hajautus, eli haetaan ensimmäinen tyhjä
     * paikka.
     *
     * @param hash hash arvo.
     * @param a avain-arvo.
     * @param b pari-arvo.
     */
    public void openHash(int hash, Object a, Object b) {
        for (int i = hash; i < key.length - 1; i++) {
            if (key[i] == null) {
                this.key[hash] = a;
                this.value[hash] = b;
                break;
            }
            if (i == key.length - 2) {
                i = 0;
            }
        }
    }
}
