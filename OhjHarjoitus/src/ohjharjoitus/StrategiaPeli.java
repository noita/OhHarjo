//
//Pelin peruslogiikka.
//Mitä nimeen tulee... mikä ihmeen yhdys sana?
//

package ohjharjoitus;

import ohjharjoitus.syotteet.ValikonKuuntelija;
import ohjharjoitus.syotteet.NappisTiedot;
import ohjharjoitus.syotteet.HiirenTiedot;
import ohjharjoitus.kayttoliittyma.Grafiikka;
import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class StrategiaPeli implements Runnable {
    private JFrame ikkuna;
    private Taso taso;
    private NappisTiedot nappis = new NappisTiedot(this);
    public JLabel tilanne = new JLabel("click to begin");
    public Grafiikka grafiikka = new Grafiikka();
    public ArrayList<Kohde> kohteet = new ArrayList<Kohde>();
    public ArrayList<Pommi> pommit = new ArrayList<Pommi>();
    public ArrayList<Seuraaja> seuraajat = new ArrayList<Seuraaja>();
    public Pistelaskin yhtPisteet = new Pistelaskin(0);
    
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
        ikkuna.addKeyListener(nappis);
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
        seuraajat.clear();
        luoKohteet(15);
        luoSeuraajat(3);
    }
    
    public void alustaTaso(Taso taso) {
        //nollataan tilanne, alustetaan uusi kenttä
        pommit.clear();
        kohteet.clear();
        seuraajat.clear();
        
        
        Pistelaskin tasonPisteet = new Pistelaskin(taso.rajoitus);
        
        kohteet = taso.tasonKohteet;
        seuraajat = taso.tasonSeur;
    }
    
    
    //satunnaisia kohteita testikenttää varten.
    public void luoKohteet(int lkm){
        kohteet.clear();
        for (int i=0; i<lkm; i++){
            kohteet.add(new Kohde((int)(Math.random()*350), (int)(Math.random()*350)));
        }
    }
    
    //satunnaisia seuraajia
    public void luoSeuraajat(int lkm){
        seuraajat.clear();
        for (int i=0; i<lkm; i++){
            seuraajat.add(new Seuraaja((int)(Math.random()*350), (int)(Math.random()*350)));
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
            pommit.add(new Pommi(x,y,40));
        //    paivitaTilanne("press 'a' to detonate");  
        //} else {
        //    paivitaTilanne("cannot place further charges") 
        //}
        laskeLiike();
    }
    
    public void laskeLiike() {
        int x = 0;
        int y = 0;
        for (Seuraaja s : seuraajat){
            int lahin = 700;
            for (Pommi p : pommit){
                int etaisyys = (int) Math.sqrt(Math.pow((p.sijaintiX+p.lavistaja/2)-s.sijaintiX, 2)+(Math.pow((p.sijaintiY+p.lavistaja/2)-s.sijaintiY, 2)));
                if (etaisyys<lahin){
                    lahin = etaisyys;
                    //piste, jota kohti liikutaan:
                    x = p.sijaintiX-3 + p.lavistaja/2;
                    y = p.sijaintiY-3 + p.lavistaja/2;
                }
                //törmätessä pommi poistetaan:
                if (etaisyys <= p.lavistaja/2 + 20){
                    pommit.remove(p);
                    break;
                }
            }
            //liikkeen suunta
            if (x <= s.sijaintiX){
                s.sijaintiX -= Math.min(20, s.sijaintiX-x);
            } else {
                s.sijaintiX += Math.min(20, x-s.sijaintiX);
            }
            if (y <= s.sijaintiY){
                s.sijaintiY -= Math.min(20, s.sijaintiY-y);
            } else {
                s.sijaintiY += Math.min(20, y-s.sijaintiY);
            }
        }
        
    }
    
    public void rajayta(){
        if (pommit.isEmpty()){
            paivitaTilanne("no charges placed!");
        } else {
            for (Pommi p : pommit){
                int boom = 60;//räjähdyksen laajuus
                p.lavistaja += boom;
                p.sijaintiX -= boom/2;
                p.sijaintiY -= boom/2;
            }
            paivitaTilanne("charges detonated!");
        }
        laskeOsumat();
        grafiikka.repaint();
    }
    
    public void laskeOsumat() {
        for (Pommi p : pommit){
            for (Kohde k : kohteet){
                int etaisyys = (int) Math.sqrt(Math.pow((p.sijaintiX+p.lavistaja/2)-k.sijaintiX, 2)+(Math.pow((p.sijaintiY+p.lavistaja/2)-k.sijaintiY, 2)));
                if (etaisyys<=p.lavistaja/2){
                    k.tuhottu = true;
                }
            }
        }
        pommit.clear();
        if (tarkistaLapaisy()){
            paivitaTilanne("all targets cleared!");
            //tähän tuloksen esitys ja seuraavan tason lataus
        }
    }
    
    //tarkistaa, onko tason läpäisyvaatimukset täytetty
    public boolean tarkistaLapaisy(){
        for (Kohde k : kohteet){
            if (!k.tuhottu){
                return false;
            }
        }
        return true;
    }
    
    public void suljePeli() {
        ikkuna.removeKeyListener(nappis);
        ikkuna.dispose();
    }
}
