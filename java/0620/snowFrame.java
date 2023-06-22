package ch14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowFrame extends JFrame {

    public SnowFrame() {
        super("눈내리는 샤갈의 마을");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new SnowPanel());
        setSize(300, 300);
        setResizable(false);
        setVisible(true);
    }

    class SnowPanel extends JPanel {
        Image img;
        final int SNOWS = 50;
        final int SNOW_SIZE = 10;
        Vector<Point> snowVector = new Vector<Point>();
public SnowPanel() {
            this.addComponentListener(new ComponentAdapter() {
                public void componentShown(ComponentEvent e) {
                    addSnow();
                    new SnowThread().start();
                    SnowPanel.this.removeComponentListener(this);
                }
            });
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            drawSnow(g);
        }

        void addSnow() {
            for (int i = 0; i < SNOWS; i++) {
                Point p = new Point((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()));
                snowVector.add(p);
            }
        }

        void drawSnow(Graphics g) {
            g.setColor(Color.white);
            for (int i = 0; i < snowVector.size(); i++) {
                Point p = snowVector.get(i);
                g.fillOval(p.x, p.y, SNOW_SIZE, SNOW_SIZE);
            }
        }
    }

    void changeSnowPosition() {
        SnowPanel snowPanel = (SnowPanel) getContentPane();
        Vector<Point> snowVector = snowPanel.snowVector;

        for (int i = 0; i < snowVector.size(); i++) {
            Point p = snowVector.get(i);
            int xDir = Math.random() > 0.5 ? 1 : -1;
            int offsetX = (int) (Math.random() * 3) * xDir;
            int offsetY = (int) (Math.random() * 7);

            p.x += offsetX;
            if (p.x < 0)
                p.x = 0;

            p.y += offsetY;
            if (p.y > snowPanel.getHeight()) {
                p.x = (int) (Math.random() * snowPanel.getWidth());
                p.y = 0;
            }
        }
    }

    class SnowThread extends Thread {
        public void run() {
            while (true) {
                changeSnowPosition();
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new SnowFrame();
    }
}
