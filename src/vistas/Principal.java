package vistas;

import vistas.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {

    private final ImagePanel panel;

    public Principal() {
        panel = new ImagePanel("./resources/bg.jpeg");
        setContentPane(panel);

        initComponents();
    }

    private void initComponents() {
        panel.setLayout(new GridBagLayout());

        ImageIcon icono = new ImageIcon("./resources/start.png");
        JButton boton = new JButton(icono);

        boton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Has pulsado el botón"));
        boton.setBounds(0, 0, 50, 50);

        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);

        panel.add(boton);
    }
}
