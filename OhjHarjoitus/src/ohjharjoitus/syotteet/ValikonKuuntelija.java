
package ohjharjoitus.syotteet;

import java.awt.event.*;
import javax.swing.*;
import ohjharjoitus.Strategiapeli;

public class ValikonKuuntelija implements ActionListener{
    Strategiapeli peli;
    
    public ValikonKuuntelija(Strategiapeli peli){
        this.peli = peli;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String valinta = ((JMenuItem)e.getSource()).getText();
        if (valinta.equals("Quit")){
            peli.suljePeli();
        }
        if (valinta.equals("New Game")){
            //alusta uusi peli
            peli.yhtPisteet.nollaaPisteet();
            peli.setNykyinenTaso(1);
            peli.setNykyinenPeli("perustasot");
            peli.alustaTaso("perustasot", 1);
        }
        if (valinta.equals("Load stages")){
            peli.pyydaTaso();
        }
    }
}
