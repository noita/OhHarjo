
package ohjharjoitus.elementit;


public class Pommi {
    public int sijaintiX;
    public int sijaintiY;
    public int lavistaja; //räjähdyksen koko
    
    
    public Pommi(int x, int y, int z){
        sijaintiX = x-z/2;
        sijaintiY = y-z/2;
        lavistaja = z;
    }   
    
}
