package practice01_0615;

class SMIFileThread extends Thread {
	@Override
	public void run() {
		String[] strArray = {"하나", "둘", "셋", "넷", "다섯"};
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		// #4. 자막번호 출력
		for(int i = 0; i < strArray.length; i++) {
			System.out.println(" - (자막번호) " + strArray[i] );
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}
}
class SMIFileThread2 extends Thread {
	@Override
	public void run() {
		String[] strArray = {"원", "투", "쓰리", "포", "파이브"};
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		// #4. 자막번호 출력
		for(int i = 0; i < strArray.length; i++) {
			System.out.println(" - (송신번호) " + strArray[i] );
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}
}
public class CreateAndStartThread_M1C1 {
	public static void main(String[] args) {
		// SMIFileThread 객체 생성 및 시작
		Thread smiFileThread = new SMIFileThread();
		smiFileThread.start();
		
		Thread smiFileThread2 = new SMIFileThread2();
		smiFileThread2.start();
		
		// #1 비디오 프레임 1 ~ 5
		int[] intArray = {1, 2, 3, 4, 5};
		
		// #3. 비디오 프레임 출력
		for(int i = 0; i < intArray.length; i++) {
			System.out.println("(비디오 프레임) " + intArray[i]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}
	
}
