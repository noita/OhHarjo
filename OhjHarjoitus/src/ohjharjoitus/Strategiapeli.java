
package ohjharjoitus;

import ohjharjoitus.syotteet.ValikonKuuntelija;
import ohjharjoitus.syotteet.NappisTiedot;
import ohjharjoitus.syotteet.HiirenTiedot;
import ohjharjoitus.kayttoliittyma.Grafiikka;
import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import ohjharjoitus.elementit.Viiva;
import java.awt.*;
import javax.swing.*;
import java.util.*;


/**
 * Pelin peruslogiikka; sisältää elementtien lisäämisen, poistamisen
 * ja niiden sijainnin päivittämisen, myös tasosta toiseen siirtyminen.
 * 
 * @author O
 */
public class Strategiapeli implements Runnable {
    private JFrame ikkuna;
    private NappisTiedot nappis = new NappisTiedot(this);
    private TasonLataus lataaja = new TasonLataus(this);
    public JLabel tilanne = new JLabel("click to begin");
    public Grafiikka grafiikka = new Grafiikka();
    public ArrayList<Kohde> kohteet = new ArrayList<Kohde>();
    public ArrayList<Pommi> pommit = new ArrayList<Pommi>();
    public ArrayList<Seuraaja> seuraajat = new ArrayList<Seuraaja>();
    public ArrayList<Viiva> viivat = new ArrayList<Viiva>();
    public Pistelaskin yhtPisteet = new Pistelaskin(666);
    public Pistelaskin tasonPisteet = new Pistelaskin(666);
    public int nykyinenTaso;
    public String nykyinenPeli = "perustasot";
    int rajaytykset = 0;
    int kombo = 0;
    
    
    public void run() {
        ikkuna = new JFrame("Some kinda Game"); 
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
        
        
        //alustetaan ensim. taso
        nykyinenTaso = 1;
        alustaTaso(nykyinenPeli, nykyinenTaso);
        //testitaso
        //alustaTestiTaso();
        
        
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
        
        JMenu tasoValikko = new JMenu("File");
        valikko.add(tasoValikko);
        
        JMenuItem valikkoLataa = new JMenuItem("Load stages");
        valikkoLataa.addActionListener(new ValikonKuuntelija(this));
        tasoValikko.add(valikkoLataa);
        
        ikkuna.setJMenuBar(valikko);
    }
    /**
     * Kysyy käyttäjältä ladattavien tasojen tiedostonimeä dialogi-ikkunan kautta.
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetSeur(java.lang.String, int) 
     */
    public void pyydaTaso() {
        String tasot = JOptionPane.showInputDialog(ikkuna, "Which stages?");
        
        if (lataaja.tarkistaOlemassaolo(tasot)){
            yhtPisteet.nollaaPisteet();
            tasonPisteet.nollaaPisteet();
        
            nykyinenPeli = tasot;
            nykyinenTaso = 1;
            alustaTaso(nykyinenPeli, nykyinenTaso);
        } else {
            JOptionPane.showMessageDialog(ikkuna, "File not found");
        }
    }
    
    /**
     * Päivittää pelitilanteen esittävän JLabelin
     * 
     * @param viesti päivitettävä tieto
     */
    public void paivitaTilanne(String viesti) {
        tilanne.setText(viesti);
    }
    
    /**
     * Alustaa testitason tyhjentämällä pelin 
     * elementtien listat ja lataamalla uudet sisällöt,
     * päivittää tilanteen ja grafiikan
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String) 
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetSeur(java.lang.String, int) 
     * 
     */
    public void alustaTestiTaso() {
        pommit.clear();
        kohteet.clear();
        seuraajat.clear();
        viivat.clear();
                
        tasonPisteet = new Pistelaskin(lataaja.lataaRaja("perustasot", 0));
        
        paivitaTilanne("click to begin");
        kohteet = lataaja.lataaUudetKohteet("perustasot", 0);
        seuraajat = lataaja.lataaUudetSeur("perustasot", 0);
        grafiikka.repaint();
    }
    
    /**
     * Alustaa uuden tason tyhjentämällä pelin 
     * elementtien listat ja lataamalla uudet sisällöt,
     * päivittää tilanteen ja grafiikan
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String)
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataus#lataaUudetSeur(java.lang.String, int) 
     * 
     * @param n ladattavan tason numero
     */
    public void alustaTaso(String tasot, int n) {
        if (n>lataaja.tasojenLkm(tasot) || n<0){
            paivitaTilanne("nope.");
            return;
        }
        
        //nollataan tilanne, alustetaan uusi kenttä
        pommit.clear();
        kohteet.clear();
        seuraajat.clear();
        viivat.clear();
        
        tasonPisteet = new Pistelaskin(lataaja.lataaRaja(tasot, n));
        
        paivitaTilanne("click to begin");
        kohteet = lataaja.lataaUudetKohteet(tasot, n);
        seuraajat = lataaja.lataaUudetSeur(tasot, n);
        grafiikka.repaint();
    }
        
    /**
     * Tyhjentää kohdelistan, luo satunnaisia kohteita
     * ja lisää nämä listaan
     * 
     * @param lkm luotavien kohteiden lukumäärä
     */
    public void luoSatunnaisetKohteet(int lkm){
        kohteet.clear();
        for (int i=0; i<lkm; i++){
            kohteet.add(new Kohde((int)(Math.random()*350), (int)(Math.random()*350)));
        }
    }
    
    /**
     * Tyhjentää seuraajalistan, luo satunnaisia seuraajia
     * ja lisää nämä listaan
     * 
     * @param lkm luotavien seuraajien lukumäärä
     */
    public void luoSatunnaisetSeuraajat(int lkm){
        seuraajat.clear();
        for (int i=0; i<lkm; i++){
            seuraajat.add(new Seuraaja((int)(Math.random()*350), (int)(Math.random()*350)));
        }
    }
    
    /**
     * Luo ja lisää pommilistaan uuden pommin
     * 
     * @param x lisättävän pommin x-koordinaatti
     * 
     * @param y lisättävän pommin y-koordinaatti
     */
    public void lisaaPommi(int x, int y) {
        //koordinaattirajoitukset
        if (x>350) x = 350;
        if (x<0) x = 0;
        if (y>350) y = 350;
        if (y<0) y = 0;
        //rajoitus käyttöön myöhemmin
        //if (pommit.size()<taso.getRaja()){ 
            pommit.add(new Pommi(x,y,100));
        //    paivitaTilanne("press 'a' to detonate");  
        //} else {
        //    paivitaTilanne("cannot place further charges") 
        //}
        laskeLiike();
    }
    
    /**
     * Käy läpi seuraajien listan ja laskee sijainnin muutoksen näille
     * 
     */
    public void laskeLiike() {
        int x = 0;
        int y = 0;
        for (Seuraaja s : seuraajat){
            int lahin = 999;
            for (Pommi p : pommit){
                int etaisyys = (int) Math.sqrt(Math.pow((p.getX()+p.getD()/2)-s.getX(), 2)+(Math.pow((p.getY()+p.getD()/2)-s.getY(), 2)));
                if (etaisyys<lahin){
                    lahin = etaisyys;
                    //piste, jota kohti liikutaan:
                    x = p.getX()-3 + p.getD()/2;
                    y = p.getY()-3 + p.getD()/2;
                }
                //törmätessä pommi poistetaan:
                if (etaisyys <= 30){
                    pommit.remove(p);
                    tasonPisteet.vahenna(2);
                    break;
                }
            }
            //sijainti ennen liikettä
            int x1 = s.getX();
            int y1 = s.getY();
            
            //liikkeen suunta
            if (x <= s.getX()){
                s.setX(s.getX() - Math.min(20, s.getX()-x));
            } else {
                s.setX(s.getX() + Math.min(20, x-s.getX()));
            }
            if (y <= s.getY()){
                s.setY(s.getY() - Math.min(20, s.getY()-y));
            } else {
                s.setY(s.getY() + Math.min(20, y-s.getY()));
            }
            
            //sijainti liikkeen jälkeen
            int x2 = s.getX();
            int y2 = s.getY();
            
            //lisätään viiva perään
            if (viivat.size()<seuraajat.size()*3){
                viivat.add(new Viiva(x1,y1,x2,y2));
            } else {
                viivat.remove(0);
                viivat.add(new Viiva(x1,y1,x2,y2));
            }
        }
    }
    
    /**
     * Päivittää tilanteen riippuen pommilistan tilasta,
     * kasvattaa käytettyjen räjäytysten määrää
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String)
     * 
     * @see ohjharjoitus.Strategiapeli#laskeOsumat() 
     * 
     */
    public void rajayta(){
        if (pommit.isEmpty()){
            paivitaTilanne("no charges placed!");
        } else {
            rajaytykset++;
            paivitaTilanne("charges detonated!");
        }
        laskeOsumat();
        grafiikka.repaint();
    }
    
    /**
     * Käy läpi pommilistan ja laskee osumat kohteisiin
     * mikäli taso läpäistään, laskee pisteet ja päivittää tilanteen
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String) 
     * 
     * @see ohjharjoitus.Strategiapeli#tarkistaLapaisy() 
     * 
     */
    public void laskeOsumat() {
        //int osumat = 0; 
        for (Pommi p : pommit){
            for (Kohde k : kohteet){
                int etaisyys = (int) Math.sqrt(Math.pow((p.getX()+p.getD()/2)-k.getX(), 2)+(Math.pow((p.getY()+p.getD()/2)-k.getY(), 2)));
                if (etaisyys<=p.getD()/2){
                    //osumat++;
                    k.tuhoa();
                }
            }
        }
        pommit.clear();
        if (tarkistaLapaisy()){
            tasonPisteet.lisaa(kohteet.size()/rajaytykset);
            rajaytykset = 0;
            kombo = 0;
            yhtPisteet.lisaa(tasonPisteet.getPisteet());
            nykyinenTaso++;
            paivitaTilanne("click to continue");
        }
    }
        
    /**
     * Tarkistaa onko kaikki tason kohteet tuhottu
     * 
     * @return läpäisyn totuusarvo
     */
    public boolean tarkistaLapaisy() {
        for (Kohde k : kohteet){
            if (!k.tuhottu()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Sulkee peli-ikkunan
     * 
     */
    public void suljePeli() {
        ikkuna.removeKeyListener(nappis);
        ikkuna.dispose();
    }
}
