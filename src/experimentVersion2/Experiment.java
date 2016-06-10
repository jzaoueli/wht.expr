package experimentVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Experiment extends JPanel implements ActionListener, KeyListener {

    private static Control control;
    private int xRed = 0, yRed = 100;
    private int xGren = 100, yGreen = 0;

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int time = 0;
    boolean finish = false;
    public int state = 0;

    private static int speedX, speedY;


    public static void main(String[] args) {
        new Experiment();

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
        System.out.println("EndFrame wird angezeigt");
    }

    private static void showStartFrame() {
        System.out.println("StartFrame wird angezeigt");
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
        control = new Control(this);
        timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(xRed, yRed, 10, 10);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(xGren, yGreen, 10, 10);
    }

    public void actionPerformed(ActionEvent a) {
        control.actionPerformed(a);
        time++;
        movePoints(8, 10);
        if (xRed == 800 || yGreen == 600) {
            finish = true;
            System.err.println("this is the end with xred= " + xRed + " and ygreen= " + yGreen);
            timer.stop();
        }
    }

    private void movePoints(int modX, int modY) {
        if (time % modX == 0) {
            if (xRed < 800) {
                xRed++;
                repaint();
            }
        }
        if (time % modY == 0) {
            if (yGreen < 600) {
                yGreen++;
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
