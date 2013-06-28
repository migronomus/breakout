package breakout;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Breakout extends Applet implements Runnable, KeyListener {
    
    Ball[] ball = new Ball[2];
    Platform platform;
    private Image i;
    private Graphics doubleG;
    Dimension dim = new Dimension(800, 600);
    
    @Override
    public void init() {
        setSize(dim);
        addKeyListener(this);
    }
    
    @Override
    public void start() {
        for (int i = 0; i < ball.length; i++) {
            ball[i] = new Ball((int)(Math.random() * 600), (int)(Math.random() * 400), 8, 20, 2.0, Color.GREEN, 1.0 - (Math.random() * 0.02), 1.0 - (Math.random() * 0.02));
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
            for (int i = 0; i < ball.length; i++) {
                ball[i].update(this);
            }
            platform = new Platform();
            platform.update(this);
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
        for (int i = 0; i < ball.length; i++) {
            ball[i].paint(g);
        }
        platform.paint(g);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) { // returns integer of key pressed
            case KeyEvent.VK_LEFT:
                ball[0].moveL();
                break;
            case KeyEvent.VK_RIGHT:
                ball[0].moveR();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
