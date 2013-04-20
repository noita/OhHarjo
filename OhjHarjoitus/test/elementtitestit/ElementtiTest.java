/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elementtitestit;

import ohjharjoitus.elementit.Kohde;
import ohjharjoitus.elementit.Pommi;
import ohjharjoitus.elementit.Seuraaja;
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
public class ElementtiTest {
    
    public ElementtiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void pommiAsettaaOikein(){
        Pommi p = new Pommi(175,175,100);
        assertEquals(125, p.getX());
        assertEquals(125, p.getY());
        assertEquals(100, p.getD());
    }
    
    @Test
    public void seuraajaAsettaaOikein(){
        Seuraaja s =  new Seuraaja(10,10);
        assertEquals(10, s.getX());
        assertEquals(10, s.getY());
    }

    @Test
    public void kohdeAsettaaOikein(){
        Kohde k = new Kohde(100,100);
        assertEquals(100, k.getX());
        assertEquals(100, k.getY());
    }
}
