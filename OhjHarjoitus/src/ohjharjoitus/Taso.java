
package ohjharjoitus;

import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import java.util.*;

public class Taso {
    ArrayList<Kohde> tasonKohteet = new ArrayList<Kohde>();
    ArrayList<Seuraaja> tasonSeur = new ArrayList<Seuraaja>();
    int rajoitus;
    
    public Taso(ArrayList<Kohde> kohteet, ArrayList<Seuraaja> seur, int raja){
        tasonKohteet = kohteet;
        tasonSeur = seur;
        rajoitus = raja;
    }
    
    public int getRaja() {
        return rajoitus;
    }
    
    public ArrayList getKohteet() {
        return tasonKohteet;
    }
    
    public ArrayList getSeur() {
        return tasonSeur;
    }
    
    
}
