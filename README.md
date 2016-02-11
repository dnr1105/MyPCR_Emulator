#	준이형 멘토링 6주차
##	Date : 2016. 02. 11(목)
### 수업 내용
	1. Thread - run 변경
	2. mPrevTarget, mTarget Temp 변경
	3. 현재온도 제어
	4. 프로토콜 온도값 순서대로 올리고 내리고 코딩
	
### 과제
	1. private ArrayList<Protocol> mProtocolList;	// 멤버 변수 선언
		위의 리스트에 Protocol.txt 파일에서 읽은 PCR 정보를 불러와서 저장하기.
		이 작업은 public MyPCR() 생성자에서 수행
	2. 출력 형태 변경
		"Label : %s, TargetTemp : %3.1f, Remain : %ds, 상태 : %s, 온도 : %3.1f, elapsedTime : %s\n", 
		---------getStateString( ), this.mTemp, getElapsedTime( ) ) );
	3. microPCR_Firmware에서 branch를 pic18f4550_emul로 변경하여 PWMValue계산하는 부분 참조하여 온도 증감 값 정하기.(어려움)
	
#	준이형 멘토링 5주차
##	Date : 2016. 02. 04(목)
###	수업 내용
	1. Thread 기초 공부, 모니터링 및 온도 올리기
	2. 입력 복습
	3. Protocol 생성
	4. 상태값 변경 및 출력 실습
	
###	과제(수요일 까지 올릴 것) -> 1 -> 3 -> 4 -> 2순으로 하면 편함
	1. StartPCR(), StopPCR() 할 시, PCR 시작!, PCR종료! 메시지가 뜨도록 바꾸세요
	2. Start시에, mPrevTargetTemp = 90.0, mTargetTemp = 50.0으로 변경, Run()함수 안에서 mPrevTemp까지 온도 도달 시키고, 
		그 후에 mTargetTemp까지 온도가 도달하도록 구현. mTargetTemp까지 온도가 도달 할 경우, 자동으로 Stop되도록 구현.
	3. StartPCR(), StopPCR() 시에, 현재 상태가 맞지 않은 경우 그냥 무시하도록 구현,
		ex> Start를 했는데 이미 상태가 STATE_RUN이라면 그냥 return 하로고 구현.
	4. private int mElapsedTime을 선언하고, 0으로 초기화.
		run() 함수에서, state가 RUN일 경우, 1초 마다 증가시킴.
		printStatus()함수에서 
		(String.format( "상태 : %s, 온도 : %3.1f\n, elapsedTime : %s", 
						getStateString( ), this.mTargetTemp, getElapsedTime() ))으로 구현
		결과물은 다음과 같음 -> 상태 :동작중, 온도:78.1, elapsedTime:1m 2s
		
		run()함수에서의 mTemp += 0.01;로 변경하세요
		getElapsedTime()함수는 다음과 같이 선언하여 구현하세요.
		private String getElapsedTime()

#	준이형 멘토링 4주차
##	Date : 2016. 02. 02(화)
###	과제
	1. 없음

# 준이형 멘토링_3주차
## Date : 2016. 01. 21(목)

### 숙제(많이 어려울 수 있으니 열심히)
	String pcr1 =	"1	50	40\n"+
			"2	kk	50\n"+
			"3	60	100\n";

	String pcr2 =	"1	25	40\n"+
			"2	40	50\n"+
			"3	60\n";

	1. MyPCR에 다음과 같은 함수를 만드세요.
	 public ArrayList<Protocol> makeProtocolList(String pcr)
	 위의 함수는 이전에 구현한 pcr line 값을 읽어서 pcr list를 만드는 예제를 이용해야 합니다.	
	 (파일 읽기 부분 구현)
	 매개변수 pcr은 위의 pcr1, pcr2를 사용하면 됩니다.

	2. 1번 예제를 올릴 시, 에러가 발생 합니다.
	 에러 중에 NumberFormatException 또는 ArrayOutofIndexBound에러가 발생할 경우, 
	 "잘못된 PCR파일입니다"라는 에러 메시지 출력 후에 return null;을 호출 하세요.

	3. public void showProtocolList(ArrayList<Protocol> list);
	 위의 함수는 이전에 구현한 프로토콜 출력 부분을 이용하면 됩니다.
	 단 매개변수로 받은 list가 null인지 체크하고, null인경우 "잘못된 PCR파일을 출력하려 했습니다."
	 라고 출력 후에 return;을 호출해주세요.


# 준이형 멘토링_2주차
## Date : 2016. 01. 07(목)

### 과제
	1. String 합치기를 이용하여 Lotto결과물을 다음 포맷으로 출력하세요.
	포맷 형식 : 1, 2, 3, 4, 5, 6, 7

	2. 본인이 만든 Lotto함수를 처음 3번 호출하여 a1, a2, a3, a4에 저장함.

	3. a1(당첨 번호)배열과, a2(사용자 1), a3(사용자 2), a4(사용자 3) 배열을 비교하여 등수
		1등 - 7개 맞췄을 때
		2등 - 6개 맞췄을 때
		3등 - 5개 맞췄을 때
			...
		6등 - 2개 맞췄을 때
		7등 - 1개 맞췄을 때
		8등 - 꽝
		의 개수를 출력
		
	ex) 만약에 a1이 { 1, 2, 3, 4, 5, 6, 7 }일 때, a2(사용자 1)가 {1, 2, 4, 5, 6, 7, 3 }이라면 2개가 맞은 것.
	

# 준이형 멘토링_1주차
## Date : 2015. 12. 31(목)

### 수업의 목적
	1. Git 사용법 (기본적인 소스 올리고 받고, 동기화)
	2. 기본 자바 문법 복습
	3. PCR 실습
		3-1. Firmware부분 가상으로 Java에서 구현 - Thread동작하여 100ms마다 값이 변경
		3-2. 실제 Java프로그램 부분 MyPCr.java함수 호출 - 값을 변경하는 것 제어
		
	// 오늘 수업 내용은 1, 2번

### Git 설치
	git-2.6.4 setup 실행
	Checkout as-is, commit as-is 라디오버튼 말고 다른 화면은 그냥 그대로

### 차주 계획
	firmware source활용하여 온도, 상태, LED 상태를 가지게 할 것
	기본 온도 25, target온도, 이전 target온도 등을 가지게 할 것
	
	void initDialog() --> public void run()이랑 동일한 의미

======================================================================
# Gighub 사용법
	## 처음 깃만들고 맨 처음에 파일을 올릴 때
	    git config --global user.name "NAME"
	    git config --global user.email "EMAIL"
	    git init
	    touch README.md
	    git add --all
	    git commit -m "first commit"
	    git remote add origin "https://github.com/dnr1105/MyPCR_Emulator.git"
	    git push -u origin master

	## 깃에 올라간 파일을 가져와서 편집
	    git clone https://github.com/dnr1105/MyPCR_Emulator.git
	    cd ./src/com/mypcr/
	    ls -al
	    git add Main.java
	    git commit -m "Second commit"
	    git push origin master

	## 이후 간단한 수정본 깃에 올리기
	    git add --all // 혹은 자신의 변경된 파일명
	    git commit -m "emulator"
	    git push

	## 깃에 올라간 파일 삭제
	    cd emulator/
	    ls -al
	    git rm MyPCR2.java
	    git commit -m "MyPCR2 byebye"
	    git push origin master

	## 함께 사용하는 깃 동기화하여 올리기
	    git add Main.java
	    git commit -m "Hello infinity"
	    git push origin master
	    git fetch
	    git pull origin master
	    git push origin master
======================================================================