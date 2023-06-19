package practice01_0616;

public class SynchronizedEx {
	public static void main(String[] args) {
		// 공유 데이터 객체 생성
		SharedBoard board = new SharedBoard();
		// 스레드 객체 2개 생성
		Thread th1 = new StudentThread("kitae", board);
		Thread th2 = new StudentThread("Juni", board);
		// 스레드 2개 시작
		th1.start();
		th2.start();

	}
}
// 공유 데이터 클래스
class SharedBoard {
	private int sum = 0;
	// public void add() {
	// 동기화 메소드로 이 객체는 하나의 스레드만 접근 가능
	// 먼저 온 스레드가 작업이 완전히 종료되어 Key를 반납할 때까지
	// 이 객체를 원하는 메소드는 BLOCKED(일시정지)상태가 됨
	// 동기화 메소드의 경우 Key는 이 클래스 객체를 뜻함
	public synchronized void add() {
		// sum에다가 10씩 누적하는 로직
		int n = sum;
		
		// 다른 스레드에게 CPU 사용을 양보하고 실행대기상태로 가는 yield()메소드지만
		// 동기화로 2개의 스레드 중 하나는 BLOCKED(일시정지)상태거나 종료상태이기 때문에
		// 먼저 온 스레드가 계속 진행 (즉, 현재는 의미없는 코드)
		// 만약 의미가 있어서 다른 스레드에게 양보하게 되더라도
		// 데이터가 꼬일 수 있기 때문에 의미 없다고 생각함
		// Thread.yield();
		n += 10;
		sum = n;
		System.out.println(Thread.currentThread().getName() + " : " + sum);
	}
	// 누적 값 반환 메소드
	public int setSum() {
		return sum;
	}
}
// 스레드 클래스
class StudentThread extends Thread {
	// 공유 객체 필드
	private SharedBoard board;
	// 매개변수로 이름과 공유 객체를 받아서
	// 이름은 부모 클래스 스레드 이름으로, 공유 객체는 필드로
	public StudentThread(String name, SharedBoard board) {
		super(name);
		this.board = board;
	}
	// 스레드 실행 : 공유 객체의 동기화 메소드 add() 10번 반복
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			board.add();
		}
	}
}
