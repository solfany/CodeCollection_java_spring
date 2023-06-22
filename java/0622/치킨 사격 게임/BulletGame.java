package ch16;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BulletGames extends JFrame {
    private int score; // 점수를 저장하는 변수

    public BulletGames() {
        setTitle("사격 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel p = new GamePanel();
        setContentPane(p);
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        p.startGame();
    }

    public static void main(String[] args) {
        new BulletGames();
    }

    // 점수를 업데이트하는 메소드
    public void updateScore() {
        score++;
        System.out.println("Score: " + score); // 원하는 위치에 점수를 표시하는 방식으로 이 부분을 수정할 수 있습니다.
    }

    class GamePanel extends JPanel {
        TargetThread targetThread = null;
        JLabel baseLabel = new JLabel();
        JLabel bulletLabel = new JLabel();
        JLabel targetLabel;

        public GamePanel() {
            setLayout(null);
            baseLabel.setSize(40, 40);
            baseLabel.setOpaque(true);
            baseLabel.setBackground(Color.BLACK);

            ImageIcon img = new ImageIcon("/Users/kimsoulbee/Desktop/chicken.png");
            targetLabel = new JLabel(img);
            targetLabel.setSize(img.getIconWidth(), img.getIconWidth());
            bulletLabel.setSize(10, 10);
            bulletLabel.setOpaque(true);
            bulletLabel.setBackground(Color.RED);

            add(baseLabel);
            add(targetLabel);
            add(bulletLabel);
        }

        public void startGame() {
            baseLabel.setLocation(this.getWidth() / 2 - 20, this.getHeight() - 40);
            bulletLabel.setLocation(this.getWidth() / 2 - 5, this.getHeight() - 50);
            targetLabel.setLocation(0, 0);

            targetThread = new TargetThread(targetLabel);
            targetThread.start();
            baseLabel.requestFocus();

            baseLabel.addKeyListener(new KeyAdapter() {
                BulletThread bulletThread = null;

                public void keyPressed(KeyEvent e) {
                    if (e.getKeyChar() == '\n') {
                        if (bulletThread == null || !bulletThread.isAlive()) {
                            bulletThread = new BulletThread(bulletLabel, targetLabel, targetThread);
                            bulletThread.start();
                        }
                    }
                }
            });
        }

        class TargetThread extends Thread {
            JComponent target;

            public TargetThread(JComponent target) {
                this.target = target;
                target.setLocation(0, 0);
                target.getParent().repaint();
            }

            public void run() {
                while (true) {
                    int x = target.getX() + 5;
                    int y = target.getY();

                    if (x > GamePanel.this.getWidth())
                        target.setLocation(0, 0);
                    else
                        target.setLocation(x, y);

                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        class BulletThread extends Thread {
            JComponent bullet, target;
            Thread targetThread;

            public BulletThread(JComponent bullet, JComponent target, Thread targetThread) {
                this.bullet = bullet;
                this.target = target;
                this.targetThread = targetThread;
            }

            public void run() {
                while (true) {
                    if (hit()) {
                        targetThread.interrupt();
                        bullet.setLocation(bullet.getParent().getWidth() / 2 - 5,
                        		bullet.getParent().getHeight() - 50);
                        updateScore(); // 점수를 증가시킴
                        JOptionPane.showMessageDialog(null, "게임 종료");
                        System.exit(0);
                        return;
                        } else {
                        int x = bullet.getX();
                        int y = bullet.getY() - 5;
                        if (y < 0) {
                            bullet.setLocation(bullet.getParent().getWidth() / 2 - 5,
                                    bullet.getParent().getHeight() - 50);
                            return;
                        }
                        bullet.setLocation(x, y);
                    }

                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
            }

            // 총알이 목표물에 맞는지 여부를 반환하는 메소드
            private boolean hit() {
                if (targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY())
                        || targetContains(bullet.getX() + bullet.getWidth() - 1,
                                bullet.getY() + bullet.getHeight() - 1)
                        || targetContains(bullet.getX(), bullet.getY() + bullet.getHeight() - 1)) {
                    return true;
                } else {
                    return false;
                }
            }

            // 목표물 영역에 해당 좌표가 포함되는지 여부를 반환하는 메소드
            private boolean targetContains(int x, int y) {
                if (((target.getX() <= x) && (target.getX() + target.getWidth() - 1 >= x))
                        && ((target.getY() <= y) && (target.getY() + target.getHeight() - 1 >= y))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
