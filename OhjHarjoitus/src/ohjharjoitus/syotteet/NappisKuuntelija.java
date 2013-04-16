
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
     * Räjäyttää pommit mikäli painettu näppäin on 'a'.
     * 
     * @see ohjharjoitus.Strategiapeli#rajayta() 
     * 
     * @param ke näppäimistötapahtuma
     */
    public void keyPressed(KeyEvent ke) {
        char merkki = ke.getKeyChar();
        if (merkki == 'a'){
            peli.rajayta();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
