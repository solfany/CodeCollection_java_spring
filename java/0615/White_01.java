package practice01_0615;

//Thread 클래스 상속
public class White_01 extends Thread {
	// run() 메소드 오버라이딩
	@Override
	public void run() {
		while(true)
			System.out.println("백기 올려 !!");
	}
}

//class White_02 implements Runnable{
//	public void run() {
//		while(true)
//			System.out.println("백기 올려 !!");
//	}
//}