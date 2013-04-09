
package ohjharjoitus.elementit;

/**
 *Määrittelee viivaelementit
 * 
 */
public class Viiva {
    int x1;
    int y1;
    int x2;
    int y2;
    
    public Viiva(int x1, int y1, int x2, int y2){
        this.x1 = x1+2;
        this.y1 = y1+2;
        this.x2 = x2+2;
        this.y2 = y2+2;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getX2(){
        return x2;
    }
    
    public int getY2(){
        return y2;
    }
}
