package practice01_0615;

public class FlagTest {
	public static void main(String[] args) {
		// 스레드 클래스 객체 생성
		White_01 white = new White_01();
		Blue_01 blue = new Blue_01();
		
		// 스레드 시작
		white.start();
		// start() 메서드를 호출하면 해당 스레드의 run() 메서드가 실행됩니다.
		blue.start();
	}
}
//public class FlagTest2 {
//	public static void maint(String[] args) {
//		// Runnable 인터페이스엔 start가 없기 때문에
//		// Thread 클래스 객체를 생성하여 Runnable 상속 클래스 활용
//		Thread t = new Thread(new Blue_02());
//		t.start();
//		Thread t2 = new Thread(new White_02());
//		t2.start();
//	}
//}