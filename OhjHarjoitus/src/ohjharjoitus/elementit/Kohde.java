
package ohjharjoitus.elementit;

public class Kohde {
    public int sijaintiX;
    public int sijaintiY;
    public boolean tuhottu = false;
    
    public Kohde(int x, int y){
        sijaintiX = x-2;
        sijaintiY = y-2;
    }
    
    public void asetaX(int x){
        sijaintiX = x-2;
    }
    
    public void asetaY(int y){
        sijaintiY = y-2;
    }
}
