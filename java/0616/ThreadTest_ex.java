package practice01_0616;

class Th1 extends Thread {
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println("월요일 대박");
			// i가 3이 되면 쓰레드를 yield() 양보한다.
			if(i == 3)
				Thread.yield();	// 다른 쓰레드에게 실행을 양보
		}
	}
}
class Th2 extends Thread {
	public void run() {
		Thread.yield();
		for(int i = 0; i < 10; i++) {
			System.out.println("대박");
			// if (i == 7) 
			// Thread.yield();
		}
		
	}
}
public class ThreadTest_ex {
	public static void main(String[] args) {
		Th1 th1 = new Th1();
		Th2 th2 = new Th2();
		
		// 스레드가 실행된 후
		th1.start();
		th2.start();
		
		// 이 for문은 없어도 동작에 이상이 없다.
		for(int i = 0; i < 20; i++) {
			try {
				// join 메서드를 실행하면 해당 쓰레드가 실행 완료되고 난 후 다음 스레드를 실행한다.
				// 즉 main 스레드를 정지 시킨다.
				// 본 예제에서는 스레드가 영향을 끼치지 않는데
				// start 와 join을 순서대로 쓰면 효과를 볼 수 있다.
				th1.join();
				th2.join();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("error");
			}
		}
	} // 메인 스레드 (Main Thread)는 자바 프로그램을 실행할 때 자동으로 생성되는 기본적인 스레드
	  // 실행순서 : 두개의 스레드가 번갈아가며 실행되기 때문에 출력 순서는 보장되지 않습니다.
	  // 1. th1과 th2 스레드가 시작되고, Th1의 run() 메서드와 Th2() 의 run() 메서드가 동시에 실행.
	  // 2. th1의 run() 메서드에서 "월요일 대박" 을 출력하다가
	  // i가 3이 되면 Thread.yield()를 호출하여 실행 양보에 들어간다.
	  // 3. th2의 run() 메서드에서 Thread.yield()를 호출한 후 "대박"을 출력
	  // 4. main() 메서드의 for 루프에서 th1.join()과 th2.join()을 호출하여
	  //    th1과 th2 스레드의 실행을 완료할 때 까지 대기합니다.
	  // 5. Thread.sleep(1000)을 통해 1초 동안 대기한 후, 다음 반복을 수행한다.
	  // 6. 위 과정을 20번 반복합니다.
}
