package ch15;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.DatagramChannel;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Messenger {
		protected JTextField textField;
		protected JTextArea textArea;
		
	
		DatagramSocket socket;
		DatagramPacket packet;
		InetAddress address = null;
		final int myPort = 2222;
		final int otherport = 5555;
		
		public Messenger() throws IOException {
			
			new MyFrame();
			
			address = InetAddress.getByName("localhost");
			socket = new DatagramSocket(myPort);
		}
		
		public void process() {
			while (true) {
				try {
					byte[] buf = new byte[256];
					packet = new DatagramPacket(buf, buf.length);
					
					socket.receive(packet);
				
				textArea.append("Recieved: " + new String(buf) + "\n");
				}catch (IOException io) {
					io.printStackTrace();
				}
			}
		}
		
//프레임 및 이벤트 내부 클래스 
		
	class MyFrame extends JFrame implements ActionListener {
		public MyFrame() {
			super("Messenger");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			textField = new JTextField(30);
			textField.addActionListener(this);
			
			textArea = new JTextArea(10, 30);
			textArea.setEditable(false);
			
			add(textField, BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER);
			
			pack();
			setVisible(true);
		}

	//액션이벤트
	@Override
	public void actionPerformed(ActionEvent e ) {
		String s = textField.getText();
		byte[] buffer = s.getBytes();
		DatagramPacket packet;
		
packet = new DatagramPacket(buffer, buffer.length, address, otherport);
try {
	socket.send(packet);
}catch (IOException ee ) {
	ee.printStackTrace();
}
		
//textArea에 내가 보낸 메세지 추가 
		textArea.append("SENT : " + s  + "\n");
	// 바로 다음 메세지를 입력할  있게 작성했던 모드 ㄴ메세지를드래그 처리한다. 
		textField.selectAll();
		//현재 코드(밑 ) 에서는 이거 없이도 동작이 됨
		//캐럿의 위치를 textArea 의 문서 모델의 문자수 위치에 설정한다. 
		
		//즉, 캐럿을 textAtea 에 입력된 문자의 마지막에 위치시킨다. 
		
		//캐럿이라는 것은 커서를 위미 
		//보내거나 받는 문자열의 마지막의 줄바꿈 문자가 있기 때문에 
		//텍스트를 입력할 커서를 textField에 입력도ㅗㅗㅚㄴ 마지막 문자의 다음 주에 위치시킴 
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	}
		public static void main(String[] args) throws IOException {
			Messenger n = new Messenger();
			n.process();
	}
}
//UDP (user datagram protocol)는 
//데이터를 몇 개의 고저 ㅇ길이의 패킷 (다이어그램 으로 불린다) 으로 분할하여 전송 
//GUL를 이용한 UDP 통신 채팅 - 통신자 A 
//1) 서버 - 클라이언트 방식이 아닌ㄴ 단방향 비동기 방식으로
