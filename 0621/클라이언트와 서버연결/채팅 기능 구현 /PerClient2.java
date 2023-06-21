package ch15;

import java.net.Socket;
import java.util.Scanner;

public class PerClient2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("연결할 IP: ");
        String ip = scanner.nextLine();

        System.out.println("사용할 별명: ");
        String name = scanner.nextLine();

        try {
            Socket socket = new Socket(ip, 9002);
            ReceiveThread_PerClient rt = new ReceiveThread_PerClient(socket);
            SendThread_ReceiveThread_PerClient st = new SendThread_ReceiveThread_PerClient(socket, name);

            rt.start();
            st.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
