package practice01_0615;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements ActionListener {
	// 상태를 기록하고 처리 흐름을 제어하기 위한 boolean 타입 변수
	boolean flag = false;
	private int light_number = 0;
	
	public MyPanel() {
		// 배치관리자 BorderLayout 사용
		setLayout(new BorderLayout());
		JButton b = new JButton("신호등");
		
		// 버튼이 눌렸을 때 액션리스너가 실행된다.
		// paintComponent 메소드로 이동!
		b.addActionListener(this);
		
		// 버튼 객체를 출력하는데 남쪽에 위치하도록.
		add(b, BorderLayout.SOUTH);
	}
	
	// JPanel을 상속받고 있기에
	// 항상 paintComponent 활용할 때는 JPanel을 활용하자.
	@Override
	protected void paintComponent(Graphics g) {
		// JPanel이나 JLabel처럼 그래픽 컴포넌트인 경우,
		// ↓ paintComponent 메소드를 재정의 할 때 내부에 호출되면 좋은 문장이다.
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		// drawOval : 원을 그린다. g.drawOval(x, y, width, height)
		// 좌측을 기준으로 x, y는 0, 0 이라 생각하고 그 위치에 그리는 것
		g.drawOval(100, 100, 100, 100);
		g.drawOval(100, 200, 100, 100);
		g.drawOval(100, 300, 100, 100);
		
		// number가 0이면 빨간색 원이 생긴다.
		// 같은 위치에 그려지므로 불이 켜진 것 처럼 느껴지게!
		if(light_number == 0) {
			g.setColor(Color.RED);
			
			// fillOval : 채워진 원을 그린다.
			g.fillOval(100, 100, 100, 100);
		} else if (light_number == 1) {
			g.setColor(Color.GREEN);
			g.fillOval(100, 200, 100, 100);
		} else {
			g.setColor(Color.YELLOW);
			g.fillOval(100, 300, 100, 100);
		}
	}
	// ActionListener를 구현받기에 클래스 생성 없이 바로 오버라이딩
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// 전위연산자, 이벤트 발생 시 light_number+1 시킨다. 
		// 그러다 3번 빨, 노, 초가 한 번씩 나오면 다시 0으로 초기화
		if(++light_number >= 3) 
			light_number = 0;
			repaint();
	}
}
public class MyFrame extends JFrame {
	public MyFrame() {
		// MyPanel클래스를 추가한다.
		add(new MyPanel());
		setTitle("버튼 누르면 신호등 색깔이 바뀐다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyFrame();
	}
}
// 모든 컴포넌트는 자신의 모양을 스스로 그리는데,
// 컨테이너는 자신을 그린 후 그 위에 자식 컴포넌트들에게 그리기를 지시한다.
// 모든 스윙 컴포넌트는 자신의 모양을 그리는 paintComponent() 메서드를 보유한다.
// @Override
// public void paintComponent(Graphics g) :
// 스윙 컴포넌트가 자신의 모양을 그리는 메서드로, 컴포넌트가 그려져야 하는 시점마다
// 크기가 변경되거나 위치가 변경되거나 가려졌던 것이 사라지는 등 이런 시점마다 호출된다.
// paintComponent()는 페인팅 메커니즘에 의해 자동으로 호출되므로
// 개발자가 직접 호출하면 안된다.
// super.paintComponent(g) : 이 문장은 그림이 그려지는 컴포넌트가 JPanel이나 JLabel처럼 그래픽 컴포넌트인 경우,
// paintComponent()를 재정의 할 때 내부에 호출되면 좋은 문장이다.
// 그래픽 컴포넌트를 상속받았을 때, 부모 클래스가 그려야 될 부분도 있기 때문이다.
// 자기 그림만 그리고 종료해버리면 부모 클래스는 그릴 기회를 얻지 못한다.