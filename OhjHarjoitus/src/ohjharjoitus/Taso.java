
package ohjharjoitus;

import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import java.util.*;

public class Taso {
    public ArrayList<Kohde> tasonKohteet = new ArrayList<Kohde>();
    public ArrayList<Seuraaja> tasonSeur = new ArrayList<Seuraaja>();
    int rajoitus;
    
    public Taso(ArrayList<Kohde> kohteet, ArrayList<Seuraaja> seur, int raja){
        tasonKohteet = kohteet;
        tasonSeur = seur;
        rajoitus = raja;
    }
    
    
}
