package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Item {

    public GravUp(int x) {
        super(x);
    }    
    
    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        super.paint(g);
    }    
}
