package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Platform {
    int dx;
    int x, y, width, height;

    public Platform() {
        this(300, 300);
    }

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = -2;
        this.width = 140;
        this.height = 40;
    }
    
    public void update(Breakout bo, Ball b) {
        x += dx;
        checkForCollision(b);
        if(x < 0 - width) {
            Random r = new Random();
            y = bo.getHeight() - 40 - r.nextInt(400);
            x = bo.getWidth() + r.nextInt(300);
        }
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }    

    private void checkForCollision(Ball b) {
        int ballX = b.getX();
        int ballY = b.getY();
        int radius = b.getRadius();
        
        if(ballX + radius > x && ballX < x + width) {
            if(ballY + radius > y && ballY + radius < y + height) {
                double newDy = b.getGameDy();
                b.setY(y - radius);
                b.setDy(newDy);
            }
        }
    }
}
