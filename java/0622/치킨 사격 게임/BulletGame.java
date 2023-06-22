package ch16;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BulletGames extends JFrame { // 프레임 상속
   public BulletGames() {
      setTitle("사격 게임");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임설정
   GamePanel p = new GamePanel(); // 패널상속받는 클래스 GamePanel의 객체생성
      setContentPane(p);
      setSize(500, 500); // 콘텐츠팬 설정 및 사이즈설정
      setResizable(false);
      setVisible(true);
      p.startGame(); // 크기조절불가, 보이기, 해당 메소드실행
   }
   public static void main(String[] args) {
      new BulletGames();
   } // 생성자 호출
}
class GamePanel extends JPanel { // 패널 상속
   TargetThread targetThread = null;
   JLabel baseLabel = new JLabel();
   JLabel bulletLabel = new JLabel();
   JLabel targetLabel; // 여러 라벨 선언, 객체생성

   public GamePanel() {
      setLayout(null);
      baseLabel.setSize(40, 40);
      baseLabel.setOpaque(true); // baseLabel 설정
      baseLabel.setBackground(Color.BLACK); 
      // 배경색 검정 // 배치관리자없음,사이즈,불투명
      ImageIcon img = new ImageIcon // 절대경로의 파일 이미지아이콘 객체로 생성
      ("/Users/kimsoulbee/Desktop/chicken.png");
   targetLabel = new JLabel(img); // img를 라벨객체에 담아 targetLabel에 대입
      targetLabel.setSize(img.getIconWidth(), img.getIconWidth()); 
      // 이미지의 크기를 사이즈로 사용
      bulletLabel.setSize(10, 10);
      bulletLabel.setOpaque(true); // bulletLabel 사이즈설정, 불투명
      bulletLabel.setBackground(Color.RED);
      add(baseLabel);      add(targetLabel);      add(bulletLabel);
   } // 배경색 빨강, 각종 라벨들 패널에 추가
   public void startGame() { // 게임시작 메소드
   baseLabel.setLocation(this.getWidth()/2-20, this.getHeight()-40);
   bulletLabel.setLocation(this.getWidth()/2-5, this.getHeight()-50);
   targetLabel.setLocation(0,0);
   // 베이스, 총알, 타겟의 위치 설정 베이스와 총알은 사진크기를 기반으로설정
   targetThread = new TargetThread(targetLabel);   
   targetThread.start();   baseLabel.requestFocus();
   // 타겟스레드 객체 생성 및 스레드 실행, 베이스라벨에 포커스 요구.
   baseLabel.addKeyListener(new KeyAdapter() { 
      // 베이스 라벨에 키리스너 추가 어댑터사용, 무명클래스
         BulletThread bulletThread = null;// 불릿스레드 레퍼런스타입 변수 생성
         public void keyPressed(KeyEvent e) { // 키 눌리면 실행
      if (e.getKeyChar() == '\n') { // 엔터키 입력 받을 때
            if (bulletThread == null || !bulletThread.isAlive()){ 
               //스레드객체가 비었거나 스레드가 죽어있을때
bulletThread = new BulletThread(bulletLabel, targetLabel, targetThread); // 객체생성
         bulletThread.start(); // 스레드실행
            } } } }); }
   class TargetThread extends Thread { // 스레드 상속
      JComponent target;
   public TargetThread(JComponent target) { // 생성자, 위치설정 및 메소드호출
         this.target = target;
         target.setLocation(0, 0);
         target.getParent().repaint();
      }
      public void run() { // 런 메소드
         while (true) { // 무한반복
            int x = target.getX() + 5 ;
            int y = target.getY(); // 타겟의 위치 변경
            if (x > GamePanel.this.getWidth())
      target.setLocation(0, 0); // 패널의 크기를 벗어나면 0,0으로 위치 초기화
            else
               target.setLocation(x, y); // 아니면 x,y 좌표 그대로.
            try {
               sleep(20);
            } catch (InterruptedException e) {
            } // 20/1000초 정지
         }
      }
   }
   class BulletThread extends Thread { // 스레드 상속
      JComponent bullet, target;
      Thread targetThread;
public BulletThread
   (JComponent bullet, JComponent target, Thread targetThread) {//생성자
         this.bullet = bullet;
         this.target = target;
         this.targetThread = targetThread;
      }
      public void run() { // 런메소드
         while (true) { // 무한반복
            if (hit()) {
         targetThread.interrupt(); // hit메소드가 true면 타겟스레드 예외발생
         bullet.setLocation(bullet.getParent().getWidth() / 2 - 5, 
         bullet.getParent().getHeight() - 50);
         JOptionPane.showMessageDialog(null, "게임 종료"); // 미사일이 타겟을 맞추면 메시지 박스로 게임 종료 안내
         System.exit(0);
               return;
            } else {
               int x = bullet.getX();
               int y = bullet.getY() - 5; // hit가 false면 Y 위치 변화
if (y < 0) { // y가 위치를 넘어서면 미사일 스레드를 종료하고 미사일 객체를 원위치 시킴
          bullet.setLocation(bullet.getParent().getWidth() / 2 - 5, 
         bullet.getParent().getHeight() - 50);
                  return;
               }
               bullet.setLocation(x, y); // 위치변경
            }
            try {
               sleep(20);
            } // 20/1000초 정지
            catch (InterruptedException e) {
            }
         }
      }
private boolean hit() { // targetContains 메소드를 통한 hit(피격)판정 메소드
if (targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY())
|| targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY() 
+ bullet.getHeight() - 1)
|| targetContains(bullet.getX(), bullet.getY() + bullet.getHeight() - 1))
            return true;
         else
            return false;
      }
      private boolean targetContains(int x, int y) {
if(((target.getX() <= x) && (target.getX() + target.getWidth() - 1 >= x))
      && //target.x의 크기가 인자로 받은 x의 크기보다 작거나 같고,
// 타겟x+타겟의width(사진의x값)의 값이 x보다 작거나같으며
((target.getY() <= y) && (target.getY() + target.getHeight() - 1 >= y)))
            return true; // y도 그와 똑같을 때 true
         else
            return false;
      }
   }
} // 아니면 false