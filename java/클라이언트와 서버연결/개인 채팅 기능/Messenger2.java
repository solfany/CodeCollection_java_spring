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

public class Messenger2 {
		protected JTextField textField;
		protected JTextArea textArea;
		
	
		DatagramSocket socket;
		DatagramPacket packet;
		InetAddress address = null;
		final int myPort = 5555;
		final int otherport = 2222;

		public Messenger2() throws IOException {
			
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
			super("Messenge2");
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

		
	//액션이벤
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
		textField.selectAll();
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	}
		public static void main(String[] args) throws IOException {
			Messenger2 n = new Messenger2();
			n.process();
	}
}
