package ch16;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GalagaGame extends JPanel implements KeyListener {
    private boolean running = true;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Sprite starship;
    private BufferedImage alienImage;
    private BufferedImage shotImage;
    private BufferedImage shipImage;

    // 미사일 저장소
    private ArrayList<ShotSprite> shots = new ArrayList<>();

    private int score = 0;

    public GalagaGame() {
        JFrame frame = new JFrame("Galaga Game");
        frame.setSize(800, 600);
        frame.add(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            shotImage = ImageIO.read(new File("/Users/kimsoulbee/Desktop/missile.png"));
            shipImage = ImageIO.read(new File("/Users/kimsoulbee/Desktop/spaceship.png"));
            alienImage = ImageIO.read(new File("/Users/kimsoulbee/Desktop/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.requestFocus();
        this.initSprites();
        addKeyListener(this);
    }

    private void initSprites() {
        starship = new StarShipSprite(this, shipImage, 370, 500);
        sprites.add(starship);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 12; x++) {
                Sprite alien = new AlienSprite(this, alienImage, 00 + (x * 50), 50 + (y * 30));
                sprites.add(alien);
            }
        }
    }

    private void startGame() {
        sprites.clear();
        initSprites();
        score = 0;
    }

    public void endGame() {
        System.out.println("endGame");
        System.exit(0);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void fire() {
        ShotSprite shot = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);
        shots.add(shot);
    }


    // Q키
    public void fireThreeShots() {
          int shotSpacing = 25; // 탄환 간의 가로 간격

    int startX = starship.getX() + 60 ; // 시작 위치: 현재 우주선의 가로 위치
    int startY = starship.getY() - 30; // 시작 위치: 우주선 위쪽으로 30만큼 이동

        if (shots.size() < 3) {
            ShotSprite shot1 = new ShotSprite(this, shotImage, starship.getX() + 5, starship.getY() - 30);
            ShotSprite shot2 = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);
            ShotSprite shot3 = new ShotSprite(this, shotImage, starship.getX() + 15, starship.getY() - 30);

            shots.add(shot1);
            shots.add(shot2);
            shots.add(shot3);
        }
          for (int i = 0; i < 3; i++) {
        int shotX = startX - (shotSpacing * (i - 1)); // 탄환의 가로 위치 계산
        ShotSprite shot = new ShotSprite(this, shotImage, shotX, startY);
        sprites.add(shot);
    }
}

    // W키
    public void fireThreeShots1() {
          int shotSpacing = 50; // 탄환 간의 가로 간격

    int startX = starship.getX() + 60 ; // 시작 위치: 현재 우주선의 가로 위치
    int startY = starship.getY() - 30; // 시작 위치: 우주선 위쪽으로 30만큼 이동

        if (shots.size() < 5) {
            ShotSprite shot1 = new ShotSprite(this, shotImage, starship.getX() + 5, starship.getY() - 30);
            ShotSprite shot2 = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);
            ShotSprite shot3 = new ShotSprite(this, shotImage, starship.getX() + 15, starship.getY() - 30);

            shots.add(shot1);
            shots.add(shot2);
            shots.add(shot3);
        }
          for (int i = 0; i < 5; i++) {
        int shotX = startX - (shotSpacing * (i - 1)); // 탄환의 가로 위치 계산
        ShotSprite shot = new ShotSprite(this, shotImage, shotX, startY);
        sprites.add(shot);
    }
}
// E키
public void fireThreeShots3() {
    int shotSpacing = 50; // 탄환 간의 가로 간격
    int startX = starship.getX() + 60; // 시작 위치: 현재 우주선의 가로 위치
    int startY = starship.getY() - 30; // 시작 위치: 우주선 위쪽으로 30만큼 이동

    if (shots.size() < 1) { // 한 번에 하나의 흰색 동그라미 미사일만 발사하도록 수정
        ShotSprite shot = new WhiteCircleShotSprite(this, shotImage, startX, startY);
        shots.add(shot);
    }

    for (int i = 0; i < shots.size(); i++) {
        ShotSprite shot = shots.get(i);
        if (!shot.isCollided()) { // 충돌 후 딜레이 중인 미사일은 체크하지 않음
            for (int j = 0; j < sprites.size(); j++) {
                Sprite sprite = sprites.get(j);
                if (sprite instanceof AlienSprite && shot.checkCollision(sprite)) {
                    score++;
                    shot.setCollided(true);
                    sprites.remove(j);
                    break;
                }
            }
        }
    }
}

// 흰색 동그라미 미사일을 나타내는 ShotSprite 클래스를 상속받은 WhiteCircleShotSprite 클래스를 추가
public class WhiteCircleShotSprite extends ShotSprite {
    private int collisionDelay = 20; // 충돌 후 딜레이 시간

    public WhiteCircleShotSprite(GalagaGame game, BufferedImage image, int x, int y) {
        super(game, image, x, y);
    }

    @Override
    public void move() {
        if (!isCollided()) {
            super.move();
        } else {
            if (collisionDelay > 0) {
                collisionDelay--;
            } else {
                setCollided(false);
                collisionDelay = 20; // 딜레이 시간 초기화
            }
        }
    }
}



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);

        for (Sprite sprite : sprites) {
            sprite.draw(g);
        }

        for (ShotSprite shot : shots) {
            shot.draw(g);
        }

        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, 20);
    }

    public void gameLoop() {
        while (running) {
            for (int i = 0; i < sprites.size(); i++) {
                Sprite sprite = sprites.get(i);
                sprite.move();
            }

            for (int p = 0; p < sprites.size(); p++) {
                for (int s = p + 1; s < sprites.size(); s++) {
                    Sprite me = sprites.get(p);
                    Sprite other = sprites.get(s);

                    if (me.checkCollision(other)) {
                        me.handleCollision(other);
                        other.handleCollision(me);
                    }
                }
            }

            // Update and remove shots
            for (int i = shots.size() - 1; i >= 0; i--) {
                ShotSprite shot = shots.get(i);
                shot.move();
                if (shot.getY() < 0) {
                    shots.remove(i);
                }
            }

            // Check collision between shots and aliens
            for (int i = 0; i < shots.size(); i++) {
                ShotSprite shot = shots.get(i);
                for (int j = 0; j < sprites.size(); j++) {
                    Sprite sprite = sprites.get(j);
                    if (sprite instanceof AlienSprite && shot.checkCollision(sprite)) {
                        score++;
                        shots.remove(i);
                        sprites.remove(j);
                        break;
                    }
                }
            }

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            starship.setDx(-3);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            starship.setDx(3);
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            fire();
        if (e.getKeyCode() == KeyEvent.VK_Q)
            fireThreeShots();
    
        if (e.getKeyCode() == KeyEvent.VK_W)
            fireThreeShots1();

        if (e.getKeyCode() == KeyEvent.VK_S)
            fireThreeShots();
        }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            starship.setDx(0);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            starship.setDx(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        GalagaGame game = new GalagaGame();
        game.gameLoop();
    }
}

