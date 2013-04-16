
package ohjharjoitus.kayttoliittyma;

import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import ohjharjoitus.elementit.Viiva;
import java.awt.*;
import ohjharjoitus.Strategiapeli;

/**
 * Pelin grafiikan piirtäminen ja päivittäminen.
 * 
 * @author O
 */
public class Grafiikka extends Canvas{
    public Strategiapeli peli;

    
    public Grafiikka(){
        this.setFocusable(false);
    }
    
    /**
     * Piirtää pelin kohteet.
     * 
     * @param g Grafiikka elementti
     */
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        
        if (peli.tilanne.getText().equals("click to continue")){
            //helvetin ruma!!!!
            g.drawString("stage cleared", 125, 70);
            g.drawString("stage score: " + peli.tasonPisteet.getPisteet(), 125, 90);
            g.drawString("total score: " + peli.yhtPisteet.getPisteet(), 125, 105);
        } else {
        
        for (Pommi p: peli.pommit){
            g.drawLine(p.getX()+p.getD()/2-3, p.getY()+p.getD()/2-3, p.getX()+p.getD()/2+3, p.getY()+p.getD()/2+3);
            g.drawLine(p.getX()+p.getD()/2-3, p.getY()+p.getD()/2+3, p.getX()+p.getD()/2+3, p.getY()+p.getD()/2-3);
            g.drawOval(p.getX(), p.getY(), p.getD(), p.getD());
        }
        
        for (Seuraaja s : peli.seuraajat){
            g.setColor(Color.YELLOW);
            g.drawOval(s.getX()-4, s.getY()-4, 8, 8);
        }
        
        for (Viiva v : peli.viivat){
            g.setColor(Color.YELLOW);
            g.drawLine(v.getX1(), v.getY1(), v.getX2(), v.getY2());
        }
        
        for (Kohde k : peli.kohteet){
            if (k.tuhottu()){
                g.setColor(Color.MAGENTA);
                g.drawOval(k.getX()-2, k.getY()-2, 4, 4);
            } else {
                g.setColor(Color.GREEN);
                g.drawOval(k.getX()-2, k.getY()-2, 4, 4);
            }
        }
        
        }
    }
}
