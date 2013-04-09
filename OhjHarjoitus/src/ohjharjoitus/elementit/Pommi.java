
package ohjharjoitus.elementit;


public class Pommi {
    int sijaintiX;
    int sijaintiY;
    int lavistaja; //räjähdyksen koko
    
    
    public Pommi(int x, int y, int z){
        sijaintiX = x-z/2;
        sijaintiY = y-z/2;
        lavistaja = z;
    }   
    
    public int getX() {
        return sijaintiX;
    }
    
    public int getY() {
        return sijaintiY;
    }
    
    public int getD() {
        return lavistaja;
    }
    
    public void setX(int x) {
        sijaintiX = x;
    }
    
    public void setY(int y) {
        sijaintiY = y;
    }
    
    public void setD(int d) {
        lavistaja = d;
    }
    
}
