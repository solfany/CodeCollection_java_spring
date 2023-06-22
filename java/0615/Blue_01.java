package practice01_0615;

public class Blue_01 extends Thread {
	// run() 메소드 오버라이딩
	@Override
	public void run() {
		// 스레드 실행 시 해당 출력문 무한 반복
		while (true)
			System.out.println("청기 올려 !!");
	}
}

//class Blue_02 implements Runnable {
//	// run 메소드 구현
//	public void run() {
//		while(true)
//			System.out.println("청기 올려 !!");
//	}
//}