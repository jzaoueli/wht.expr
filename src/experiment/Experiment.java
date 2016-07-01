package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.valueOf;
import static javax.swing.JOptionPane.*;

public class Experiment extends JPanel implements ActionListener {

    private int xRed = screenWidth/4+100, yRed = screenHeight/4+80, redMax = screenWidth/4+screenWidth/2;
    private int xBlue = screenWidth/4+80, yBlue = screenHeight/4+100, blueMax = screenHeight/4+screenHeight/2;

    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private int xRedStart = screenWidth/4+100, yRedStart = screenHeight/4+80;
    private int xBlueStart = screenWidth/4+80, yBlueStart = screenHeight/4+100;
private static int baseSpeed= 6;
    private static Font myFont = new Font("serif", Font.PLAIN, 180);
    private Timer timer = new Timer(1, this);
    private int time = 0;
    private int state = 1;
    private static JOptionPane opts= new JOptionPane(myFont);

    private static ArrayList<String> speedArray;

    private static JFrame jFrame;
    private static Experiment experiment;

    private int modX, modY;

    private static CodeGeneratorFunction codeGeneratorFunction;

    public static void main(String[] args) {
        new Experiment();
        UIManager.getLookAndFeelDefaults()
                .put("defaultFont", new Font("Arial", Font.BOLD, 104));
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
                "Dear participant! \n" +
                        "In this experiment we want to find out if the perception of speed \n" +
                        "correlates with the direction of a moving object.\n"+
                        "When the experiment starts, you will see two rectangles,\n" +
                        " a red one and a blue one.\n"+
                        "They move at different speeds and in different directions.\n"+
                        "Your task is to decide, which rectangle moves faster.\n\n"+
                        "ATTENTION: There will not be a test round, please focus now!\n"+
                        "Please enter your name or alias",
                "    Welcome!",
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
        speedArray = new ArrayList();
        initArrayList(speedArray);
        setNewSpeed();
    }

    private static void initArrayList(ArrayList<String> speedArray) {

        speedArray.add("3,3");
        speedArray.add("4,4");
        speedArray.add("5,5");
        speedArray.add("4,3");
        speedArray.add("3,4");
        speedArray.add("5,4");
        speedArray.add("4,5");
        speedArray.add("3,5");
        speedArray.add("5,3");
        speedArray.add("6,4");
        speedArray.add("4,6");

        Collections.shuffle(speedArray);
        speedArray.add("1,1");
    }

    private void setNewSpeed(){
        String speeds=speedArray.get(0);

        this.modX=valueOf(speeds.substring(0,1));
        this.modY=valueOf(speeds.substring(2));
        speedArray.remove(0);
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

                doCycle(2);
                break;
            case 2:
                input = askUserDialog(3);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 3:
                doCycle(4);
                break;
            case 4:
                input = askUserDialog(5);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 5:
                doCycle(6);
                break;
            case 6:
                input = askUserDialog(7);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 7:
                doCycle(8);
                break;
            case 8:
                input = askUserDialog(9);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 9:
                doCycle(10);
                break;
            case 10:
                input = askUserDialog(11);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 11:
                doCycle(12);
                break;
            case 12:
                input = askUserDialog(13);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 13:
                doCycle(14);
                break;
            case 14:
                input = askUserDialog(15);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 15:
                doCycle(16);
                break;
            case 16:
                input = askUserDialog(17);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 17:
                doCycle(18);
                break;
            case 18:
                input = askUserDialog(19);
                codeGeneratorFunction.appendCycle(state / 2, modX, modY, input);
                break;
            case 19:
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
                JOptionPane opts=new JOptionPane();
                opts.setFont(myFont);
                showMessageDialog(null, "Thank you for participating!", "Experience over", opts.WARNING_MESSAGE);
                experiment.timer.stop();
                jFrame.dispose();
                break;
        }
    }

    private void doCycle(int nextState) {
        time++;
        int redMaxRand= redMax+(int)(Math.random()*100);
        if (xRed < redMaxRand) {
            movePoints(modX, modY);
            if (xRed >= redMaxRand || yBlue >= blueMax) {

                System.err.println("this is the end with xred= " + xRed + " and yBlue= " + yBlue + "and time = " + time);
                time = 0;
                state = nextState;
                xRed = yRedStart+(int)(Math.random()*100);
                yBlue = yBlueStart+(int)(Math.random()*100);
                setNewSpeed();
            }
        }
    }

    private String askUserDialog(int nextState) {
        Object[] choices = {"BLUE(up-down)", "RED(left-right)", "SAME SPEED"};
        String initValue = "no choice";
        String message = "Which point was faster?";
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
            if (yBlue < blueMax) {

                    yBlue++;


            }
        }
        repaint();
    }
}

