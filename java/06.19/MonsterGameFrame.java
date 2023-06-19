package ch13;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonsterGameFrame extends JFrame {
// 아바타로 사용할 문자열은 "@", 괴물로 사용할 문자열은 "M", 종료키는 'q', 괴물은 200ms 주기로 움직인다
   JPanel gamePanel = new GamePanel("@", "M", 'q', 200); 
   // 게임 패널, 컨텐트팬으로 사용한다.

   public MonsterGameFrame() {
      setTitle("Open Challenge 13");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setContentPane(gamePanel); // GamePanel을 컨텐트팬으로 사용한다.
//컨텐트 팬 : 스윙 컴포넌트들이 부착되는 공간
//스윙에서는 컨텐트 팬에만 컴포넌트를 부착할 수 있다.
//컨텐트 팬은 JFrame 객체가 처음 생길 때 자동으로 생성된다.
//setContent(변경할 컴포넌트)는 JFrame의 생성 당시 붙어 있는 컨텐트팬을 제거하고 컨텐트팬을 
//새로 만들어 붙인다.
//컨텐트팬은 Container타입이므로 Container를 상속받은 어떤 컴포넌트도 컨텐트팬이 될 수 있다.

      setSize(1000, 1000);
      setVisible(true);

      gamePanel.requestFocus(); 
      // GamePanel이 키를 입력받을 수 있도록 포커스를 설정한다.
   }
   // 게임이 진행되는 패널. 배치관리자를 null로 설정
   class GamePanel extends JPanel {
      String avatarChar;
      String monsterChar;
      char quitChar;
      long monsterDelay;
      JLabel avatar;// 아바타를 위한 레이블
      JLabel monster; // 괴물을 위한 레이블
      final int AVATAR_MOVE = 10; // 아바타가 한번에 움직이는 픽셀 수

      public GamePanel(String avatarChar, String monsterChar, char quitChar, long monsterDelay) {
         this.avatarChar = avatarChar;
         this.monsterChar = monsterChar;
         this.quitChar = quitChar;
         this.monsterDelay = monsterDelay;

         // 아바타와 괴물 레이블 생성
         ImageIcon icon = new ImageIcon("/Users/kimsoulbee/Desktop/myet.png");
         Image avatarImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         avatar = new JLabel(new ImageIcon(avatarImage));

         ImageIcon icon2 = new ImageIcon("/Users/kimsoulbee/Desktop/myet1.jpeg");
         Image monsterImage = icon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
         monster = new JLabel(new ImageIcon(monsterImage));


         setLayout(null); // 아타바 레이블과 괴물 레이블의 위치를 마음대로 지정할 수 있도록 null로 설정
         addKeyListener(new MyKeyListener()); // 키 리스너 등록

         // 아바타 레이블의 위치와 크기 설정 및 팬에 부착
         avatar.setLocation(50, 50);
         avatar.setSize(100, 100);
         avatar.setForeground(Color.RED);
         add(avatar);

         // 괴물 레이블의 위치와 크기 설정 및 팬에 부착
         monster.setLocation(200, 5);
         monster.setSize(100, 100);
         add(monster);

         // 괴물을 움직이는 스레드 생성 및 시작
         MonsterThread th = new MonsterThread(monster, avatar, monsterDelay);
         th.start();
      }
      // GamePanel에 등록된 키 리스너. 상, 하, 좌, 우 키와 'q' 키 처리
      class MyKeyListener extends KeyAdapter {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == quitChar) // 종료 키는 유니코드 키
               System.exit(0); // 종료 키가 입력되면 프로그램 종료

            int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님

            // 키에 따라 아바타 레이블을 AVATAR_MOVE 픽셀 만큼 움직인다.
            switch (keyCode) {
            case KeyEvent.VK_UP:
               avatar.setLocation(avatar.getX(), avatar.getY() - AVATAR_MOVE);
               break;
            case KeyEvent.VK_DOWN:
               avatar.setLocation(avatar.getX(), avatar.getY() + AVATAR_MOVE);
               break;
            case KeyEvent.VK_LEFT:
               avatar.setLocation(avatar.getX() - AVATAR_MOVE, avatar.getY());
               break;
            case KeyEvent.VK_RIGHT:
               avatar.setLocation(avatar.getX() + AVATAR_MOVE, avatar.getY());
               break;
            }

            avatar.getParent().repaint(); // 아바타의 위치가 변경되었으므로 다시 그리기
            // 아바타가 있는 패널에는 이전의 위치에 있었던 아바타를 지워야 하기 때문에
            // 아바타의 부모 패널에게 다시그리기를 지시함
         }
      }
   }
   // 괴물 레이블을 움직이는 200ms 당 5 픽셀을 움직이면서 끊임없이 아바타를 추적하는 스레드
   // 생성자는 두 개의 레이블 컴포넌트를 전달받는다. 첫번째 from이 두번째 to 레이블을 추적한다.
   class MonsterThread extends Thread {
      JLabel from; // 쫓아가는 레이블. 괴물 레이블
      JLabel to; // 도망가는 레이블. 아바타 레이블
      long monsterDelay;
      final int MONSTER_MOVE = 5; // from 레이블이 한번에 이동하는 거리

      MonsterThread(JLabel from, JLabel to, long monsterDelay) {
         this.from = from;
         this.to = to;
         this.monsterDelay = monsterDelay;
      }
      public void run() {
         int x = from.getX(), y = from.getY(); // 현재 괴물의 위치

         while (true) {
            if (to.getX() < from.getX()) // 아바타가 괴물의 왼쪽이 있는 경우
               x = from.getX() - MONSTER_MOVE;
            else // 아바타가 괴물의 오른쪽이 있는 경우
               x = from.getX() + MONSTER_MOVE;

            if (to.getY() < from.getY()) // 아바타가 괴물의 위쪽이 있는 경우
               y = from.getY() - MONSTER_MOVE;
            else // 아바타가 괴물의 아래쪽이 있는 경우
               y = from.getY() + MONSTER_MOVE;

            // 괴물의 위치 수정
            from.setLocation(x, y);

            // 괴물의 위치가 변경되었기 때문에 괴물을 포함하는 패널을 다시 그리도록 함
            from.getParent().repaint();

            // 200ms 동안 잠을 잔다.
            try {
               sleep(monsterDelay);
            } catch (InterruptedException e) {
               return;
            }
         }
      }
   }
   public static void main(String[] args) {
      new MonsterGameFrame();
   }
}
