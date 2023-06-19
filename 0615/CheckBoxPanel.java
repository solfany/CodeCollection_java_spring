package practice01_0615;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckBoxPanel extends JPanel implements ItemListener {
	// 체크박스 배열 객체 생성
	JCheckBox[] buttons = new JCheckBox[3];
	
	// 이름 문자열 생성
	String[] china = { "jajang", "jjambbong", "tangsuyuk" };
	
	//이미지 파일 개수에 맞춰 JLabel도 3개 생성
	JLabel[] pictureLabel = new JLabel[3];
	
	// ImageIcon 객체도 3개 생성
	// 이미지는 자바프로젝트 소스파일 저장돼 있는 상대경로에 있어야 한다.
	// 상대경로란? 현재 작업폴더(working directory) 위치를 기준으로 상대적인 경로를 표기하는 방식
	// 1. C:\Users\JAE_WON_PARK\Desktop\JAVA1\Test_01_Month 경로에 사진 넣기
	// 2. Test_01_Month에 사진 파일 넣기
	ImageIcon[] icon = new ImageIcon[3];
	
	public CheckBoxPanel() {
		// JPanel 객체를 4등분해서 표현
		super(new GridLayout(0,4));
		
		 for(int i = 0; i < china.length; i++) {
			 // 체크박스에 이름 입력
			 buttons[i] = new JCheckBox(china[i]);
			 
			 // 체크박스에 아이템 리스너 등록
			 // 현재객체가 체크박스의 아이템 리스너로 동작하도록 설정하는 것.
			 buttons[i].addItemListener(this);	// 현재객체 CheckBoxPanel()
			
			 
			 pictureLabel[i] = new JLabel(china[i] + ".jpg");
			 icon[i] = new ImageIcon(china[i] + ".jpg");
			 
		 }	// 체크박스 생성
		 
		 // 체크박스 객체를 JPanel 첫 번째 열에 저장
		 // ↓ checkPanel 0,1 이므로 세로로 값이 출력된다.
		 // 0을 사용하면 행의 개수는 자동적으로 조정된다. 열의 개수는 1로 고정된다는 뜻.
		 JPanel checkPanel = new JPanel(new GridLayout(0,1));	// 행의 개수, 열의 개수
		 
		 // i는 영역범위를 벗어났기 때문에 위의 i와 다른것.
		 for (int i = 0; i < 3; i++) 
			 checkPanel.add(buttons[i]);	// 체크박스를 하나로 묶는다.
		 
		 // add를 통해 JPanel 객체에 입력
		 add(checkPanel);
		 add(pictureLabel[0]);
		 add(pictureLabel[1]);
		 add(pictureLabel[2]);
	}
	
	public void itemStateChanged(ItemEvent e) {
		// 체크박스 선택 상태가 변하는 경우 호출
		ImageIcon[] image = null;
		
		// 이벤트 객체로부터 체크된 체크박스 객체를 획득
		Object source = e.getItemSelectable();
		for(int i = 0; i < 3; i++) {
			if (source == buttons[i]) {
				// 해당 객체가 선택되지 않았다면
				if(e.getStateChange() == ItemEvent.DESELECTED)
					pictureLabel[i].setIcon(null);
				else 
					// pictureLabel 배열 객체에 저장돼있는 Icon을 출력하는 것. 즉, 해당 이미지.
					pictureLabel[i].setIcon(icon[i]);
			}	// DESELECTED : 체크가 해제되어있는지 검사한다.
		}		// 체크박스가 체크된 경우 ItemEvent.SELECTED
	}			// 체크박스가 해제된 경우 ItemEvent.DESELECTED를 리턴
	
	public static void main(String[] args) {
		// JFrame 객체를 생성
		JFrame frame = new JFrame("CheckBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// CheckBoxPanel 객체 생성하고 생성자를 실행
		CheckBoxPanel panel = new CheckBoxPanel();
		
		// CheckBoxPanel 객체 표시 여부 설정, true나 false나 결과는 동일함.
		// setOpaque 메소드를 추가해야 변경사항이 적용된다.
		// JComponenet는 Default로 Opaque 값이 false(투명)으로 되어있기 때문
		panel.setOpaque(true);	// 그림 표시하도록 설정, 불투명
		
		// CheckBoxPanel 생성자 연산 후 JFrame추가
		frame.add(panel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

}




