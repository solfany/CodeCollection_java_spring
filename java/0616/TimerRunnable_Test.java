package practice01_0616;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TimerRunnable02 implements Runnable {
	private JLabel timerLabel;
	
	// 생성자로 라벨을 전달받아 저장
	public TimerRunnable02(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	// run() 메소드 구현
	@Override
	public void run() {
		int n = 0;
		//텍스트 라벨의 숫자가 1초 간격으로 무한히 올라간다.
		while(true) {
			timerLabel.setText(Integer.toString(n));
			n++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}


public class TimerRunnable_Test extends JFrame {
	public TimerRunnable_Test() {
	setTitle("Runnable을 구현한 타이머 스레드 예제");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container c = getContentPane();
	c.setLayout(new FlowLayout());
	
	JLabel timerLabel = new JLabel();
	timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
	c.add(timerLabel);
	
	setSize(250, 150);
	setVisible(true);
	
	TimerRunnable02 runnable = new TimerRunnable02(timerLabel);
	// Runnable 인터페이스를 상속 받는 클래스를 스레드 객체로 만든 것
	Thread th = new Thread(runnable);
	th.start();
	}
	public static void main(String[] args) {
		new TimerRunnable_Test();
	}
}	// 내일의 과제 isPaused 필드를 사용합니다.
//	resume() 메서드 안에는 synchronized notify()를 사용합니다.
// 스레드 답게 레이블을 클릭시 addMouseListener()를 사용합니다.
