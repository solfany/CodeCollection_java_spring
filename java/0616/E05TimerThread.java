package practice01_0616;

public class E05TimerThread extends Thread	 {
	private int n = 0;
	private boolean flag = false;
	
	// 종료 메소드 : 플래그 전환
	public void finish() {
		flag = true;
	}
	// 스레드 동작 실행
	 @Override
	 public void run() {
		 // 0부터 1초 간격으로 숫자가 계속 올라감 출력
		 while(true) {
			 System.out.println(n);
			 n++;
			 try {
				 sleep(1000);
				 // 플래그가 true이면 스레드 동작 종료
				 if(flag ==true) 
					 return;
			 } catch (InterruptedException e) {
				 return;
			 }
		 }
	 }
	 public static void main(String[] args) {
		 // 스레드 객체 생성후 실행하자마자
		 E05TimerThread th = new E05TimerThread();
		 th.start();
		 try {
			 th.sleep(10000);
			 th.finish();
		 } catch (InterruptedException e){
			 e.printStackTrace();
		 }
		 // th.interrupt(); // 스레드 강제 종료
		 // 종료 메소드 호출하여 바로 종료
		 // th.finish();
	 }
}
// 문 > 10초후에 피니쉬를 이용한 쓰레드 종료를 해보세요...

//  스레드의 상태 제어 메서드 들
// sleep() 특징
// sleep(long millis)
// Thread.sleep() 메소드는 현재 스레드가 일정 기간동안 실행을 중지시킨다.
// 우선순위가 낮은 스레드가 기아 상태에 빠지는 것을 방지할 수 있다.

// join 특징 : 다른 스레드의 작업을 기다린다.
//  조인 (join)은 한 스레드가 다른 스레드의 완료를 기다리게 한다.
// 만약 t가 현재 동작중인 스레드의 객체라고 한다면
// t.join(); 이 코드는 현재 스레드가 t 스레드가 종료될 때 까지 실행을 중단합니다.
// yield 특징 : 다른 스레드에게 양보한다.
// yield() 메소드를 호출한 스레드는 실행 대기상태로 돌아가고 동일한 우선순위
// 또는 높은 우선 순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록 해준다.
// 예를 들어, 스케줄러에 의해 1초의 실행시간을 할당받은 스레드가 0.5초의 시간동안 작업한 상태에서
// yield()가 호출되면 나머지 0.5초는 포기하고 다시 실행 대기상태가 된다.
// 호출방법 Thread.yield(); CPU자원의 소모를 방지할 수 있다.
