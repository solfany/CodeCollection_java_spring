//package practice01_0616;
//
//import java.awt.Container;
//import java.awt.FlowLayout;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//public class E01FlickeringLabelEx extends JFrame {
//	public E01FlickeringLabelEx() {
//		// 프레임 셋팅
//		setTitle("E01FlickeringLabelEx 예제");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Container c = getContentPane();
//		c.setLayout(new FlowLayout());
//		// E01FlickeringLabelEx 클래스는 프레임을 실행하여
//		// 플리커 효과를 가진 라벨을 표시하는 역할을 합니다.
//		
//		// 배경색이 바뀌는 스레드 라벨 2개와 일반 라벨 1개 생성
//		FlickeringLabel fLabel = new FlickeringLabel("깜빡", 500);
//		JLabel label = new JLabel("안깜빡");
//		FlickeringLabel fLabel2 = new FlickeringLabel("여기도 깜빡", 300);
//		
//		// 프레임에 라벨들 삽입
//		c.add(fLabel);	c.add(label);	c.add(fLabel2);
//		
//		// 프레임 창 크기를 내부 컴포넌트들 크기에 맞춤
//		pack();
//		setVisible(true);
//	}
//	public static void main(String[] args) {
//		new E01FlickeringLabelEx();
//	}
//
//}
