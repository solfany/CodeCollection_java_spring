package practice01_0616;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


class E01FlickeringLabel extends JLabel implements Runnable {
	private long delay;
	
	public E01FlickeringLabel(String text, long delay) {
		// 부모 클래스 JLabel에 받아온 문자열을 라벨 텍스트로 삽입
		super(text);
		
		// 받아온 값을 delay 필드에 저장
		this.delay = delay;
		
		// 배경을 불투명 상태로 만들어 배경을 보이게 한다.
		setOpaque(true);
		
		// Runnable 인터페이스를 활용해 스레드를 생성 및 실행
		Thread th = new Thread(this);
		
		th.start();
	}
	
	// 스레드 실행 메소드
	@Override
	public void run() {
		// n은 스위치 역할
		int n = 0; // 초기화
		
		// 무한 반복
		while (true) { // 스레드가 실행되는 동안
			
			// n이 0이면 라벨 배경 노랑
			if (n == 0)
				setBackground(Color.YELLOW);
			
			// 아니면 라벨 배경 그린
			else
				setBackground(Color.GREEN);
			
			// 배경색 변경 후 n 값 변경
			if (n == 0)
				n = 1; // 토글 효과가 나옴
			else
				n = 0;
			
			// 받아온 delay 값으로 delay/1000 초 일시 정지
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
public class FlickeringLabel extends JFrame {
	public FlickeringLabel() {
		// 프레임 셋팅
		setTitle("E01FlickeringLabelEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		// E01FlickeringLabel 클래스는 프레임을 실행하여
		// 플리커 효과를 가진 라벨을 표시하는 역할을 합니다.
		
		// 배경색이 바뀌는 스레드 라벨 2개와 일반 라벨 1개 생성
		E01FlickeringLabel fLabel = new E01FlickeringLabel("깜빡", 500);
		JLabel label = new JLabel("안깜빡");
		E01FlickeringLabel fLabel2 = new E01FlickeringLabel("여기도 깜빡", 300);
		
		// 프레임에 라벨들 삽입
		c.add(fLabel);
		c.add(label);
		c.add(fLabel2);
		
		// 프레임 창 크기를 내부 컴포넌트들 크기에 맞춤
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FlickeringLabel();
	}
}
