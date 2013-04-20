

import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import ohjharjoitus.elementit.Seuraaja;
import ohjharjoitus.Strategiapeli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StrategiaPeliTest {
    
    Strategiapeli peli;
    
    public StrategiaPeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        peli = new Strategiapeli();
    }
    
    @After
    public void tearDown() {
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    @Test
    public void alustetaanOikeaMaaraKohteita(){
        peli.alustaTaso("perustasot", 1);
        assertEquals(4, peli.kohteet.size());
    }
    
    @Test
    public void alustetaanOikeaMaaraPommeja(){
        assertEquals(0,peli.pommit.size());
    }
    
    @Test
    public void luodaanOikeaMaaraKohteita(){
        peli.luoSatunnaisetKohteet(20);
        assertEquals(20,peli.kohteet.size());
    }
    
    @Test
    public void luodaanOikeaMaaraKohteitaNolla(){
        peli.luoSatunnaisetKohteet(0);
        assertEquals(0,peli.kohteet.size());
    }
    
    @Test
    public void tilannePaivittyyOikein(){
        peli.paivitaTilanne("Lorem Ipsum");
        assertEquals(2, peli.pelitilanne());
    }
    
    @Test
    public void pomminLisaysToimii(){
        int lkm = peli.pommit.size();
        peli.lisaaPommi(10, 10);
        peli.lisaaPommi(15, 15);
        peli.lisaaPommi(20, 20);
        assertEquals(lkm+3,peli.pommit.size());
    }
    
    @Test
    public void pommienAlarajatPatevat(){
        peli.pommit.clear();
        peli.lisaaPommi(-10,-20);
        Pommi p = peli.pommit.get(0);
        assertEquals(-50,p.getX());
        assertEquals(-50,p.getY());
    }
    
    @Test
    public void pommienYlarajatPatevat(){
        peli.pommit.clear();
        peli.lisaaPommi(543,2345);
        Pommi p = peli.pommit.get(0);
        assertEquals(300,p.getX());
        assertEquals(300,p.getY());
    }
    
    @Test
    public void kohteidenLuontiNegSyotteella(){
        peli.luoSatunnaisetKohteet(-5);
        assertEquals(0,peli.kohteet.size());
    }
    
    @Test
    public void alustetaanOikeaLkmTestiKohteita(){
        peli.alustaTestiTaso();
        assertEquals(6,peli.kohteet.size());
    }
    
    @Test
    public void alustetaanOikeaLkmTestiSeuraajia(){
        peli.alustaTestiTaso();
        assertEquals(6,peli.seuraajat.size());
    }
    
    @Test
    public void alustetaanOikeaLkmSeur(){
        peli.luoSatunnaisetSeuraajat(34);
        assertEquals(34,peli.seuraajat.size());
    }
    
    @Test
    public void seuraajienLuontiNegSyote(){
        peli.luoSatunnaisetSeuraajat(-12);
        assertEquals(0,peli.seuraajat.size());
    }
    
    @Test 
    public void pomminLisaysSeurPaalle(){
        peli.seuraajat.add(new Seuraaja(150, 150));
        peli.lisaaPommi(150, 150);
        assertEquals(0,peli.pommit.size());
    }
    
    @Test 
    public void pomminLisaysSeurLahelle(){
        peli.seuraajat.add(new Seuraaja(150, 150));
        peli.lisaaPommi(153, 149);
        assertEquals(0,peli.pommit.size());
    }
    
    @Test 
    public void tyhjanRajaytys(){
        peli.luoSatunnaisetKohteet(1);
        peli.rajayta();
        assertEquals(2, peli.pelitilanne());
    }
    
    @Test
    public void rajaytysPaivitys(){
        peli.kohteet.add(new Kohde(1,1));
        peli.lisaaPommi(150, 150);
        peli.rajayta();
        assertEquals(2, peli.pelitilanne());
    }
    
    @Test
    public void rajaytysPaivitysOsuma(){
        peli.kohteet.add(new Kohde(150,150));
        peli.lisaaPommi(150, 150);
        peli.rajayta();
        assertEquals(0, peli.pelitilanne());
    }
    
    @Test
    public void liianKorkeaTaso(){
        peli.alustaTaso("perustasot", 99);
        assertEquals(1, peli.pelitilanne());
    }
    
    @Test
    public void negTasonumero(){
        peli.alustaTaso("perustasot", -1);
        assertEquals(1, peli.pelitilanne());
    }
    
    @Test
    public void varitPalautuvatOikein(){
        peli.vaihdaVarit();
        assertEquals(4, peli.getVarit().size());
    }
}
