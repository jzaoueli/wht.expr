package experimentVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Experiment extends JPanel implements ActionListener, KeyListener {


    private int x = 00, y = 100;
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int time = 0;
    boolean finish = false;


    private static int speedX, speedY;

    public static void main(String[] args) {
        setUpFrame();

        showStartFrame();

        startExpr();

        showEndFrame();
    }

    private static void setUpFrame() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
    }

    private static void startExpr() {
        speedX = 1;
        speedY = 1;
        doExpr();
        doUserChoice();
    }

    private static void doUserChoice() {

    }

    private static void showEndFrame() {

    }

    private static void showStartFrame() {

    }

    private static void doExpr() {

        Experiment expr = new Experiment();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
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
        graphics.fillRect(x, y, 10, 10);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(500, x, 10, 10);
    }

    public void actionPerformed(ActionEvent a) {
        time++;
        if (time % 10 == 0) {
            if (x < 800) {
                x++;
                repaint();
            } else {
                x = 0;
                repaint();
            }
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
