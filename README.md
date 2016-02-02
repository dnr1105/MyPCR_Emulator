#	준이형 멘토링 4주차
##	Date : 2016. 02. 02(화)
###	

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