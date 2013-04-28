
package ohjharjoitus;

/**
 * Tuloslaskin, lisää ja vähentää pisteitä.
 * 
 * @author O
 */
public class Pistelaskin {
    /**
     * Laskimen pisteluku.
     */
    int pisteet;
    
    public Pistelaskin() {
        pisteet = 0;
    }
    
    /**
     * Vähentää pisteitä.
     * @param x  vähennys
     */
    public void vahenna(int x) {
        pisteet -= x;
    }
    /**
     * Lisää pisteitä.
     * @param x lisäys
     */
    public void lisaa(int x) {
        pisteet += x;
    }
    
    public int getPisteet() {
        return pisteet;
    }
    /**
     * Nollaa laskimen pisteet.
     */
    public void nollaaPisteet() {
        pisteet = 0;
    }
}
