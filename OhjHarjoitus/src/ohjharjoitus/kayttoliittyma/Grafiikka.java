
package ohjharjoitus;

import java.awt.*;

public class Grafiikka extends Canvas{
    public int[] viimSijainti = {0,0};
    public StrategiaPeli peli;
    
    
    public Grafiikka(int x, int y){
        viimSijainti[0] = x;
        viimSijainti[1] = y;
    }
    
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        
        for (Pommi p: peli.pommit){
            g.drawOval(p.sijaintiX, p.sijaintiY, p.lavistaja, p.lavistaja);
        }
        
        for (Kohde k : peli.kohteet){
            g.drawOval(k.sijaintiX-2, k.sijaintiY-2, 4, 4);
        }
    }

}
