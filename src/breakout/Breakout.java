package breakout;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Breakout extends Applet implements Runnable, KeyListener {
    
    Ball ball;
    Platform platform[] = new Platform[7];
    Item item[] = new GravUp[3];
    private Image i;
    private Graphics doubleG;
    Dimension dim = new Dimension(800, 600);
    private int score;
    
    @Override
    public void init() {
        setSize(dim);
        addKeyListener(this);
    }
    
    @Override
    public void start() {
        ball = new Ball();
        score = 0;
        for (int i = 0; i < platform.length; i++) {
            Random r = new Random();
            platform[i] = new Platform(getWidth() + 200 * i, getHeight() - 40 - r.nextInt(400));
        }
        for (int i = 0; i < item.length; i++) {
            Random r = new Random();
            item[i] = new GravUp(getWidth() + 2000 * i);
        }
        Thread thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void stop() {
        
    }
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void run() {
        while (true) {
            ball.update(this);
            for (int i = 0; i < platform.length; i++) {
                platform[i].update(this, ball);
            }
            for (int i = 0; i < item.length; i++) {               
                item[i].update(this, ball);
            }
            repaint();
            try {
                Thread.sleep(17); // 60 fps, 1000 (1 sec) / 60 = 17
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(Graphics g) {
//        super.update(g);
//      double buffering setup
        if(i == null) {
            i = createImage(this.getSize().width, this.getSize().height);
            doubleG = i.getGraphics();
        }
        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
        doubleG.setColor(getForeground());
        paint(doubleG);
        g.drawImage(i, 0, 0, this);
    }
    
    @Override
    public void paint(Graphics g) {
        ball.paint(g);
        for (int i = 0; i < platform.length; i++) {
            platform[i].paint(g);
        }
        for (int i = 0; i < item.length; i++) {
            item[i].paint(g);
        }
        g.drawString("SCORE: " + String.valueOf(score), getWidth() / 2, 20);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) { // returns integer of key pressed
            case KeyEvent.VK_LEFT:
                ball.moveL();
                break;
            case KeyEvent.VK_RIGHT:
                ball.moveR();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
