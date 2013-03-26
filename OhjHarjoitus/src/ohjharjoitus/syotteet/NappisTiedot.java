
package ohjharjoitus.syotteet;

import java.awt.event.*;
import ohjharjoitus.StrategiaPeli;

public class NappisTiedot implements KeyListener{
    private StrategiaPeli peli;

    public NappisTiedot(StrategiaPeli peli) {
        this.peli = peli;
    }
    
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
