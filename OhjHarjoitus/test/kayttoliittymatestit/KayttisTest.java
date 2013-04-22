/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymatestit;

import java.awt.Color;
import java.util.ArrayList;
import ohjharjoitus.kayttoliittyma.Varinarpoja;
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
public class KayttisTest {
    
    Varinarpoja varinarpoja;
    
    public KayttisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        varinarpoja = new Varinarpoja();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void oikeaLkmVareja(){
        ArrayList<Color> varit = varinarpoja.arvoVarit();
        assertEquals(4, varit.size());
    }
    
}
