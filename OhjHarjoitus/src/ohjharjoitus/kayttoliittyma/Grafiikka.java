
package ohjharjoitus.kayttoliittyma;

import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import ohjharjoitus.elementit.Viiva;
import java.awt.*;
import java.util.*;
import ohjharjoitus.Strategiapeli;

/**
 * Pelin grafiikan piirtäminen ja päivittäminen.
 * 
 * @author O
 */
public class Grafiikka extends Canvas{
    public Strategiapeli peli;
    private ArrayList<Color> varit = new ArrayList<Color>();

    
    public Grafiikka(){
        this.setFocusable(false);
    }
    
    /**
     * Piirtää pelin kohteet.
     * 
     * @param g Grafiikkaelementti
     */
    public void paint(Graphics g){
        uudetVarit();
        //this.setBackground(varit.get(0));
        g.setColor(varit.get(3));
                
        int tilanne = peli.pelitilanne();
        
        if (tilanne == 0){
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
            g.setColor(varit.get(1));
            g.drawOval(s.getX()-4, s.getY()-4, 8, 8);
        }
        
        for (Viiva v : peli.viivat){
            g.setColor(varit.get(1));
            g.drawLine(v.getX1(), v.getY1(), v.getX2(), v.getY2());
        }
        
        for (Kohde k : peli.kohteet){
            if (k.tuhottu()){
                g.setColor(varit.get(2));
                g.drawOval(k.getX()-2, k.getY()-2, 4, 4);
            } else {
                g.setColor(varit.get(3));
                g.drawOval(k.getX()-2, k.getY()-2, 4, 4);
            }
        }
        
        }
    }
    
    /**
     * Hakee uudet värit käytettäviksi.
     */
    public void uudetVarit(){
        varit = peli.getVarit();
    }
}
