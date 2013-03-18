
package ohjharjoitus;

import javax.swing.*;

public class OhjHarjoitus {
    

    public static void main(String[] args) {
        StrategiaPeli peli = new StrategiaPeli();
        SwingUtilities.invokeLater(peli);
    }
}
