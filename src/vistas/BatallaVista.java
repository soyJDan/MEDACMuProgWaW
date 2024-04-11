package vistas;

import javax.swing.*;

public class BatallaVista extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JTable table1;
    private JButton salirButton;
    private JButton serializarButton;
    private JButton XMLButton;

    public BatallaVista() {
        setContentPane(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
