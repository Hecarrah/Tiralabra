package polunetsintatiralabra.dataStructures;
 /**
  * Map olio johon voidaan tallentaa arvopari ja hakea jälkimmäinen ensimmäisen perusteella.
  * @author Peter
  * @param <K> avain arvo.
  * @param <V> toinen arvo.
  */
public class Map<K, V> {

    private Object[] key = new Object[100000];
    private Object[] value = new Object[100000];
    //int index = 0; //index määrittää mihin indeksiin arvot sijoitetaan

    /**
     * contains metodi tarkistaa sisältääkö olio jo jonkin tietyn arvon.
     * @param haku haettava olio.
     * @return palauttaa indeksin jos löytyi, muuten -1.
     */
//    public int contains(Object haku) {
//        for (int i = 0; i < index; i++) {
//            if (key[i] == haku) { //jos arvo löytyy niin palautetaan sen indeksi.
//                return i;
//            }
//        }
//        return -1; //muuten -1
//    }
    
    /**
     * Generoidaan hashcode oliolle
     * @param o olio jolle generoidaan
     * @return palautettava hashcode
     */
    public int hashC(Object o){
        int hash = 0;
        char[] c = o.toString().toCharArray();
        for(char c2 : c){
            hash = hash+Character.getNumericValue(c2);
        }
        return hash;
    }
/**
 * Hakee olion oliosta.
 * @param haku haettava olio
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
 * Laittaa uuden arvon olioon, tai jos avain arvo on jo olemassa niin päivittää sen pari-arvon
 * @param a avain arvo.
 * @param b pari-arvo
* @return true kun metodi on suoritettu onnistuneesti
 */
    public boolean put(Object a, Object b) {
        if (key[hashC(a)] != null) { //tarkistetaan onko arvo jo oliossa
            this.value[hashC(a)] = b;
        } else {
            this.key[hashC(a)] = a;
            this.value[hashC(a)] = b;
            //index++; //kasvatetaan indeksinumeroa seuraavalle arvolle.
        }return true;
    }
}
