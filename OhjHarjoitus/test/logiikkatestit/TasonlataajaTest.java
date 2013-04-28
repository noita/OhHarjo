/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikkatestit;

import ohjharjoitus.Strategiapeli;
import ohjharjoitus.TasonLataaja;
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
public class TasonlataajaTest {
    
    Strategiapeli peli;
    TasonLataaja lataaja;
    
    public TasonlataajaTest() {
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
        lataaja = new TasonLataaja(peli);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tasojenLkmPalautuuOikein(){
        int n = lataaja.tasojenLkm("perustasot");
        assertEquals(6, n);
    }
    
    @Test
    public void tasojenLkmVaarallaNimella(){
        int n = lataaja.tasojenLkm("asdf");
        assertEquals(0, n);
    }
    
    @Test
    public void tasonSeuraajienLataus(){
        assertEquals(6, lataaja.lataaUudetSeur("perustasot", 0).size());
    }
    
    @Test
    public void seuraajienLatausVaaraNimi(){
        assertEquals(0, lataaja.lataaUudetSeur("asdf", 0).size());
    }
    
    @Test
    public void rajanLataus(){
        assertEquals(666, lataaja.lataaRaja("perustasot", 0));
    }
    
    @Test
    public void rajanLatausVaaraNimi(){
        assertEquals(0, lataaja.lataaRaja("asdf", 0));
    }
    
    @Test
    public void olemassaOloTrue(){
        assertTrue(lataaja.tarkistaOlemassaolo("perustasot"));
    }
    
    @Test
    public void olemassaOloFalse(){
        assertFalse(lataaja.tarkistaOlemassaolo("asdf"));
    }
}
