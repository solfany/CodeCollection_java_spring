package practice01_0616;

import javax.swing.JLabel;

public class TimerRunnable implements Runnable {
	// Field : 숫자 카운트 라벨 객체 선언
	private JLabel timerLabel;
	
	// Constructor : 라벨 객체 받아서 필드 초기화
	public TimerRunnable(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
// 스레드 실행
	@Override
	public void run() {
		int n = 0;
		// 무한 반복
		 while (true) {
			 // 라벨의 텍스트를 n 값으로 변경
			 timerLabel.setText(Integer.toString(n));
			 // n값 증가
			 n++;
			 try {
				 // 1초 정지
				 Thread.sleep(1000);
			 } catch(InterruptedException e) {
				 return;
			 }
		 }
	}
}
