
package ohjharjoitus.elementit;
/**
 * Määrittelee kohde-elementit.
 * @author O
 */
public class Kohde {
    int sijaintiX;
    int sijaintiY;
    /**
     * Tieto siitä, onko kohde jo tuhottu.
     */
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
    /**
     * Muuttaa tuhottu-muuttujan true:ksi.
     */
    public void tuhoa() {
        tuhottu = true;
    }
}
