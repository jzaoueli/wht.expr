package hello;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Jihed on 28.05.2016.
 */
public class HelloForm extends JFrame{
    private JButton clickHereButton;
    private JPanel rootPanel;

    public HelloForm(){
        super("hello in super()");

        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clickHereButton.addActionListener(myAction());

        setVisible(true);
    }

    private ActionListener myAction() {
        return e -> JOptionPane.showConfirmDialog(HelloForm.this,"now Click on Button");
    }
}
