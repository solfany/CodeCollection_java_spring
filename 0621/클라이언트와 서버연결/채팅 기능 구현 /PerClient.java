package ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 단톡방 같이 여러 클라이언트가 메시지로 대화하게 하는 스레드 클래스 
public class PerClient extends Thread {

	// 동기화된 ArrayList를 생성하여 PrintWriter만 들어갈 수 있는 List로 설정
	// static으로 지정하여 객체 생성이 안되어 PerClient 객체들을 공유 데이터로 설정하고
	// 각 클라이언트 별로 생성된 PrintWriter를 담아서
	// 한 명의 클라이언트가 메시지 전송하면 모든 클라이언트에게 전달한다.
	// 클라이언트들에게 메시지를 입력한 순서대로 전달하기 위해 동기화 설정을 한 것이다.
	// Collections.synchronizedList(List) :
	// 지정된 목록이 지원하는 동기화된 (스레드로부터 안전한) 목록을 반환

	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<>());

	Socket client;
	PrintWriter pw;

	public PerClient(Socket client) throws IOException {
		this.client = client;
		pw = new PrintWriter(client.getOutputStream());
		list.add(pw);
	}

	public void run() {
		String name = "";
		try {
			// 연결된 클라이언트에 대한 문자 입력 스트림 버퍼 생성
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				// 처음으로 전달 받은 클라이언트에게 입장 메시지 전달
				name = br.readLine();
				sendAll("#" + name + "님이 입장하셨습니다.");

				while (true) {
					// 클라이언트의 메시지를 한 줄 받아서 저장
					String msg = br.readLine();
					if (msg == null)
						break;
					sendAll(name + ">" + msg);
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(pw);
			sendAll("#" + name + "님이 퇴장하셨습니다.");
			try {
				client.close();
			} catch (Exception e) {

			}
		}
	}

	void sendAll(String msg) {
		for (PrintWriter p : list) {
			p.println(msg);
			p.flush();
		}
	}
}
