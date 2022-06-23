package at.altin;

import javax.swing.*;

class GameFrame extends JFrame {
    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("DrawPoint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
