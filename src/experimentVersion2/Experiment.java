package experimentVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.*;

public class Experiment extends JPanel implements ActionListener {

    private final int numberOfIterations = 1;
    private int xRed = 20, yRed = 100, redMax = 800;
    private int xGreen = 100, yGreen = 10, greenMax = 600;

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int time = 0;
    private int state = 1;

    private static int speedX, speedY;
    private ExperimentOutput out;

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
        int input = CANCEL_OPTION;
        while (input != OK_OPTION) {
            input = showConfirmDialog(null,
                    "to start the experience please click on OK",
                    "Perception experience",
                    OK_CANCEL_OPTION);
        }
    }

    private static void doExpr() {
        Experiment expr = new Experiment();
        expr.timer.start();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        jFrame.add(expr);
    }

    private Experiment() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.out = new ExperimentOutput("Experiment-"+timeStamp+".csv", numberOfIterations);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(xRed, yRed, 10, 10);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(xGreen, yGreen, 10, 10);
    }

    public void actionPerformed(ActionEvent a) {
        switch (state) {
            case 1:
                doCycle(8, 10, 2);
                break;
            case 2:
                askUserDialog(3);
                break;
            case 3:
                doCycle(10, 5, 4);
                break;
            case 4:
                askUserDialog(5);
                break;
            case 5:
                doCycle(10,10,6);
                break;
            case 6:
                askUserDialog(7);
                break;
            case 7:
                showMessageDialog(null, "The experience result is in data folder", "Experience over", OK_OPTION);
                out.writeToFile();
                timer.stop();
                break;
        }
    }

    private void doCycle(int modX, int modY, int nextState) {
        time++;
        if (xRed < redMax) {
            movePoints(modX, modY);
            if (xRed == 800 || yGreen == 600) {

                System.err.println("this is the end with xred= " + xRed + " and ygreen= " + yGreen + "and time = " + time);

                time = 0;
                state = nextState;
                xRed = 20;
                yGreen = 10;
            }
        }
    }

    private void answer(int answer, int modRed, int modGreen) {
        float speedRed=1/modRed;
        float speedGreen=1/modGreen;
        out.addIteration(0, speedRed, speedGreen, answer);
    }

    private int askUserDialog(int nextState) {
        Object[] choices = {"GREEN", "RED", "SAME SPEED"};
        String initValue = "no choice";
        String message = "Witch point was faster?";
        String title = "Please choose an option";
        int optionType = YES_NO_CANCEL_OPTION;
        int messageType = QUESTION_MESSAGE;

        int input = 3;
        while (input != 0 && input != 1 && input != 2) {
            input = showOptionDialog(null, message, title, optionType, messageType, null, choices, initValue);
        }
        this.answer(input,1,1);

        state = nextState;
        return input;
    }

    private void movePoints(int modX, int modY) {
        if (time % modX == 0) {
            if (xRed < redMax) {
                xRed++;
            }
        }
        if (time % 10 == 0) {
            if (yGreen < greenMax) {
                if (time % modY == 0) {
                    if (yGreen < 600) {
                        yGreen++;
                    }
                }
            }
        }
        repaint();
    }
}

