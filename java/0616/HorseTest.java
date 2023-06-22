package practice01_0616;

import java.util.Arrays;

public class HorseTest {

	public static void main(String[] args) {
//		Thread t1 = new Thread(new Horse(1)); 
//		Thread t2 = new Thread(new Horse(2)); 
//		Thread t3 = new Thread(new Horse(3)); 
//		Thread t4 = new Thread(new Horse(4)); 
//		Thread t5 = new Thread(new Horse(5)); 
//		Thread t6 = new Thread(new Horse(6)); 
//		Thread t7 = new Thread(new Horse(7)); 
//		Thread t8 = new Thread(new Horse(8)); 
//		Thread t9 = new Thread(new Horse(9)); 
//
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();
//		t9.start();
		

		Thread[] t = new Thread[10];	// 배열로 생성 크기 지정
		
		for (int i = 0; i <= 9; i++) {	// 배열의 길이만큼 반복
		    t[i] = new Thread(new Horse(i+1));
		    // 해당 클래스의 인스턴스를 생성자로 하여 인스턴스 스레드 t[i] 생성
		    t[i].start();	// t[i]의 스레드 실행
		}
		System.out.println("main() 종료...");
	}	// 메인문의 마지막에서 출력
}
// 스레드 동기화란?
// 1. 공유 데이터를 접근하는 모든 스레드를 한 줄로 세워
// 한 스레드가 공유 데이터에 대한 작업을 끝낼 때 까지 다른 스레드가 대기하도록 하는 것.
// 1. synchronized로 동기화 블록 지정
// public synchronized void method_name() {
//	// 동기화 할 내용
// }
