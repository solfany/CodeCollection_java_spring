package practice01_0616;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThreadInterruptEx extends JFrame {
	// Field : 스레드 객체 선언
	private Thread th;
	
	// Constructor
	public ThreadInterruptEx() {
		// JFrame 셋팅
		setTitle("ThreadInterruptEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = (getContentPane());
		c.setLayout(new FlowLayout());
		
		// 라벨 객체 생성 및 폰트 설정
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		
		// 라벨 객체를 인수로 Runnable 객체 생성
		 TimerRunnable runnable = new TimerRunnable(timerLabel);
		 
		// Runnable 객체로 스레드 객체 생성
		th = new Thread(runnable);
		
		// 생성한 라벨을 프레임에 삽입
		c.add(timerLabel);
		
		// 버튼을 생성하고 이벤트 등록
		JButton btn = new JButton("Kill Timer");
		
		// 무명 클래스로 버튼 이벤트 리스너 생성
		btn.addActionListener(new ActionListener() {
			@Override
			// 버튼 이벤트를 통해 스레드를 강제 종료
			public void actionPerformed(ActionEvent e) {
				th.interrupt(); // 스레드 강제 종료
				JButton btn = (JButton) e.getSource();
				// 버튼 비활성화
				btn.setEnabled(false);
			}
		});
		// 버튼을 프레임에 삽입 후
		c.add(btn);
		// 프레임 셋팅
		setSize(300, 170);
		setVisible(true);
		// 스레드 시작
		th.start();
	}
	public static void main(String[] args) {
		new ThreadInterruptEx();
	}
}
