package ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread_ReceiveThread_PerClient extends Thread {
    Socket socket;
    String name;
    PrintWriter pw;

    public SendThread_ReceiveThread_PerClient(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            pw = new PrintWriter(socket.getOutputStream());
            pw.println(name);
            pw.flush();

            while (true) {
                String msg = br.readLine();
                if (msg.equals("bye"))
                    break;

                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
