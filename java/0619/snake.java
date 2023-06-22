package ch13;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex06 extends JFrame {
	Thread snakeThread;
	GroundPanel p;
	public Ex06() {
		super("스네이크 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		p = new GroundPanel();//게임 판을 표시
		setContentPane(p);		setSize(400,400);
		setVisible(true);		p.requestFocus();
		snakeThread = new Thread(p);//게임루프를 실행합니다
		snakeThread.start();
	}
	class GroundPanel extends JPanel implements Runnable{
		static final int LEFT = 0;
		static final int RIGHT = 1;
		static final int UP = 2;
		static final int DOWN = 3;
		int direction;//이동방향을 나타내는 정수값
		Image img;
		SnakeBody snakeBody;
		final int delay = 200;
		public GroundPanel() {
			setLayout(null);
			snakeBody = new SnakeBody();
			snakeBody.addIn(this);
			direction = LEFT;
			this.addKeyListener(new MyKeyListener());
//this는 현재 객체인 GroundPanel	
//GroundPanel 객체에 MyKeyListener 인스턴스를 키 이벤트 리스너로 등록하는 것
			ImageIcon icon = new ImageIcon("/Users/kimsoulbee/Downloads/bg1.jpg");
			img = icon.getImage();
		}//판에 이미지를 그립니다.		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0,0,getWidth(), getHeight(), null);
		}
		public void run() {
			while(true) {
				try {
					Thread.sleep(delay);				
					snakeBody.move(direction);
				}catch(InterruptedException e) {
					return;
				}
			}
		}				
		class MyKeyListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					direction = LEFT;
					break;
				case KeyEvent.VK_RIGHT:
					direction = RIGHT;
					break;
				case KeyEvent.VK_UP:
					direction = UP;
					break;
				case KeyEvent.VK_DOWN:
					direction = DOWN;
					break;
				}
			}
		}
	}	
	class SnakeBody {//스네이크의 몸통을 관리하는 클래스
		Vector<JLabel> v = new Vector<JLabel>();
//Vector를 사용하여 JLabel 객체들을 저장		
		public SnakeBody() {
			ImageIcon head = new ImageIcon("src/images/head.jpg");
			JLabel la = new JLabel(head);
			la.setSize(head.getIconWidth(), head.getIconHeight());
			la.setLocation(100, 100);
			v.add(la);
			ImageIcon body = new ImageIcon("/Users/kimsoulbee/Downloads/body.jpg");		
			for(int i=1; i<10; i++) {
				la = new JLabel(body);
				la.setSize(body.getIconWidth(), body.getIconHeight());
				la.setLocation(100+i*20, 100);
				v.add(la);
			}
		}//몸통을 패널에 추가		
		public void addIn(JPanel p) {
			for(int i=0; i<v.size(); i++)
				p.add(v.get(i));
		}//스네이크의 이동을 처리한다	
		public void move(int direction) {
			for(int i=v.size() - 1; i>0; i--) {
				JLabel b = v.get(i);
				JLabel a = v.get(i-1);
				b.setLocation(a.getX(), a.getY());
			}
			JLabel head = v.get(0);//첫번째 요소 뱀의머리를 가져온다
			switch(direction) {
			case GroundPanel.LEFT :
				head.setLocation(head.getX()-20, head.getY());
				break;
			case GroundPanel.RIGHT :
				head.setLocation(head.getX()+20, head.getY());
				break;
			case GroundPanel.UP :
				head.setLocation(head.getX(), head.getY()-20);
				break;
			case GroundPanel.DOWN :
				head.setLocation(head.getX(), head.getY()+20);
				break;
			}
		} 
	}
	public static void main(String[] args) {
		new Ex06();
	}
}
