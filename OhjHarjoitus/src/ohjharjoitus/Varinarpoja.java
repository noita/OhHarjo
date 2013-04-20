
package ohjharjoitus;

import java.util.*;
import java.awt.*;

/**
 * Arpoo pelin grafiikassa käytettävät värit.
 * 
 * @author O
 */
public class Varinarpoja {
    Strategiapeli peli;
    
    public Varinarpoja(Strategiapeli peli){
        this.peli = peli;
    }
    
    /**
     * Arpoo väriskeeman satunnaisesti.
     * 
     * @return ArrayList<Color>  värit sisältävä lista
     */
    public ArrayList<Color> arvoVarit(){
        ArrayList<Color> varit = new ArrayList<Color>();
        int arpa = new Random().nextInt(8);
        if(arpa==0){
            varit.add(new Color(166, 34, 142));
            varit.add(new Color(242, 170, 204));
            varit.add(new Color(83,68,129));
            varit.add(new Color(208, 236, 246));
        } else if(arpa==1){
            varit.add(new Color(31,197,224));
            varit.add(new Color(183,216,144));
            varit.add(new Color(248,215,140));
            varit.add(new Color(243,60,136));
        } else if(arpa==2){
            varit.add(new Color(176,214,163));
            varit.add(new Color(228,86,126));
            varit.add(new Color(251,160,124));
            varit.add(new Color(79,0,52));
        } else if(arpa==3){
            varit.add(new Color(255,232,137));
            varit.add(new Color(119,30,98));
            varit.add(new Color(242,10,91));
            varit.add(new Color(150,157,130));
        } else if(arpa==4){
            varit.add(new Color(0,170,160));
            varit.add(new Color(249,50,159));
            varit.add(new Color(197,91,134));
            varit.add(new Color(215,253,206));
        }else if (arpa==5){
            varit.add(new Color(145,198,210));
            varit.add(new Color(205,131,143));
            varit.add(new Color(156,141,181));
            varit.add(new Color(82,114,115));
        } else if (arpa==6){
            varit.add(new Color(243,60,136));
            varit.add(new Color(31,197,224));
            varit.add(new Color(48,15,140));
            varit.add(new Color(183,216,144));
        } else {
            varit.add(new Color(102,134,165));
            varit.add(new Color(235,141,173));
            varit.add(new Color(116,101,141));
            varit.add(new Color(145,198,210));
            
        }
        return varit;
    }
    
}
