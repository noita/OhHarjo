
package ohjharjoitus.syotteet;

import java.awt.event.*;
import javax.swing.*;
import ohjharjoitus.StrategiaPeli;

public class ValikonKuuntelija implements ActionListener{
    StrategiaPeli peli;
    
    public ValikonKuuntelija(StrategiaPeli peli){
        this.peli = peli;
    }
    
    public void actionPerformed(ActionEvent e){
        String valinta = ((JMenuItem)e.getSource()).getText();
        if (valinta.equals("Quit")){
            peli.suljePeli();
        }
        if (valinta.equals("New Game")){
            //alusta uusi peli
        }
    }
}
