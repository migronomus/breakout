package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private double gameDy;
    private int radius;
    private double speed;
    private Color color;
    private double eLoss;
    private double gravity;
    private double dt;
    private double fX;
    
    public Ball() {
        this(0, 0, 0, 20, 2.0, Color.GREEN, 1, 1);
    }
    
    public Ball(int x, int y, double dx, int radius, double speed, Color color, double eLoss, double fX) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        dy = 0;
        gameDy = -75;
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

    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }
    
    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getGameDy() {
        return gameDy;
    }

    public void setGameDy(double gameDy) {
        this.gameDy = gameDy;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getELoss() {
        return eLoss;
    }
    
    public void seteLoss(double eLoss) {
        this.eLoss = eLoss;
    }

    public double getGravity() {
        return gravity;
    }
    
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getfX() {
        return fX;
    }
    
    public void setfX(double fX) {
        this.fX = fX;
    }

    public int getRadius() {
        return radius;
    }
    
    public void moveR() {
        if(dx + 1 < 10) {
            dx += 1;
        }
    }
    
    public void moveL() {
        if(dx - 1 > -10) {
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
            if(Math.abs(dx) < 0.2) { // if dx is small, set it to zero
                dx = 0;
            }
        }
//        y coords, using gravity
        if(y > bo.getHeight() - radius - 1) {
            y = bo.getHeight() - radius - 1;
            dy *= eLoss;
            dy = gameDy;
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
