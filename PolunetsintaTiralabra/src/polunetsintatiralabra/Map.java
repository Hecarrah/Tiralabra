package polunetsintatiralabra;
 /**
  * Map olio johon voidaan tallentaa arvopari ja hakea jälkimmäinen ensimmäisen perusteella.
  * @author Peter
  * @param <K> avain arvo.
  * @param <V> toinen arvo.
  */
public class Map<K, V> {

    private Object[] a = new Object[1028];
    private Object[] b = new Object[1028];
    int index = 0; //index määrittää mihin indeksiin arvot sijoitetaan

    /**
     * contains metodi tarkistaa sisältääkö olio jo jonkin tietyn arvon.
     * @param haku haettava olio.
     * @return palauttaa indeksin jos löytyi, muuten -1.
     */
    public int contains(Object haku) {
        for (int i = 0; i < index; i++) {
            if (a[i] == haku) { //jos arvo löytyy niin palautetaan sen indeksi.
                return i;
            }
        }
        return -1; //muuten -1
    }
/**
 * Hakee olion oliosta.
 * @param haku haettava olio
 * @return palauttaa olion jos löytyi, null jos ei.
 */
    public Object get(Object haku) {
        if (contains(haku) != -1) {
            return b[contains(haku)];
        } else {
            return null;
        }
    }
/**
 * Laittaa uuden arvon olioon, tai jos avain arvo on jo olemassa niin päivittää sen pari-arvon
 * @param a avain arvo.
 * @param b pari-arvo
* @return true kun metodi on suoritettu onnistuneesti
 */
    public boolean put(Object a, Object b) {
        if (contains(a) != -1) { //tarkistetaan onko arvo jo oliossa
            this.b[contains(a)] = b;
        } else {
            this.a[index] = a;
            this.b[index] = b;
            index++; //kasvatetaan indeksinumeroa seuraavalle arvolle.
        }return true;
    }
}
