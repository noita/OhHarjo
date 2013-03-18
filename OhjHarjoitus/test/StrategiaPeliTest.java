/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohjharjoitus.Pommi;
import ohjharjoitus.StrategiaPeli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author O
 */
public class StrategiaPeliTest {
    
    StrategiaPeli peli;
    
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
        peli = new StrategiaPeli();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void alustetaanOikeaMaaraKohteita(){
        //kunhan tasoja on määritelty
    }
    
    @Test
    public void alustetaanOikeaMaaraPommeja(){
        assertEquals(0,peli.pommit.size());
    }
    
    @Test
    public void luodaanOikeaMaaraKohteita(){
        peli.luoKohteet(20);
        assertEquals(20,peli.kohteet.size());
    }
    
    @Test
    public void luodaanOikeaMaaraKohteitaNolla(){
        peli.luoKohteet(0);
        assertEquals(0,peli.kohteet.size());
    }
    
    @Test
    public void tilannePaivittyyOikein(){
        peli.paivitaTilanne("Lorem Ipsum");
        assertEquals("Lorem Ipsum",peli.tilanne.getText());
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
        assertEquals(-40,p.sijaintiX);
        assertEquals(-40,p.sijaintiY);
    }
    
    @Test
    public void pommienYlarajatPatevat(){
        peli.pommit.clear();
        peli.lisaaPommi(543,2345);
        Pommi p = peli.pommit.get(0);
        assertEquals(310,p.sijaintiX);
        assertEquals(310,p.sijaintiY);
    }
    
    @Test
    public void kohteidenLuontiNegSyotteella(){
        peli.luoKohteet(-5);
        assertEquals(0,peli.kohteet.size());
    }
    
    @Test
    public void alustetaanOikeaLkmTestiKohteita(){
        peli.alustaTestiTaso();
        assertEquals(15,peli.kohteet.size());
    }
    
}
