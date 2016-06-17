package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.*;

public class Experiment extends JPanel implements ActionListener {

    private final int numberOfIterations = 1;
    private int xRed = 100, yRed = 100, redMax = 800;
    private int xGreen = 100, yGreen = 100, greenMax = 600;

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int time = 0;
    private int state = 1;

    private static JFrame jFrame;
    private static Experiment experiment;

    private static int speedX, speedY;
    private static ExperimentCSVDataFileGenerator out = new ExperimentCSVDataFileGenerator("", 10);
    private int modX, modY;

    public static void main(String[] args) {
        new Experiment();

        setUpFrame();
        //setupGeneratedFile(filename)
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

    private static int showStartFrame() {
        out.writeHeader();
        int input = showConfirmDialog(null,
                "to start the experiment please click on OK",
                "Perception experiment",
                OK_CANCEL_OPTION);
        if (input != OK_OPTION) {
            System.exit(0);
        }
        return input;
    }

    private static void doExpr() {
        experiment = new Experiment();
        experiment.timer.start();
        jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        jFrame.add(experiment);
    }

    private Experiment() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.out.setFilename("Experiment-" + timeStamp + ".csv");
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(xRed, yRed, 10, 10);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(xGreen, yGreen, 10, 10);
    }

    public void actionPerformed(ActionEvent a) {
        int input = 0;
        switch (state) {
            case 1:
                modX = 2;
                modY = 4;
                doCycle(2);
                break;
            case 2:
                input = askUserDialog(3);
                this.answer(input);
                break;
            case 3:
                modX = 5;
                modY = 6;
                doCycle(4);
                break;
            case 4:
                input = askUserDialog(5);
                this.answer(input);
                break;
            case 5:
                modX = 6;
                modY = 9;
                doCycle(6);
                break;
            case 6:
                input = askUserDialog(7);
                this.answer(input);
                break;
            case 7:
                showMessageDialog(null, "The experiment result is in data folder", "Experience over", OK_OPTION);
                out.close();
                experiment.timer.stop();
                jFrame.dispose();
                break;
        }
    }

    private void doCycle(int nextState) {
        time++;
        if (xRed < redMax) {
            movePoints(modX, modY);
            if (xRed == 800 || yGreen == 600) {

                System.err.println("this is the end with xred= " + xRed + " and ygreen= " + yGreen + "and time = " + time);
                time = 0;
                state = nextState;
                xRed = 100;
                yGreen = 100;
            }
        }
    }

    private void answer(int answer) {
        float speedRed = 1 / modX;
        float speedGreen = 1 / modY;
        out.addIteration(speedRed, speedGreen, answer);
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
        this.answer(input);

        state = nextState;
        return input;
    }

    private void movePoints(int modX, int modY) {
        if (time % modX == 0) {
            if (xRed < redMax) {
                xRed++;
            }
        }
        if (time % modY == 0) {
            if (yGreen < greenMax) {
                if (yGreen < 600) {
                    yGreen++;
                }
            }
        }
        repaint();
    }
}

