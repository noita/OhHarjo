
package ohjharjoitus.syotteet;

import java.awt.event.*;
import ohjharjoitus.Strategiapeli;

/**
 * Kuuntelee näppäimistötapahtumia.
 * 
 * @author O
 */
public class NappisKuuntelija implements KeyListener{
    private Strategiapeli peli;

    public NappisKuuntelija(Strategiapeli peli) {
        this.peli = peli;
    }
    
    /**
     * Räjäyttää pommit mikäli painettu näppäin on 'a' ja 
     * uusii tason, jos painetttu näppäin on 'r'.
     * 
     * @see ohjharjoitus.Strategiapeli#rajayta() 
     * 
     * @param ke näppäimistötapahtuma
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        char merkki = ke.getKeyChar();
        if (merkki == 'a'){
            peli.rajayta();
        }
        if (merkki == 'r' && peli.pelitilanne() == 0){
            peli.toistaTaso();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
