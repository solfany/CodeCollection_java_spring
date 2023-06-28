package ch17;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CattingClient implements ActionListener, Runnable {
    private static final String SERVER_IP = "192.168.10.25";

    private Socket socket;
    private JFrame jframe;
    private JTextField jtf;
    private JTextArea jta;
    private JLabel jlb1, jlb2;
    private JPanel jp1, jp2;
    private String chatName;
    private JButton jbtn;

    InputStream is;
    OutputStream os;
    BufferedReader br_in;
    BufferedWriter bw;
    PrintWriter pw;

    public CattingClient() {
        chatName = JOptionPane.showInputDialog(jframe, "대화명을 입력하세요", "대화명 입력 다이얼로그",
                JOptionPane.PLAIN_MESSAGE);
        if (chatName == null || chatName.length() == 0) {
            System.exit(0);
        }

        String ip = SERVER_IP;

        jframe = new JFrame("멀티 채팅 클라이언트 프로그램");
        jp1 = new JPanel();
        jp1.setLayout(new BorderLayout());

        jtf = new JTextField(30);
        jbtn = new JButton("종료");

        jp1.add(jbtn, BorderLayout.EAST);
        jp1.add(jtf, BorderLayout.CENTER);

        jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());

        jlb1 = new JLabel("서버 IP 주소: " + ip);
        jlb2 = new JLabel("대화명: " + chatName);

        jp2.add(jlb1, BorderLayout.CENTER);
        jp2.add(jlb2, BorderLayout.EAST);

        jta = new JTextArea("", 10, 50);
        jta.setBackground(Color.GREEN);

        JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jframe.add(jp1, BorderLayout.SOUTH);
        jframe.add(jp2, BorderLayout.NORTH);
        jframe.add(jsp, BorderLayout.CENTER);

        jtf.addActionListener(this);
        jbtn.addActionListener(this);

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    pw.println(chatName + "#exit");
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        jta.setEditable(false);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String msg = jtf.getText();

        if (obj == jtf) {
            if (msg == null || msg.length() == 0) {
                JOptionPane.showMessageDialog(jframe, "글을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    pw.println(chatName + "#" + msg);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                jtf.setText("");
            }
        } else if (obj == jbtn) {
            try {
                pw.println(chatName + "#exit");
                socket.close();
            } catch (IOException ee) {
                ee.printStackTrace();
            }
            System.exit(0);
        }
    }

    public void init() {
        try {
            socket = new Socket(SERVER_IP, 5001);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br_in = new BufferedReader(new InputStreamReader(is));
            bw = new BufferedWriter(new OutputStreamWriter(os));
            pw = new PrintWriter(bw, true);

            Thread t = new Thread(this);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        CattingClient cc = new CattingClient();
        cc.init();
    }

    @Override
    public void run() {
        String message;
        String[] receiveMsg;
        boolean isStop = false;

        while (!isStop) {
            try {
                message = br_in.readLine();
                receiveMsg = message.split("#");
            } catch (IOException e) {
                e.printStackTrace();
                isStop = true;
                break;
            }

            System.out.println(receiveMsg[0] + " : " + receiveMsg[1]);

            if (receiveMsg[1].equals("exit")) {
                if (receiveMsg[0].equals(chatName)) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                } else {
                    jta.append(receiveMsg[0] + "님이 종료했습니다.\n");
                    jta.setCaretPosition(jta.getDocument().getLength());
                }
            } else {
                jta.append(receiveMsg[0] + ": " + receiveMsg[1] + "\n");
                jta.setCaretPosition(jta.getDocument().getLength());
            }
        }
    }
}
