
package ohjharjoitus.syotteet;

import java.awt.event.*;
import ohjharjoitus.StrategiaPeli;

public class HiirenTiedot implements MouseListener{
    private StrategiaPeli peli;
    
    public HiirenTiedot(StrategiaPeli peli){
        this.peli = peli;
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //int nappain = e.getButton();
        
        //rajataan arvot järkeviin
        if (x>350) x = 350;
        if (x<0) x = 0;
        if (y>350) y = 350;
        if (y<0) y = 0;
        
        
        //lisätään uusi elementti, päivitetään grafiikka
        peli.lisaaPommi(x, y);
        peli.grafiikka.repaint();
        peli.paivitaTilanne("new charge at " + x + " ; " + y);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
