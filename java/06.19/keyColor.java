package ch12;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class A2 extends JFrame {
    private JLabel la = new JLabel();

    private A2() {
        setTitle("key code에서 f1키 : 초록색, %키 노란색");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();

        c.addKeyListener(new MyKeyListener());
        c.setFocusable(true);  // 컨텐츠 팬이 키 이벤트를 받을 수 있도록 포커스 설정
        c.requestFocus();  // 컨텐츠 팬에 포커스 요청

        setSize(300, 150);
        setVisible(true);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            la.setText(e.getKeyText(e.getKeyCode()));

            Container contentPane = (Container) e.getSource();
            if (e.getKeyChar() == '%')
                contentPane.setBackground(Color.YELLOW);
            else if (e.getKeyCode() == KeyEvent.VK_F1)
                contentPane.setBackground(Color.GREEN);
            else if (e.getKeyCode() == KeyEvent.VK_1)
                contentPane.setBackground(getForeground());
            else if (e.getKeyCode() == KeyEvent.VK_2)
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else if (e.getKeyCode() == KeyEvent.VK_3)
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

    }

    public static void main(String[] args) {
        new A2();
    }
}
