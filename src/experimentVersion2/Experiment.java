package experimentVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Experiment extends JPanel implements ActionListener, KeyListener {


    private int x = 200, y = 100, velX = 0, velY = 0;
    private int tour = 1, st = 10;
    //int counterAP = 1, counterKR = 1, counterKP = 1;
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int tt = 5;
    private int time = 0;

    public static void main(String[] args) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        screenWidth = width;
        screenHeight = height;


        Experiment expr = new Experiment();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(width, height);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        //jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.add(expr);
    }

    private Experiment() {
        timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 20, 20);
        graphics.fillRect(500,x, 20, 20);

    }

    public void actionPerformed(ActionEvent a) {
        boolean run = false;

        time++;
        double beschleunigungsfaktor = 0.000005;
        int vNull = 1;
        double bewegung = vNull + beschleunigungsfaktor * time;
        if (time % 3 == 0) {


            if (x < 800) {
                x ++; //x + vNull + (int) bewegung;
                repaint();
            } else {
                x = 0;
                repaint();
            }
        }
        /*if (t < 2048) {
            if (tt < 500) {
                x = x +vNull+ (int)bewegung*time*time;
                repaint();
            } else if (tt < 1000) {
                y = y + (int)bewegung*time;
                repaint();
            } else if (tt < 1500) {
                x = x - (int)bewegung*time;
                repaint();
            } else if (tt < 2000) {
                y = y - (int)bewegung*time;
                repaint();
            } else {
                tour += 1;
                tt = 1;
                if (st > 0) st = st-1;
                //if (timer.getDelay() > 0) timer.setDelay(timer.getDelay() - 1);
            }

            try {
                sleep(st);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tt += 1;
        } else {
            //timer.setDelay(timer.getInitialDelay());
            tour = 1;
            x = 200;
            y = 100;
        }*/
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
