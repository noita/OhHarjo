
package ohjharjoitus.elementit;

public class Kohde {
    int sijaintiX;
    int sijaintiY;
    boolean tuhottu = false;
    
    public Kohde(int x, int y){
        sijaintiX = x;
        sijaintiY = y;
    }
    
    public void asetaX(int x){
        sijaintiX = x;
    }
    
    public void asetaY(int y){
        sijaintiY = y;
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
