package practice01_0615;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class MyCounter extends JFrame implements ActionListener{
	private JLabel[] labels;
	private JButton button;
	private int[] numbers;
	private Timer timer;
	private int delay = 100;
	
	public MyCounter() {
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		
		//JPanel 객체를 생성하는데
		JPanel panel = new JPanel();
		
		// 배치관리자를 지정하지 않는다.
		panel.setLayout(null);
		
		// panel.setLayout(new FlowLayout());
		// 이렇게 적으면 버튼이 숫자와 나란히 옆으로 출력된다.
		// labels는 text문구 배열에 크기가 3
		labels = new JLabel[3];
		numbers = new int[3];
		
		// for문을 이용해 labels에 값 지정하기
		for(int i = 0; i < 3; i++) {
			// numbers가 출력되는데 numbers는 아래 actionPerformed 메소드에서 확인
			labels[i] = new JLabel("" + numbers[i]);
			labels[i].setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 100));
			labels[i].setSize(100,100);
			
			// labels의 x값은 배열의 index 만큼 곱해주면
			// 옆으로 나란히 출력할 수 있다. 설정하지 않으면 겹쳐서 출력된다.
			labels[i].setLocation(100 + 100 * i, 20);
			panel.add(labels[i]);
		}
		button = new JButton("슬롯");
		button.setSize(250, 50);
		button.setLocation(100, 150);
		panel.add(button);
		
		// 버튼을 눌렀을 때 ActionListener 발생
		button.addActionListener(this);
		add(panel);
		setTitle("My Game");
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(timer == null) {
			timer = new Timer(delay, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < 3; i++) {
						numbers[i] = (int) (Math.random() * 10);
						labels[i].setText("" + numbers[i]);
					}	// actionPerformed() 메소드에서는 버튼이 클릭되면 Timer를 시작하거나 중지합니다.
				}		// actionPerformed() 메소드 내의 익명 클래스인 ActionListener 객체가 실행됩니다.
			});			// ActionListener는 숫자를 랜덤하게 변경하고, 각 JLabel에 변경된 숫자를 반영합니다.
			timer.start();
		} else {
			timer.stop();
			timer = null;
			checkResult();
		}
	}
	private void checkResult() {
		if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
			JOptionPane.showMessageDialog(this, "승리");
		} else {
			JOptionPane.showMessageDialog(this, "패배");
		} // 메시지를 표시하는 팝업 창을 생성하는데, this를 사용하여 현재 객체의 참조를 전달
	}
}


public class SlotMachine_ex {
	public static void main(String[] args) {
		new MyCounter();

	}
}
