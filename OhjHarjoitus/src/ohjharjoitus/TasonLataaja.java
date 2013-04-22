
package ohjharjoitus;

import java.io.*;
import java.util.*;
import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Seuraaja;

/**
 * Tason tietojen lataaminen tekstitiedostosta.
 * 
 * @author O
 */
public class TasonLataaja {
    Strategiapeli peli;
    ArrayList<Kohde> kohteita = new ArrayList<Kohde>();
    ArrayList<Seuraaja> seuraajia = new ArrayList<Seuraaja>();
    
    public TasonLataaja(Strategiapeli p) {
        peli = p;
    }
    
    /**
     * Tarkistaa montako tasoa tiedosto määrittelee.
     * 
     * @param tiedosto nimi tiedostolle, josta tasot ladataan
     * 
     * @return tasojen lukumäärä tiedostossa
     */
    public int tasojenLkm(String tiedosto) {
        int lkm = -1;
        String rivi = "-1";
        try {
            Scanner luku = new Scanner(new File(tiedosto + ".txt"));
            while (luku.hasNextLine()){
                rivi = luku.nextLine();
            }
            lkm = Integer.parseInt(rivi);
        } catch (Exception e){
            //System.out.println("joku kämmi; lkm: " + e.toString());
        }
        return lkm-1;
    }
    
    /**
     * Lataa halutun tason kohteet tiedostosta.
     * 
     * @param tiedosto nimi tiedostolle, josta tasot ladataan
     * 
     * @param monesko monesko taso tiedostosta ladataan
     * 
     * @return pyydetyn tason kohteet
     */
    public ArrayList<Kohde> lataaUudetKohteet(String tiedosto, int monesko) {
        kohteita.clear();
        String sana = "";
        try {
            Scanner luku = new Scanner(new File(tiedosto + ".txt"));
            while (!sana.equals("Taso " + monesko)){
                sana = luku.nextLine();
            }
            while (!sana.equals("seuraajat:")){
                int x = luku.nextInt();
                int y = luku.nextInt();
                kohteita.add(new Kohde(x,y));
                sana = luku.nextLine();
            }
        } catch (Exception e){
            //System.out.println("joku kämmi; kohteet: " + e.toString());
        }
        return kohteita;
    }
    
    /**
     * Lataa halutun tason seuraajat tiedostosta.
     * 
     * @param tiedosto nimi tiedostolle, josta tasot ladataan
     * 
     * @param monesko monesko taso tiedostosta ladataan
     * 
     * @return pyydetyn tason seuraajat
     */
    public ArrayList<Seuraaja> lataaUudetSeur(String tiedosto, int monesko) {
        seuraajia.clear();
        String sana = "jtn.";
        try {
            Scanner luku = new Scanner(new File(tiedosto + ".txt"));
            while (!sana.equals("Taso " + monesko)){
                sana = luku.nextLine();
            }
            while (!sana.equals("seuraajat:")){
                sana = luku.nextLine();
            }
            while (!sana.equals("loppu")){
                int x = luku.nextInt();
                int y = luku.nextInt();
                seuraajia.add(new Seuraaja(x,y));
                sana = luku.nextLine();
            }
        } catch (Exception e){
            //System.out.println("joku kämmi; seuraajat: " + e.toString());
        }
        return seuraajia;
    }
    
    /**
     * Hakee tiedostosta halutun tason pommien käytön rajoituksen.
     * 
     * @param tiedosto tasotiedoston nimi
     * 
     * @param monesko kyseessä olevan tason numero
     * 
     * @return tason rajoitus luku
     */
    public int lataaRaja(String tiedosto, int monesko) {
        String sana = "whatev";
        int raja = 999;
        try {
            Scanner luku = new Scanner(new File(tiedosto + ".txt"));
            while (!sana.equals("Taso " + monesko)){
                sana = luku.nextLine();
            }
            while (!sana.equals("raja:")){
                sana = luku.nextLine();
            }
            raja = luku.nextInt();
        } catch (Exception e){
            //System.out.println("joku kämmi; raja: " + e.toString());
        }
        return raja;
    }
    
    /**
     * Tarkistaa onko haluttu tiedosto olemassa.
     * 
     * @param tiedosto etsittävän tiedoston nimi
     * 
     * @return olemassaolon totuusarvo
     */
    public boolean tarkistaOlemassaolo(String tiedosto){
        try{
            File tied = new File(tiedosto + ".txt");
            return tied.exists();
        } catch (Exception e){
            return false;
        }
    }
    
}
