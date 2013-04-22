
package ohjharjoitus;

import ohjharjoitus.kayttoliittyma.Varinarpoja;
import ohjharjoitus.syotteet.ValikonKuuntelija;
import ohjharjoitus.syotteet.NappisKuuntelija;
import ohjharjoitus.syotteet.HiirenKuuntelija;
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
    private JFrame ikkuna = new JFrame("A Game");
    private NappisKuuntelija nappis = new NappisKuuntelija(this);
    private TasonLataaja lataaja = new TasonLataaja(this);
    private ArrayList<Color> varit= new ArrayList<Color>();
    private Varinarpoja varinArpoja = new Varinarpoja();
    private JLabel tilanne = new JLabel("click to begin");
    public Grafiikka grafiikka = new Grafiikka();
    public ArrayList<Kohde> kohteet = new ArrayList<Kohde>();
    public ArrayList<Pommi> pommit = new ArrayList<Pommi>();
    public ArrayList<Seuraaja> seuraajat = new ArrayList<Seuraaja>();
    public ArrayList<Viiva> viivat = new ArrayList<Viiva>();
    public Pistelaskin yhtPisteet = new Pistelaskin();
    public Pistelaskin tasonPisteet = new Pistelaskin();
    /**
     * Nykyisen tason raja käytettäville pommeille.
     */
    private int nykyinenRaja;
    /**
     * Nykyisen tason numero.
     */
    private int nykyinenTaso;
    /**
     * Nykyisen pelitiedoston nimi.
     */
    private String nykyinenPeli = "perustasot";
    /**
     * Tasossa käytettyjen räjäytysten lukumäärä.
     */
    private int rajaytykset = 0;
    /**
     * Tasossa käytettyjen pommien lukumäärä.
     */
    private int kaytetyt = 0;
    
    
    @Override
    public void run() {
        ikkuna.setPreferredSize(new Dimension(350, 410));
        ikkuna.setResizable(false);
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        vaihdaVarit();
        luoValikko();
        
        grafiikka.peli = this;
        grafiikka.addMouseListener(new HiirenKuuntelija(this));
        
        luoAlkutilanne();
        
        ikkuna.pack();
        ikkuna.setVisible(true);
        ikkuna.addKeyListener(nappis);
    }   
    
    /**
     * Lisää peli-ikkunaan objektit ja lataa ensimmäisen tason.
     * 
     * @see ohjharjoitus.Strategiapeli#alustaTaso(java.lang.String, int) 
     */
    private void luoAlkutilanne() {
        Container pohja = ikkuna.getContentPane();
        pohja.setLayout(new BoxLayout(pohja, BoxLayout.Y_AXIS));
        
        tilanne.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        //alustetaan ensim. taso
        setNykyinenTaso(1);
        alustaTaso(nykyinenPeli, nykyinenTaso);
        
        pohja.add(tilanne);
        pohja.add(grafiikka);
    }
    
    /**
     * Luo ikkunaan valikon.
     */
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
     * Hakee uudet käyttöliittymässä käytettävät värit ja vaihtaa ikkunan värit.
     * 
     * @see ohjharjoitus.Varinarpoja#arvoVarit() 
     */
    public void vaihdaVarit(){
        varit = varinArpoja.arvoVarit();
        ikkuna.setBackground(varit.get(0));
        tilanne.setForeground(varit.get(3));
    }
    
    public ArrayList<Color> getVarit(){
        return varit;
    }
    
    /**
     * Tyhjentää pelin elementtien listat.
     */
    public void tyhjennaListat(){
        pommit.clear();
        seuraajat.clear();
        viivat.clear();
        kohteet.clear();
    }
    
    public void setNykyinenTaso(int x){
        nykyinenTaso = x;
    }
    
    public void setNykyinenPeli(String peli){
        nykyinenPeli = peli;
    }
    
    /**
     * Kysyy käyttäjältä ladattavien tasojen tiedostonimeä dialogi-ikkunan kautta.
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetSeur(java.lang.String, int) 
     */
    public void pyydaTaso() {
        String tasot = JOptionPane.showInputDialog(ikkuna, "Which stages?");
        
        if (lataaja.tarkistaOlemassaolo(tasot)){
            yhtPisteet.nollaaPisteet();
            tasonPisteet.nollaaPisteet();
        
            setNykyinenPeli(tasot);
            setNykyinenTaso(1);
            alustaTaso(nykyinenPeli, nykyinenTaso);
        } else {
            JOptionPane.showMessageDialog(ikkuna, "File not found");
        }
    }
    
    /**
     * Päivittää pelitilanteen esittävän JLabelin.
     * 
     * @param viesti päivitettävä tieto
     */
    public void paivitaTilanne(String viesti) {
        tilanne.setText(viesti);
    }
    
    /**
     * Kertoo hiirelle missä tilanteessa pelissä ollaan.
     * 
     * @return lukukoodi tilanteelle
     */
    public int pelitilanne(){
        if (tilanne.getText().equals("click to continue")){
            return 0;
        } else if(tilanne.getText().equals("Game Over")){
            return 1;
        } else {
            return 2;
        }
    }
    
    /**
     * Alustaa testitason tyhjentämällä pelin 
     * elementtien listat ja lataamalla uudet sisällöt,
     * päivittää tilanteen ja grafiikan.
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String) 
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetSeur(java.lang.String, int) 
     * 
     */
    public void alustaTestiTaso() {
        tyhjennaListat();
                
        tasonPisteet = new Pistelaskin();
        
        paivitaTilanne("click to begin");
        kohteet = lataaja.lataaUudetKohteet("perustasot", 0);
        seuraajat = lataaja.lataaUudetSeur("perustasot", 0);
        grafiikka.repaint();
    }
    
    /**
     * Alustaa uuden tason tyhjentämällä pelin 
     * elementtien listat ja lataamalla uudet sisällöt,
     * päivittää tilanteen ja grafiikan.
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String)
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetKohteet(java.lang.String, int) 
     * 
     * @see ohjharjoitus.TasonLataaja#lataaUudetSeur(java.lang.String, int) 
     * 
     * @param n ladattavan tason numero
     */
    public void alustaTaso(String tasot, int n) {
        if (n>lataaja.tasojenLkm(tasot) || n<0){
            paivitaTilanne("Game Over");
            naytaTulos();
            return;
        }
        
        //nollataan tilanne, alustetaan uusi kenttä
        tyhjennaListat();
        
        tasonPisteet = new Pistelaskin();
        nykyinenRaja = lataaja.lataaRaja(tasot, n);
        
        paivitaTilanne("click to begin");
        kohteet = lataaja.lataaUudetKohteet(tasot, n);
        seuraajat = lataaja.lataaUudetSeur(tasot, n);
        
        //arvotaan väriskeema
        vaihdaVarit();
        grafiikka.repaint();
    }
        
    /**
     * Tyhjentää kohdelistan, luo satunnaisia kohteita
     * ja lisää nämä listaan.
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
     * ja lisää nämä listaan.
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
     * Luo ja lisää pommilistaan uuden pommin.
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
 
        pommit.add(new Pommi(x,y,100));
        
        kaytetyt++;
        if (kaytetyt>nykyinenRaja){
            tasonPisteet.vahenna(1);
        }
        laskeLiike();
    }
    
    /**
     * Käy läpi seuraajien listan ja laskee sijainnin muutoksen näille.
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
                if (etaisyys <= 40){
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
                s.setX(s.getX() - Math.min(30, s.getX()-x));
            } else {
                s.setX(s.getX() + Math.min(30, x-s.getX()));
            }
            if (y <= s.getY()){
                s.setY(s.getY() - Math.min(30, s.getY()-y));
            } else {
                s.setY(s.getY() + Math.min(30, y-s.getY()));
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
     * kasvattaa käytettyjen räjäytysten määrää.
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
     * mikäli taso läpäistään, laskee pisteet ja päivittää tilanteen.
     * 
     * @see ohjharjoitus.Strategiapeli#paivitaTilanne(java.lang.String) 
     * 
     * @see ohjharjoitus.Strategiapeli#tarkistaLapaisy() 
     * 
     */
    public void laskeOsumat() {
        for (Pommi p : pommit){
            for (Kohde k : kohteet){
                int etaisyys = (int) Math.sqrt(Math.pow((p.getX()+p.getD()/2)-k.getX(), 2)+(Math.pow((p.getY()+p.getD()/2)-k.getY(), 2)));
                if (etaisyys<=p.getD()/2){
                    k.tuhoa();
                }
            }
        }
        pommit.clear();
        if (tarkistaLapaisy()){
            tasonPisteet.lisaa(kohteet.size()/rajaytykset);
            rajaytykset = 0;
            kaytetyt = 0;
            yhtPisteet.lisaa(tasonPisteet.getPisteet());
            paivitaTilanne("click to continue");
        }
    }
    
    /**
     * Kasvattaa tason numeroa, kutsuu uuden tason alustusta.
     * 
     * @see ohjharjoitus.Strategiapeli#alustaTaso(java.lang.String, int) 
     */
    public void seuraavaTaso(){
        nykyinenTaso++;
        alustaTaso(nykyinenPeli, nykyinenTaso);
    }
        
    /**
     * Tarkistaa onko kaikki tason kohteet tuhottu.
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
     * Näyttää loppuneen pelin tuloksen, kysyy aloitetaanko uusi peli.
     */
    public void naytaTulos(){
        int x = JOptionPane.showConfirmDialog(ikkuna, "Your score: " + yhtPisteet.pisteet + "\nStart new game?", "Game Over", 2);
        if (x == 0){
            yhtPisteet.nollaaPisteet();
            setNykyinenTaso(1);
            setNykyinenPeli("perustasot");
            alustaTaso("perustasot", 1);
        }
    }
    
    /**
     * Sulkee peli-ikkunan ja tätä myötä koko pelin.
     * 
     */
    public void suljePeli() {
        ikkuna.removeKeyListener(nappis);
        ikkuna.dispose();
    }
}
