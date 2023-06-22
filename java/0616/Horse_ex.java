package practice01_0616;

public class Horse_ex implements Runnable {
	private int horse_num; // 말의 번호
	private int curMeter; // 현재 위치
	private final int MAXMETER = 1000; // 골인 지점
	private int speed = 10; // run 한번 실행시마다 이동하는 거리
	private static int rank = 1; // 등수
	private int fake_horse_num = 8; // 특정 조작을 가할 말의 번호

	public Horse_ex(int horse_num) {
		this.horse_num = horse_num;
	}

	public void run() {
		while (curMeter < MAXMETER) {
			long sleepTime = (long) (Math.random() * 500);
			curMeter += speed;

			if (horse_num == fake_horse_num) {
				if (curMeter == 500) {
					fakeComment();
				}
			}

			if (curMeter >= 500 && rank < 9) {
				sleepTime = fake(sleepTime);
			}

			if (curMeter % 100 == 0) {
				System.out.println(horse_num + "번 말이 " + curMeter + "미터 도착 ");
			}

			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (curMeter >= MAXMETER) {
			System.out.println(horse_num + "번 말이 결승선 도착 " + rank + "등 도착");
			rank++;
		}
	}

	public long fake(long sleepTime) {
		return sleepTime + 500;
	}

	public void fakeComment() {
		System.out.println("아 " + fake_horse_num + "번 말이 사행을 하는데요.");
	}
	
	public static void main(String[] args) {
		Thread[] t = new Thread[10];	

		for (int i = 0; i <= 9; i++) {
			t[i] = new Thread(new Horse_ex(i + 1));
			t[i].start();
		}
	}
}

