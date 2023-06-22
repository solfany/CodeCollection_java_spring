package ch14;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GraphicObject {
	BufferedImage img = null;
	int x = 0, y = 0;
	
	//생성자에서 이미지를 읽는다.
	public GraphicObject(String name) {
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	public void update() {
	}	
	//화면에 이미지를 그린다.
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	public void keyPressed(KeyEvent event) {
	}
}
//GraphicObject상속,미사일을 나타내는 클래스 정의
class Missile extends GraphicObject {
	boolean launched = false;

	public Missile(String name) {
		super(name);
		y = -200;
	}
	public void update() {
		//미사일 위치를 변경	
		if (launched)
			y -= 10;
		if (y < -100000)
			launched = false;
	}	
	//스페이스키가 눌리면 미사일이 발사
	public void keyPressed(KeyEvent event, int x, int y) {
		if (event.getKeyCode() == KeyEvent.VK_SPACE) {
			launched = true;
			this.x = x;
			this.y = y;
		}
	}
}
//GraphicObject상속, Enemy클래스 작성
class Enemy extends GraphicObject {
	int dx = -10;

	public Enemy(String name) {

		super(name);
		x = 500;
		y = 0;
	}
	//Enemy캐릭터의 위치 변경
	public void update() {
		x += dx;
		if (x < 0)
			dx = +10;
		if (x > 500)
			dx = -10;
	}
}
//GraphicObject상속, SpaceShip클래스 작성
class SpaceShip extends GraphicObject {
	public SpaceShip(String name) {
		super(name);
		x = 150;
		y = 350;
	}
	//화살표 키에 따라 플레이어 캐릭터 위치 변경
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			y -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 10;
		}
	}
}
class Background extends GraphicObject {
	public Background(String name) {
		super(name);
		x = 0;
		y = 0;
	}
}
class MyPanel extends JPanel implements KeyListener {
	Enemy enemy;
	SpaceShip spaceship;
	Missile missile;
	Background background;

	public MyPanel() {
		super();
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);

		enemy = new Enemy("/Users/kimsoulbee/Desktop/enemy.png");
		spaceship = new SpaceShip("/Users/kimsoulbee/Desktop/spaceship.png");
		missile = new Missile("/Users/kimsoulbee/Desktop/missile.png");
		background = new Background ("/Users/kimsoulbee/Desktop/background.jpg");
		
		//스레드를 이용하여 게임의 메인루프를 작성
		//각객체의 위치를 변경하고 다시 그린다.
		class MyThread extends Thread {
			public void run() {
				while (true) {					
					enemy.update();
					spaceship.update();
					missile.update();
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
				}
			}
		}
		Thread t = new MyThread();
		t.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		background.draw(g);
		enemy.draw(g);
		spaceship.draw(g);
		missile.draw(g);
	}
	//키보드 이벤트를 각 객체에 전달
	public void keyPressed(KeyEvent event) {
		spaceship.keyPressed(event);
		missile.keyPressed(event, spaceship.x, spaceship.y);
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
}
public class ShootingGame_Thread extends JFrame {
	public ShootingGame_Thread() {
		setTitle("Shooting Game");
		add(new MyPanel());
		setSize(500, 500);		
		setVisible(true);
	}
	public static void main(String[] args) {
		new ShootingGame_Thread();
	}
}
