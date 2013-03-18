//
//Pelin peruslogiikka.
//Mitä nimeen tulee... mikä ihmeen yhdys sana?
//

package ohjharjoitus;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class StrategiaPeli implements Runnable {
    private JFrame ikkuna;
    private Taso taso;
    public JLabel tilanne = new JLabel("click to begin");
    public Grafiikka grafiikka = new Grafiikka(175, 150);
    public ArrayList<Kohde> kohteet = new ArrayList<Kohde>();
    public ArrayList<Pommi> pommit = new ArrayList<Pommi>();
    
    public void run() {
        ikkuna = new JFrame("Peli?"); //muutetaan vielä
        ikkuna.setPreferredSize(new Dimension(350, 410));
        ikkuna.setResizable(false);
        ikkuna.setBackground(Color.BLACK);
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoValikko();
        
        grafiikka.peli = this;
        grafiikka.addMouseListener(new HiirenTiedot(this));
        
        luoAlkutilanne();
        
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
    private void luoAlkutilanne() {
        Container pohja = ikkuna.getContentPane();
        pohja.setLayout(new BoxLayout(pohja, BoxLayout.Y_AXIS));
        
        tilanne.setAlignmentX(Component.CENTER_ALIGNMENT);
        tilanne.setForeground(Color.GREEN);
        
        //alustetaan testikenttä
        alustaTestiTaso();
        //korvataan oikean tason lataamisella myöhemmin
        
        pohja.add(grafiikka);
        pohja.add(tilanne);
    }
    
    private void luoValikko(){
        JMenuBar valikko = new JMenuBar();
        valikko.setBorderPainted(false);
        
        JMenu peliValikko = new JMenu("Game");
        valikko.add(peliValikko);
        
        JMenuItem valikkoUusi = new JMenuItem("New Game");
        valikkoUusi.addActionListener(new ValikonKuuntelija(this));
        peliValikko.add(valikkoUusi);
        
        JMenuItem valikkoLopeta = new JMenuItem("Quit");
        valikkoLopeta.addActionListener(new ValikonKuuntelija(this));
        peliValikko.add(valikkoLopeta);
        
        ikkuna.setJMenuBar(valikko);
    }
    
    public void paivitaTilanne(String viesti) {
        tilanne.setText(viesti);
    }
    
    //testausta varten
    public void alustaTestiTaso() {
        pommit.clear();
        kohteet.clear();
        luoKohteet(15);
    }
    
    public void alustaTaso(Taso taso) {
        //nollataan tilanne, alustetaan uusi kenttä
        pommit.clear();
        kohteet.clear();
        kohteet = taso.tasonKohteet;
    }
    
    
    //satunnaisia kohteita testikenttää varten.
    public void luoKohteet(int x){
        kohteet.clear();
        for (int i=0; i<x; i++){
            kohteet.add(new Kohde((int)(Math.random()*350), (int)(Math.random()*300)));
        }
    }
    
    public void lisaaPommi(int x, int y) {
        //koordinaattirajoitukset
        if (x>350) x = 350;
        if (x<0) x = 0;
        if (y>350) y = 350;
        if (y<0) y = 0;
        //rajoitus käyttöön myöhemmin
        //if (pommit.size()<taso.rajoitus){ 
            pommit.add(new Pommi(x,y,80));
        //    paivitaTilanne("press 'a' to detonate");  
        //} else {
        //    paivitaTilanne("cannot place further charges") 
        //}
    }
    
    public void laskeMuutokset(int x, int y) {
        //laskee liikkeet liikkuville kohteille
    }
    
    public void suljePeli() {
        //poista näppis
        ikkuna.dispose();
    }
}
