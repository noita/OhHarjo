
package ohjharjoitus.kayttoliittyma;

import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import java.awt.*;
import ohjharjoitus.StrategiaPeli;

public class Grafiikka extends Canvas{
    public StrategiaPeli peli;
    
    
    public Grafiikka(){
        this.setFocusable(false);
    }
    
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        
        for (Pommi p: peli.pommit){
            g.drawOval(p.sijaintiX, p.sijaintiY, p.lavistaja, p.lavistaja);
        }
        
        for (Seuraaja s : peli.seuraajat){
            g.setColor(Color.YELLOW);
            g.drawOval(s.sijaintiX, s.sijaintiY, 6, 6);
        }
        
        for (Kohde k : peli.kohteet){
            if (k.tuhottu){
                g.setColor(Color.MAGENTA);
                g.drawOval(k.sijaintiX, k.sijaintiY, 4, 4);
            } else {
                g.setColor(Color.GREEN);
                g.drawOval(k.sijaintiX, k.sijaintiY, 4, 4);
            }
        }
    }

}
