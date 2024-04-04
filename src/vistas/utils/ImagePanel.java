package vistas.utils;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

        private final ImageIcon image;

        public ImagePanel(String path) {
            image = new ImageIcon(path);
            Dimension dimension = new Dimension(image.getIconWidth(), image.getIconHeight());
            setPreferredSize(dimension);
            setSize(dimension);
            setLayout(null);
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(),null);
        }
}
