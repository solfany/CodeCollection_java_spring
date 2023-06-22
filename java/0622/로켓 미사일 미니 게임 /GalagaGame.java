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

//메인 클래스
public class GalagaGame extends JPanel implements KeyListener {
//게임 실행중 여부 플래그
	private boolean running = true;
//각 유닛 객체들 저장소
	private ArrayList sprites = new ArrayList();
	private Sprite starship;
	private BufferedImage alienImage;
	private BufferedImage shotImage;
	private BufferedImage shipImage;

//생성자 : 프레임 셋팅 및 각 유닛 이미지 생성
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
//포커스 지정(프레임)
		this.requestFocus();
//내 유닛과 적 유닛 생성하여 저장소에 저장
		this.initSprites();
//키 이벤트 지정(프레임)
		addKeyListener(this);
	}
//내 유닛과 적 유닛 저장 메소드
	private void initSprites() {
//내 유닛 생성하여
		starship = new StarShipSprite(this, shipImage, 370, 500);
//저장소에 저장
		sprites.add(starship);
//적 유닛 객체를 5행 12열로 60마리 생성하여
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 12; x++) {
				Sprite alien = new AlienSprite(this, alienImage, 
										00 + (x * 50), 50 + (y * 30));
//순서대로 저장소에 저장
				sprites.add(alien);
			}
		}
	}

//게임 시작 : 저장소를 비우고 다시 채움
	private void startGame() {
		sprites.clear();
		initSprites();
	}

//게임 종료 : 프로그램 종료
	public void endGame() {
		System.out.println("endGame");
		System.exit(0);
	}
//유닛 삭제 : 전달 받은 유닛을 저장소에서 삭제
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
//탄환 생성 : 탄환 생성 후 저장소에 저장
	public void fire() {
		ShotSprite shot = new ShotSprite(this, shotImage, 
				            starship.getX() + 10, starship.getY() - 30);
		sprites.add(shot);
	}
//그리기 오버라이딩
	@Override
	public void paint(Graphics g) {
//부모 컨테이너(프레임) 그리고
		super.paint(g);
//검은색 사각형 생성 (배경)
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
//저장소에 저장한 객체들을 모두 그리기 (제네릭 쓰면 형변환 안해도 됨)
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = (Sprite) sprites.get(i);
			sprite.draw(g);
		}
	}
//게임 진행
	public void gameLoop() {
//게임 실행중 여부 플래그에 따라 반복 진행/정지
		while (running) {
//저장소에 저장한 객체 모두 움직임
			for (int i = 0; i < sprites.size(); i++) {
				Sprite sprite = (Sprite) sprites.get(i);
				sprite.move();
			}
//저장소의 모든 객체가 부딪혓는지 비교
//예) 0번과 그 이후 나머지 비교, 1번과 그 이후 나머지 비교, .... 
			for (int p = 0; p < sprites.size(); p++) {
				for (int s = p + 1; s < sprites.size(); s++) {
					Sprite me = (Sprite) sprites.get(p);
					Sprite other = (Sprite) sprites.get(s);
//두 객체가 부딪혔으면
					if (me.checkCollision(other)) {
//각 객체에 정의된 handleCollision 메소드로
//적과 부딪혔는지 확인하여 그에 맞는 동작을 실행
						me.handleCollision(other);
						other.handleCollision(me);
					}
				}
			}
//변경된 정보로 다시 그리고 0.01초 쉼
			repaint();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}

//키 누름 이벤트
	@Override
	public void keyPressed(KeyEvent e) {
//누른 키가 좌, 우 방향키이면 내 유닛 움직임
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(-3);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(3);
//누른 키가 스페이스바이면 탄환 생성하여 발사
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			fire();
	}

//키 뗌 이벤트
	@Override
	public void keyReleased(KeyEvent e) {
//좌우 방향키를 뗏으면 내 유닛 멈춤
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(0);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

//프로그램 실행
	public static void main(String[] args) {
//이벤트 분배 스레드로 GUI와 각 객체 셋팅하고
		GalagaGame g = new GalagaGame();
//메인 스레드로 게임 진행
		g.gameLoop();
	}
}


//스코어 넣기 
//기능 추가 


