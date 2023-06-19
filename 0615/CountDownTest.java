package practice01_0615;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CountDownTest extends JFrame{
	private JLabel label;
	// Thread 클래스를 상속받은 내부 클래스와 GUI 복합 활용
	// 내부 클래스에 Thread 객체 상속
	class MyThread extends Thread{
		// start 메소드에 의해 실행됨
		public void run() {
			for(int i = 10; i >= 0; i--) {
				try {
					// sleep()은 도중에 예외가 발생할 가능성이 있다.
					// 1초 멈췄다가 재시작
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 1초마다 라벨 텍스트 변경
				label.setText("    " + String.valueOf(i));
			}// 스레드 내부 클래스로 만들면 필드에 접근하기 쉬워진다.
			System.exit(0);	// 동작 끝내고 프로그램 종료
		}
	}
	CountDownTest(){
		setTitle("카운트 다운");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 초기 JLabel 객체 상태 지정
		label = new JLabel("Start");
		label.setFont(new Font("Serif", Font.BOLD, 100));
		// JFrame 에 입력
		add(label);
		setVisible(true);
		// 스레드 시작
		MyThread t = new MyThread();
		t.start();
	}
	public static void main(String[] args) {
		new CountDownTest();
	}

}
