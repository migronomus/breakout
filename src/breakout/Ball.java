package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private int radius;
    private double speed;
    private Color color;
    private double eLoss;
    private double gravity;
    private double dt;
    private double fX;
    
    public Ball() {
        this(0, 0, 6, 20, 1.0, Color.GREEN, 0.90, 0.95);
    }
    
    public Ball(int x, int y, double dx, int radius, double speed, Color color, double eLoss, double fX) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        dy = 0;
        this.radius = radius;
        this.speed = speed;
        this.color = color;
        gravity = 15;
        this.eLoss = eLoss;
        dt = 0.2;
        this.fX = fX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getGravity() {
        return gravity;
    }

    public double getELoss() {
        return eLoss;
    }

    public double getfX() {
        return fX;
    }
    
    
    
    public void moveR() {
        if(dx + 1 < 20) {
            dx += 1;
        }
    }
    
    public void moveL() {
        if(dx - 1 > -20) {
            dx -= 1;
        }
    }

    public void update(Breakout bo) {
//        x coords
        if(x + dx > bo.getWidth() - radius) {
            x = bo.getWidth() - radius - 1; // -1 because width=800 and we draw to 799
            dx = -dx;
        } else if(x + dx < 0 + radius) {
            x = 0 + radius;
            dx = -dx;
        } else {
            x += dx * speed;
        }       
        if(y == bo.getHeight() - radius - 1) { // if at floor level
            dx *= fX; // friction
            if(Math.abs(dx) < 0.8) { // if dx is small, set it to zero
                dx = 0;
            }
        }
//        y coords, using gravity
        if(y > bo.getHeight() - radius - 1) {
            y = bo.getHeight() - radius - 1;
            dy *= eLoss;
            dy = -dy;
        } else {
            dy += gravity * dt; // velocity formula
            y += (int)(dy * dt + 0.5 * gravity * dt * dt); // position physics formula
        }
    }
    
    public int getCMx() {
        return x - radius;
    }
    
    public int getCMy() {
        return y - radius;
    }
    
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(getCMx(), getCMy(), radius << 1, radius << 1); // << 1 = *2
    }
    
}
