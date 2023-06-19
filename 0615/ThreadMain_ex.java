package practice01_0615;
// 현재 동작중인 스레드 정보
// 스레드란 프로세스가 만들어낸 임의의 데이터 처리 공간 정
public class ThreadMain_ex {
	public static void main(String[] args) {
		long id = Thread.currentThread().getId();
		// 메소드를 호출하여 현재 스레드의 id를 가져옵니다.
		String name = Thread.currentThread().getName();
		// 메소드를 호출하여 현재 스레드의 우선순위를 가져옵니다.
		int priority = Thread.currentThread().getPriority();
		
		Thread.State s = Thread.currentThread().getState();
		
		System.out.println("현재 스레드 이름 = " + name);
		System.out.println("현재 스레드 ID = " + id);
		System.out.println("현재 스레드 우선순위 값 = " + priority);
		System.out.println("현재 스레드 상태 = " + s);
	}
}
// 스레드의 상태
// Runnable 상태 : 스레드를 실행하기 위한 준비 상태
// Running 상태 : 스레드 스케줄러가 선택된 스레드를 실행하는 상태
// Blocked 상태 : 스레드가 작업을 완수하지 못하고 잠시 작업을 멈추는 상태
// new -> Runnable(준비) -> Running(실행) -> Dead

// 1. Start()호출 2. 스레드가 실행 준비 3. 스케줄러에 의해 스레드가 선택 4. 실행
// 5. 스레드가 완료하지 못하고 대기상태로 전환 (sleep(), yield(), join() 호출)
// 6. 대기상태에서 다시 준비상태로 전환
// sleep() 현재 실행중인 스레드의 실행을 지정한 시간만큼 중지시킨다.
// yield() 특징
// 현재 실행하는 스레드가 다른 스레드를 실행하도록 실행을 중지한다.
// 호출방법 Thread.yield();
// 실행중인 스레드가 호출된다.

// 스레드 동기화(synchronized)
// 멀티쓰레드 프로세스의 경우 여러 스레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에
// 서로의 작업에 영향을 주게된다.
// 따라서 스레드 동기화는 한번에 한 개의 스레드만 객체에 접근할 수 있도록
// 객체에 락(lock)을 걸어서 데이터의 일관성을 유지하는 것이다.
// 공유 데이터를 사용하는 코드 영역을 임계영역으로 지정해놓고,
// 공유 데이터(객체)가 가지고 있는 lock을 획득한 단 하나의 스레드만
// 이 영역 내의 코드를 수행할 수 있게 된다.
// Lock은 일반적으로 synchronized키워드를 사용하여 구현됩니다.
// synchronized 키워드를 이용하면 임계 영역에 대한 락을 자동으로 확보하고 해제할 수 있습니다.
// 락을 확보한 스레드가 임계영역을 빠져나갈 때 락은 자동으로 해제되어
// 다른 스레드가 진입할 수 있게 됩니다.
// 동기화 메서드와 동기화 블럭이 있습니다.
// 메서드 앞에 synchronized를 붙이면 메서드 전체가 임계 영역으로 설정됩니다.
// 스레드의 동기화의 효율을 높이기 위해서 wait(), notify(), notifyAll()을 사용합니다.
// Object 클래스에 정의되어 있으며, 동기화 블록내에서만 사용할 수 있습니다.
// wait() : 객체의 lock을 풀고 해당 객체의 스레드를 waiting pool 객체에 넣는다.
// notify() : waiting pool 에서 대기중인 스레드 중의 하나를 깨운다.
// notifyAll() : waiting pool 에서 대기중인 모든 스레드를 깨운다.