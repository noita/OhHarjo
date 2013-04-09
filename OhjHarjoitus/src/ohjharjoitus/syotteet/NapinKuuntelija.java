//tarvitaanko tätä?
package ohjharjoitus.syotteet;

import java.awt.event.*;
import javax.swing.*;
import ohjharjoitus.Strategiapeli;

/**
 * Kuuntelee nappeja.
 * 
 */
public class NapinKuuntelija implements ActionListener{
    private Strategiapeli peli;
    
    public NapinKuuntelija(Strategiapeli peli) {
        this.peli = peli;
    }
    
    public void actionPerformed(ActionEvent e){
        JButton nappi = ((JButton)e.getSource());
    }
}
