package helloanim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Thread.sleep;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class HelloAnimation extends JPanel implements ActionListener, KeyListener {


    private int x = 200, y = 100, velX = 0, velY = 0;
    private int tour = 1,st = 5;
    //int counterAP = 1, counterKR = 1, counterKP = 1;


    private Timer timer = new Timer(0, this);
    private int tt = 5;

    public static void main(String[] args) {
        HelloAnimation helloAnimation = new HelloAnimation();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("animation JFrame");
        jFrame.setSize(1000, 700);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.add(helloAnimation);
    }

    private HelloAnimation() {
        timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 20, 20);
    }

    public void actionPerformed(ActionEvent a) {
        if (tour < 3) {
            if (tt < 500) {
                x = x + 1;
                repaint();
            } else if (tt < 1000) {
                y = y + 1;
                repaint();
            } else if (tt < 1500) {
                x = x - 1;
                repaint();
            } else if (tt < 2000) {
                y = y - 1;
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
        }
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
