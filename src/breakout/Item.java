package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Item {
    protected int x, y, dx, radius;
    protected boolean isAlive;

    public Item() {
        this(300);
    }

    public Item(int x) {
        this.x = x;
        y = (int)(Math.random() * 400) + radius;
        dx = -2;
        radius = 15;
        isAlive = true;
    }
    
    public void update(Breakout bo, Ball b) {
        x += dx;
        Random r = new Random();
        if(!isAlive) {
            bo.setScore(bo.getScore() + 10);            
            x = bo.getWidth() + 800 + r.nextInt(400);
            isAlive = true;
        }
        if(x < 0 - radius) {
            x = bo.getWidth() + 800 + r.nextInt(400);
        }
    }
    
    private void checkForCollision(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballR = ball.getRadius();

        int a = x - ballX;
        int b = y - ballY;
        int collide = radius + ballR;
//        distance between object centers
        double c = Math.sqrt((double)a*a + (double)b*b);
        
        if(c < collide) {
            performAction();
        }
    }
    
    public void performAction() {
        isAlive = false;
    }
    
    public void paint(Graphics g) {
//        g.setColor(Color.RED);
        if(isAlive) {
            g.fillOval(x - radius, y - radius, radius << 1, radius << 1);
        }
    }
}
