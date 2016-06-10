package helloanim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Thread.sleep;

public class Expr extends JPanel implements ActionListener, KeyListener {


    private int x = 200, y = 100, velX = 0, velY = 0;
    private int tour = 1, st = 20;
    private boolean finish = false;
    //int counterAP = 1, counterKR = 1, counterKP = 1;


    private Timer timer = new Timer(2, this);
    private int tt = 5;

    public static void main(String[] args) {
        Expr expr = new Expr();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(1000, 700);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        //jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.add(expr);
    }

    public Expr() {
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 20, 20);
    }

    public void actionPerformed(ActionEvent a) {
        a.getActionCommand();
        if (!finish) {
            if (tour < 5) {
                doTour();
                tt += 1;
            } else {
                //timer.setDelay(timer.getInitialDelay());
                tour = 1;
                x = 200;
                y = 100;
            }
        }

    }

    private void doTour() {
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
            //if (st > 0) st = st - 1;
            //if (timer.getDelay() > 0) timer.setDelay(timer.getDelay() - 1);
        }

        //System.err.println("finish value :" + finish);

        try {
            sleep(st);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (tt % 10 == 0) if (st > 0) st = st - 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /* int i = 1;
        System.out.println("e: " + e);
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            finish = true;
        }
        System.out.println("e.getKeyCode " + e.getKeyCode());
        */
    }


    public void keyPressed(KeyEvent e) {

        System.out.println(e.getKeyChar() + " pressed");

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("F5 pressed");
            finish = true;
        }

    }


    public void keyReleased(KeyEvent e) {
    }
}
