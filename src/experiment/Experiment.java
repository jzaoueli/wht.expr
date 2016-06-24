package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.*;

public class Experiment extends JPanel implements ActionListener {

    private int xRed = 100, yRed = 100, redMax = 800;
    private int xBlue = 100, yBlue = 100, BlueMax = 600;

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Timer timer = new Timer(1, this);
    private int time = 0;
    private int state = 1;

    private static JFrame jFrame;
    private static Experiment experiment;

    private int modX, modY;

    private static CodeGeneratorFunction codeGeneratorFunction;

    public static void main(String[] args) {
        new Experiment();

        setUpFrame();

        String userName = showStartFrame();
        codeGeneratorFunction = new CodeGeneratorFunction(userName);

        startExpr();
        showEndFrame();
    }

    private static void setUpFrame() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
    }

    private static void startExpr() {
        doExpr();
        doUserChoice();
    }

    private static void doUserChoice() {

    }

    private static void showEndFrame() {
        System.out.println("EndFrame wird angezeigt");
    }

    private static String showStartFrame() {
        String name = JOptionPane.showInputDialog(null,
                "Please write your name\n-the generated data file is with your name",
                "     ************    Start Dialog   ************",
                PLAIN_MESSAGE);
        System.out.print(name);
        if (name == null) {
            System.exit(0);
        }
        return name;
    }

    private static void doExpr() {
        experiment = new Experiment();
        experiment.timer.start();
        jFrame = new JFrame();
        jFrame.setTitle("Experiment");
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.darkGray);
        jFrame.add(experiment);
    }

    private Experiment() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(xRed, yRed, 10, 10);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(xBlue, yBlue, 10, 10);
    }

    public void actionPerformed(ActionEvent a) {
        String input;
        switch (state) {
            case 1:
                modX = 5;
                modY = 4;
                doCycle(2);
                break;
            case 2:
                input = askUserDialog(3);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 3:
                modX = 5;
                modY = 9;
                doCycle(4);
                break;
            case 4:
                input = askUserDialog(5);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 5:
                modX = 6;
                modY = 9;
                doCycle(6);
                break;
            case 6:
                input = askUserDialog(7);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 7:
                modX = 9;
                modY = 6;
                doCycle(8);
                break;
            case 8:
                input = askUserDialog(9);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 9:
                modX = 6;
                modY = 6;
                doCycle(10);
                break;
            case 10:
                input = askUserDialog(11);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 11:
                modX = 9;
                modY = 9;
                doCycle(12);
                break;
            case 12:
                input = askUserDialog(13);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 13:
                modX = 5;
                modY = 4;
                doCycle(14);
                break;
            case 14:
                input = askUserDialog(15);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 15:
                modX = 6;
                modY = 9;
                doCycle(16);
                break;
            case 16:
                input = askUserDialog(17);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 17:
                modX = 6;
                modY = 9;
                doCycle(18);
                break;
            case 18:
                input = askUserDialog(19);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 19:
                modX = 6;
                modY = 9;
                doCycle(20);
                break;
            case 20:
                input = askUserDialog(21);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 21:
                try {
                    codeGeneratorFunction.createAndWriteInFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showMessageDialog(null, "The experiment result is in data folder", "Experience over", JOptionPane.WARNING_MESSAGE);
                experiment.timer.stop();
                jFrame.dispose();
                break;
        }
    }

    private void doCycle(int nextState) {
        time++;
        if (xRed < redMax) {
            movePoints(modX, modY);
            if (xRed == 800 || yBlue == 600) {

                System.err.println("this is the end with xred= " + xRed + " and yBlue= " + yBlue + "and time = " + time);
                time = 0;
                state = nextState;
                xRed = 100;
                yBlue = 100;
            }
        }
    }

    private String askUserDialog(int nextState) {
        Object[] choices = {"BLUE", "RED", "SAME SPEED"};
        String initValue = "no choice";
        String message = "Witch point was faster?";
        String title = "Please choose an option";
        int optionType = YES_NO_CANCEL_OPTION;
        int messageType = QUESTION_MESSAGE;

        int input = showOptionDialog(null, message, title, optionType, messageType, null, choices, initValue);
        state = nextState;
        switch (input){
            case 0:
                return "BLUE";
            case 1:
                return "RED";
            case 2:
                return "SAME SPEED";
            case -1:
                System.exit(0);
                break;
        }
        return null;
    }

    private void movePoints(int modX, int modY) {
        if (time % modX == 0) {
            if (xRed < redMax) {
                xRed++;
            }
        }
        if (time % modY == 0) {
            if (yBlue < BlueMax) {
                if (yBlue < 600) {
                    yBlue++;
                }
            }
        }
        repaint();
    }
}

