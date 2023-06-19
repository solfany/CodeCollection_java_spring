package practice01_0616;

public class Horse implements Runnable {	// 런에이블 인터페이스 상속
	private int horse_num;
	
	public Horse (int horse_num) {		// 생성자
		this.horse_num = horse_num;
	}
	public void run() {
		long sleepTime = (long) (Math.random() * 500);
		// 0 ~ 499 사이의 랜덤값을 해당 변수에 할당
		System.out.println(horse_num + "번 말이 " + sleepTime + "만큼 sleep ...");
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // sleepTime/1000 초 만큼 스레드 정지 // 모든 예외 발생시 순차적으로 추적
}
// 스토리보드란?
// 1. UX 구현에 수반되는 사용자와 목표, 인터페이스 간 상호작용을 시각화하여
//	  개발자, 디자이너와의 의사소통을 돕는 도구
// 2. 완성해야 할 서비스와 예상되는 사용자 경험을 미리 보기 위한 방법이다.
// 작성법
// 1. 스토리보드 표지엔 프로젝트 명, 문서버전정보, 최종업데이트 일자, 작성자 등의 정보 기재
// - 협업 프로젝트에서 버전 관리 정보가 중요하다. 팀 전체가 내용을 공유,
// 유기적 개발 진행
// Wireframe
// 1. UI 중심의 화면 레이아웃을 설계하여 선과 뼈대를 만드는 것.
//    시각적인 프레임워크, 버튼배치, 페이지 요소 등의 계층 구조 등
//    페이지 구조와 구조성 측면의 결과를 통합적으로 적용하는 작업이다.
// Mockup : 실물과 흡사한 (정적인) 형태의 모형이며 서비스 개발 전에 결과물을 그려보는것.
// Prototype : 다양한 인터렉션을 결합하여 실제 서비스처럼 작동하는(동적인) 형태의 모형을 작성하는 것.