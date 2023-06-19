package practice01_0616;

public class SynchronizedEx2 {

	public static void main(String[] args) {
		// 공유 데이터 객체 생성
		SharedPrinter p = new SharedPrinter();
		// 출력할 문자열 배열
		String[] engText = { "Wise men say,", "only fools rush in", "But i can't help", "falling in love with you",
				"Shall I stay?", "Would it be a sin?", "If I can't help,", "falling in love with you" };
		String[] korText = { "동해물과 백두산이 마르고 닳도록, ", "하느님이 보우하사 우리 나라 만세", "무궁화 삼천리 화려강산, ", "대한사람 대한으로 길이 보전하세",
				"남산위에 저 소나무 철갑을 두른 듯", "바람서리 불변함은 우리 기상일세.", "무궁화 삼천리 화려강산", "대한사람 대한으로 길이 보전하세." };

		// 스레드 객체 2개 생성
		Thread th1 = new WorkerThread(p, engText);
		Thread th2 = new WorkerThread(p, korText);
		// 스레드 시작
		th1.start();
	}
}

// 공유 데이터 클래스
class SharedPrinter {
	// 동기화 메소드로 먼저 온 스레드의 작업 종료까지
	// 다른 스레드 접근 불가
	// 문자열을 매개변수로 받아서
	synchronized void print (String text) {
		// 문자열의 길이만큼 반복하여
		 for (int i = 0; i < text.length(); i++) {
	         System.out.print(text.charAt(i));
	         try {
	            Thread.sleep(100); // 일부러 딜레이를 주어 출력이 구분되도록 함
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }

		System.out.println();
	}
}
// 스레드 클래스
class WorkerThread extends Thread{
	SharedPrinter p;
	String[] text;
	
	// 생성 시 공유 데이터 객체와 문자열 배열을 매개변수로 받아서 필드 초기화
	WorkerThread(SharedPrinter p, String[] text) {
		this.p = p;
		this.text = text;
	}
	// 메소드 실행
	public void run() {
		// 받아온 문자열 배열 길이만큼 반복하며,
		// 문자열을 하나씩 인수로 공유 데이터 객체의 출력 메소드 호출
		for(int i = 0; i < text.length; i++) {
			p.print(text[i]);
		}
	}
}

