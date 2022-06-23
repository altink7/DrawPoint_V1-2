package at.altin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import at.altin.Resource.GraphicsLoader;



class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 175;
    final int[][] x = new int[GAME_UNITS * 10][2];
    final int[][] y = new int[GAME_UNITS * 10][2];

    int Farbe = 6;

    char direction = 'R';
    boolean running = false;
    boolean statistik = false;
    boolean home = true;

    Timer timer;
    Random random;
    //BufferedImage image= GraphicsLoader.loadGraphics("210916.jpg");

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        this.addKeyListener(new MyColorAdapter());
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (home) {
            g.drawImage(null, 132, 50, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Welcome to DrawPoint", (SCREEN_WIDTH - metrics.stringWidth("Welcome to DrawPoint")) / 2, g.getFont().getSize() + 50);

            g.setColor(Color.lightGray);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Press SPACE to continue", (SCREEN_WIDTH - metrics2.stringWidth("Press SPACE to continue")) / 2, g.getFont().getSize() + 650);

        } else {
            if (statistik) {
                drawPoint(g, 1, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 600 / UNIT_SIZE * UNIT_SIZE);
                drawPoint(g, 2, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 500 / UNIT_SIZE * UNIT_SIZE);
                drawPoint(g, 3, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 400 / UNIT_SIZE * UNIT_SIZE);
                drawPoint(g, 4, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 300 / UNIT_SIZE * UNIT_SIZE);
                drawPoint(g, 5, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 200 / UNIT_SIZE * UNIT_SIZE);
                drawPoint(g, 6, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 100 / UNIT_SIZE * UNIT_SIZE);

            } else {
                if (running) {
                    g.setColor(Color.red);

                    for (int i = 0; i < x.length - 1; i++) {

                        if (i == 0) {
                            g.setColor(Color.lightGray);
                        } else if (x[i][0] == x[0][0] && y[i][0] == y[0][0]) {
                            g.setColor(Color.lightGray);
                            x[i][1] = Farbe;
                        } else {
                            if (x[i][1] == 1 || y[i][1] == 1) {
                                g.setColor(Color.black);
                            } else if (x[i][1] == 2 || y[i][1] == 2) {
                                g.setColor(Color.white);
                            } else if (x[i][1] == 3 || y[i][1] == 3) {
                                g.setColor(Color.red);
                            } else if (x[i][1] == 4 || y[i][1] == 4) {
                                g.setColor(Color.blue);
                            } else if (x[i][1] == 5 || y[i][1] == 5) {
                                g.setColor(Color.yellow);
                            } else if (x[i][1] == 7 || y[i][1] == 7) {
                                g.setColor(Color.DARK_GRAY);
                            } else {
                                g.setColor(Color.green);
                            }
                        }
                        g.fillRect(x[i][0], y[i][0], UNIT_SIZE, UNIT_SIZE);
                    }

                    g.setColor(Color.GRAY);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g.drawString("DrawPoint by Altin", (SCREEN_WIDTH - metrics.stringWidth("DrawPoint by Altin")) / 2, g.getFont().getSize());

                    fill(g, SCREEN_WIDTH - 100 / UNIT_SIZE * UNIT_SIZE, SCREEN_HEIGHT - 100 / UNIT_SIZE * UNIT_SIZE);
                } //else {
                // gameOver(g);
                //}
            }

        }
    }

    public void fill(Graphics g, int x, int y) {
        if (Farbe == 1) {
            g.setColor(Color.black);
        } else if (Farbe == 2) {
            g.setColor(Color.white);
        } else if (Farbe == 3) {
            g.setColor(Color.red);
        } else if (Farbe == 4) {
            g.setColor(Color.blue);
        } else if (Farbe == 5) {
            g.setColor(Color.yellow);
        } else if (Farbe == 7) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.green);
        }
        g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);

        g.setFont(new Font("Ink Free", Font.BOLD, 23));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("color:", (SCREEN_WIDTH - metrics.stringWidth("                       ")), SCREEN_HEIGHT - 70);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.setColor(Color.GRAY);
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("0=Grün/1=Schwarz/2=Weiß/3=Rot/4=Blau/5=Gelb/7=Löschen/8=Enter stat./9=Leave stat.",
                (SCREEN_WIDTH - metrics1.stringWidth("0=Grün/1=Schwarz/2=Weiß/3=Rot/4=Blau/5=Gelb/7=Löschen/8=Enter stat./9=Leave stat. ")), SCREEN_HEIGHT - 10);
    }

    public void drawPoint(Graphics g, int idx, int xVal, int yVal) {
        if (idx == 1) {
            g.setColor(Color.black);
        } else if (idx == 2) {
            g.setColor(Color.white);
        } else if (idx == 3) {
            g.setColor(Color.red);
        } else if (idx == 4) {
            g.setColor(Color.blue);
        } else if (idx == 5) {
            g.setColor(Color.yellow);
        } else if (idx == 7) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.green);
        }
        g.fillRect(xVal, yVal, UNIT_SIZE, UNIT_SIZE);

        g.setFont(new Font("Ink Free", Font.BOLD, 23));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("color:", (SCREEN_WIDTH - metrics.stringWidth("                             ")), yVal + 30);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
    } //Einzelne Punkte mit Farbe ausfüllen

    public void move() {

        y[0][1] = Farbe;
        x[0][1] = Farbe;

        for (int i = x.length - 1; i > 0; i--) {
            x[i][0] = x[i - 1][0];
            x[i][1] = x[i - 1][1];
            y[i][0] = y[i - 1][0];
            x[i][1] = x[i - 1][1];
        }
        switch (direction) {
            case 'U':
                y[0][0] = y[0][0] - UNIT_SIZE;
                break;
            case 'D':
                y[0][0] = y[0][0] + UNIT_SIZE;
                break;
            case 'L':
                x[0][0] = x[0][0] - UNIT_SIZE;
                break;
            case 'R':
                x[0][0] = x[0][0] + UNIT_SIZE;
                break;
        }
        direction = 65;
    }


    /*public void checkCollisions() {
        if (x[0][0] < 0) running = false;
        if (x[0][0] > SCREEN_WIDTH) running = false;
        if (y[0][0] < 0) running = false;
        if (y[0][0] > SCREEN_HEIGHT) running = false;
        if (!running) timer.stop();
    }*/ //Checkt ob man noch drinnen im Feld ist

    /*public void gameOver(Graphics g) {
        //Rahmengrenze text
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Außerhalb vom Rahmen ", (SCREEN_WIDTH - metrics1.stringWidth("Außerhalb vom Rahmen ")) / 2, g.getFont().getSize());
    }*/ //Neues Fenster wenn man Außerhalb vom Rahmen ist

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            //checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    direction = 'L';
                    break;

                case KeyEvent.VK_D:
                    direction = 'R';
                    break;

                case KeyEvent.VK_W:
                    direction = 'U';
                    break;

                case KeyEvent.VK_S:
                    direction = 'D';
                    break;
            }

        }
    }

    public class MyColorAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent a) {
            switch (a.getKeyCode()) {
                case KeyEvent.VK_NUMPAD1://Schwarz
                    Farbe = 1;
                    break;
                case KeyEvent.VK_NUMPAD2://Weiß
                    Farbe = 2;
                    break;
                case KeyEvent.VK_NUMPAD3://Rot
                    Farbe = 3;
                    break;
                case KeyEvent.VK_NUMPAD4://Blau
                    Farbe = 4;
                    break;
                case KeyEvent.VK_NUMPAD5://Gelb
                    Farbe = 5;
                    break;
                case KeyEvent.VK_NUMPAD0://Gruen
                    Farbe = 6;
                    break;
                case KeyEvent.VK_NUMPAD7://Gruen
                    Farbe = 7;
                    break;
                case KeyEvent.VK_NUMPAD8://Statistik anzeigen
                    statistik = true;
                    break;
                case KeyEvent.VK_NUMPAD9://Statistik verdecken
                    statistik = false;
                    break;
                case KeyEvent.VK_SPACE://Spiel starten
                    home = false;
                    break;
            }
        }
    }
}
