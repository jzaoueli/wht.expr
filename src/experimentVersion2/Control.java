package experimentVersion2;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Control extends JFrame implements ActionListener {

    private int state = 0, round = 0;

    // JPanel
    JPanel pnlButton;

    // Buttons
    JButton btnAddFlight = new JButton("Add Flight");
    JButton state0 = new JButton("0");
    JButton state1 = new JButton("0");
    JButton state2 = new JButton("0");
    JButton state3 = new JButton("0");
    JButton state4 = new JButton("0");

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    public Control(JPanel panel) {
        pnlButton = panel;
        // FlightInfo setbounds
        btnAddFlight.setBounds(60, 400, 220, 30);

        // JPanel bounds
        pnlButton.setBounds(800, 800, 200, 100);

        btnAddFlight.addActionListener((ActionListener) this);
        // Adding to JFrame
        pnlButton.add(btnAddFlight);
        pnlButton.add(state0);
        pnlButton.add(state1);
        pnlButton.add(state2);
        pnlButton.add(state3);
        pnlButton.add(state4);
        btnAddFlight.setActionCommand("Next State");


        add(pnlButton);


    }


    public void actionPerformed(ActionEvent e) {
        if ("Next State".equals(e.getActionCommand())) {
            System.out.println("Old State: " + state);
            //btnAddFlight.setEnabled(false);
            switch (state) {
                case 0:
                    remove(state3);
                    add(state0);
                    repaint();
                    state++;
                    break;
                case 1:
                    remove(state0);
                    repaint();
                    add(state1);
                    repaint();
                    state++;
                    break;
                case 2:
                    remove(state1);
                    add(state2);
                    repaint();
                    if (round == 11) {
                        state++;
                        round = 0;
                    } else {
                        state--;
                        round++;
                    }

                    break;
                case 3:
                    remove(state2);
                    add(state3);
                    repaint();
                    state++;
                    break;

                case 4:
                    remove(state3);
                    add(state4);
                    repaint();
                    state = 0;
                    break;
            }
            System.out.println("New State: " + state);
        } else {

        }
    }
}