package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {
    int dx;
    int x, y, width, height;

    public Platform() {
        dx = -10;
        x = 300;
        y = 300;
        width = 120;
        height = 40;
    }
    
    public void update(Breakout bo) {

    }
    
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }    
}
