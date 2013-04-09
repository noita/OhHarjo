
package ohjharjoitus;

/**
 * Tuloslaskin, lisää ja vähentää pisteitä.
 * 
 * @author O
 */
public class Pistelaskin {
    int rajoitus;
    int pisteet;
    
    public Pistelaskin(int raja) {
        rajoitus = raja;
        pisteet = 0;
    }
    
    //negatiiviset pisteet ovat tarkoituksella mahdollisia!
    //case: not a bug; a feature
    public void vahenna(int x) {
        pisteet -= x;
    }
    
    public void lisaa(int x) {
        pisteet += x;
    }
    
    public int getPisteet() {
        return pisteet;
    }
    
    public void nollaaPisteet() {
        pisteet = 0;
    }
}
