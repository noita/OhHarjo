/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikkatestit;

import ohjharjoitus.Pistelaskin;
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
public class PistelaskinTest {
    
    Pistelaskin pistelaskin;
    
    public PistelaskinTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pistelaskin = new Pistelaskin();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void pisteetAlussaNolla(){
        assertEquals(0,pistelaskin.getPisteet());
    }
    
    @Test
    public void pisteetKasvavatOikein(){
        pistelaskin.lisaa(4);
        pistelaskin.lisaa(0);
        pistelaskin.lisaa(9);
        assertEquals(13, pistelaskin.getPisteet());
    }
    
    @Test
    public void pisteetVahenevatOikein(){
        pistelaskin.vahenna(5);
        pistelaskin.vahenna(-5);
        pistelaskin.vahenna(10);
        assertEquals(-10, pistelaskin.getPisteet());
    }
    
    @Test
    public void pisteetNollautuvat(){
        pistelaskin.lisaa(13);
        pistelaskin.nollaaPisteet();
        assertEquals(0, pistelaskin.getPisteet());
    }

}
