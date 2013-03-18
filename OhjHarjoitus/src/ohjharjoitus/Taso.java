
package ohjharjoitus;

import java.util.*;

public class Taso {
    public ArrayList<Kohde> tasonKohteet = new ArrayList<Kohde>();
    int rajoitus;
    
    public Taso(ArrayList<Kohde> kohteet, int raja){
        tasonKohteet = kohteet;
        rajoitus = raja;
    }
    
    
}
