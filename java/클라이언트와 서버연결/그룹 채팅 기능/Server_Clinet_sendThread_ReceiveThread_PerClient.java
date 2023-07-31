package ch15;

import java.net.ServerSocket;
import java.net.Socket;

public class Server_Clinet_sendThread_ReceiveThread_PerClient {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9002);
			while (true) {
				Socket client = ss.accept();
				PerClient pc = new PerClient(client);
				
				pc.start();
			}
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
	}
}
