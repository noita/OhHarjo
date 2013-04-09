
package ohjharjoitus.elementit;


public class Seuraaja {
    int sijaintiX;
    int sijaintiY;
    
    public Seuraaja(int x, int y){
        sijaintiX = x-3;
        sijaintiY = y-3;
    }
    
    public int getX() {
        return sijaintiX;
    }
    
    public int getY() {
        return sijaintiY;
    }
    
    public void setX(int x) {
        sijaintiX = x;
    }
    
    public void setY(int y) {
        sijaintiY = y;
    }
}
