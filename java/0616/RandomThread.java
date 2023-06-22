package practice01_0616;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;

public class RandomThread extends Thread {
	// Field : JFrame의 컨텐트팬과 플래그
	private Container contentPane;
	private boolean flag = false;
	
	// 생성자로 컨텐트팬 받아오기
	public RandomThread(Container contentPane) {
		this.contentPane = contentPane;
	}
	
	//스레드 종료 메소드 : 플래그 전환
	void finish() {
		flag = true;
	}
	
	// 스레드 동작 실행
	@Override
	public void run() {
		while(true) {
			// 랜덤 x, y 좌표 생성
			int x = (int) (Math.random() * contentPane.getWidth());
			int y = (int) (Math.random() * contentPane.getHeight());
			
			// 라벨 생성 및 크기와, 위에서 생성한 좌표로 위치 셋팅
			JLabel label = new JLabel("Java");
			label.setSize(80,30);
			label.setLocation(x,y);
			
			// 받아온 컨텐트팬에 넣고 컨텐트팬 다시 그리기
			contentPane.add(label);
			contentPane.repaint();
			
			try {
				//0.3초 정지
				Thread.sleep(300);
				
				//만약 플래그가 true이면
				if(flag == true) {
					// 컨텐트팬 안의 모든 컴포넌트 제거 후
					contentPane.removeAll();
					
					//라벨 생성하여 finish 빨간 글자로
					// 크기와 마지막으로 생성한 랜덤 x, y좌표로 지정
					label = new JLabel("finish");
					label.setSize(80,30);
					label.setLocation(x,y);
					label.setForeground(Color.RED);
					
					//컨텐트팬에 넣고 다시 그리고 
					contentPane.add(label);
					contentPane.repaint();
					
					// 스레드 종료
					return;
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
