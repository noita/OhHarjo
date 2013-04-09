
package ohjharjoitus.elementit;

public class Kohde {
    int sijaintiX;
    int sijaintiY;
    boolean tuhottu = false;
    
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
    
    public int getX() {
        return sijaintiX;
    }
    
    public int getY() {
        return sijaintiY;
    }
    
    public boolean tuhottu() {
        return tuhottu;
    }
    
    public void tuhoa() {
        tuhottu = true;
    }
}
