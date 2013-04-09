
package ohjharjoitus.syotteet;

import java.awt.event.*;
import ohjharjoitus.Strategiapeli;

/**
 * Kuuntelee hiirtä.
 * 
 * @author O
 */
public class HiirenTiedot implements MouseListener{
    private Strategiapeli peli;
    
    public HiirenTiedot(Strategiapeli peli){
        this.peli = peli;
    }
    
    /**
     * Lisää uuden pommin klikattuun kohtaan, 
     * päivittää tilannetiedot ja grafiikan.
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String) 
     * 
     * @param e hiiritapahtuma
     */
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //int nappain = e.getButton();
        
        //rajataan arvot järkeviin
        if (x>350) x = 350;
        if (x<0) x = 0;
        if (y>350) y = 350;
        if (y<0) y = 0;
        
        if(peli.tilanne.getText().equals("click to continue")){
            //seuraava taso
            peli.alustaTaso(peli.nykyinenPeli, peli.nykyinenTaso);
        } else if (peli.tilanne.getText().equals("nope.")){
            //ei mitn.
        } else {
            //lisätään uusi elementti, päivitetään grafiikka
            peli.lisaaPommi(x, y);
            peli.grafiikka.repaint();
            peli.paivitaTilanne("new charge at " + x + " : " + y);
        }
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
