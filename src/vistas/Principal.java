package vistas;

import vistas.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

    private final ImagePanel panel;

    public Principal() {
        panel = new ImagePanel("./resources/bg.jpeg");
        setContentPane(panel);

        initComponents();
    }

    private void initComponents() {
        panel.setLayout(null);

        ImageIcon icon = new ImageIcon("./resources/start.png");
        JButton boton = new JButton();

        // Center the button
//        Dimension size = panel.getSize();
//        boton.setLocation((size.width - boton.getWidth()) / 2, (size.height - boton.getHeight()) / 2);

        // Resize of image button
        icon = new ImageIcon(icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        boton.setIcon(icon);
        boton.setSize(300, 300);

        // Effect hover expand image and delete border
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon = new ImageIcon("./resources/start.png");
                icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                boton.setIcon(icon);
                boton.setBorderPainted(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon = new ImageIcon("./resources/start.png");
                icon = new ImageIcon(icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                boton.setIcon(icon);
                boton.setBorderPainted(false);
            }
        });

        // Navigate to another view
//        boton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EjercitoVista ejercitoVista = new EjercitoVista();
//                ejercitoVista.setVisible(true);
//                dispose();
//            }
//        });

        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);

        panel.add(boton);
    }
}
