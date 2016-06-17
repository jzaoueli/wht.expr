package experimentVersion2;

/**
 * Created by Michael on 03.06.2016.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Control extends JFrame implements ActionListener{
private int state=0, round=0;
    // JPanel
    JPanel pnlButton;
    // Buttons
    JButton btnAddFlight = new JButton("Add Flight");
    JButton state0 = new JButton("0");
    JButton state1 = new JButton("1");
    JButton state2 = new JButton("2");
    JButton state3 = new JButton("3");
    JButton state4 = new JButton("4");

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    public Control(JPanel panel) {
        pnlButton= panel;
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
                    pnlButton.remove(state3);
                    pnlButton.add(state0);
                    pnlButton.repaint();
                    state++;
                    break;
                case 1:
                    pnlButton.remove(state0);
                    pnlButton.repaint();
                    pnlButton.add(state1);
                    pnlButton.repaint();
                    state++;
                    break;
                case 2:
                    pnlButton.remove(state1);
                    pnlButton.add(state2);
                    pnlButton.repaint();
                    if(round==11){
                        state++;
                        round = 0;
                    } else {
                        state--;
                        round++;
                    }

                    break;
                case 3:
                    pnlButton.remove(state2);
                    pnlButton.add(state3);
                    pnlButton.repaint();
                    state++;
                    break;

                case 4:
                    pnlButton.remove(state3);
                    pnlButton.add(state4);
                    pnlButton.repaint();
                    state=0;
                    break;
            }
            System.out.println("New State: "+state);

        }
    }
}