#	������ ���丵 6����
##	Date : 2016. 02. 11(��)
### ���� ����
	1. Thread - run ����
	2. mPrevTarget, mTarget Temp ����
	3. ����µ� ����
	4. �������� �µ��� ������� �ø��� ������ �ڵ�
	
### ����
	1. private ArrayList<Protocol> mProtocolList;	// ��� ���� ����
		���� ����Ʈ�� Protocol.txt ���Ͽ��� ���� PCR ������ �ҷ��ͼ� �����ϱ�.
		�� �۾��� public MyPCR() �����ڿ��� ����
	2. ��� ���� ����
		"Label : %s, TargetTemp : %3.1f, Remain : %ds, ���� : %s, �µ� : %3.1f, elapsedTime : %s\n", 
		---------getStateString( ), this.mTemp, getElapsedTime( ) ) );
	3. microPCR_Firmware���� branch�� pic18f4550_emul�� �����Ͽ� PWMValue����ϴ� �κ� �����Ͽ� �µ� ���� �� ���ϱ�.(�����)
	
#	������ ���丵 5����
##	Date : 2016. 02. 04(��)
###	���� ����
	1. Thread ���� ����, ����͸� �� �µ� �ø���
	2. �Է� ����
	3. Protocol ����
	4. ���°� ���� �� ��� �ǽ�
	
###	����(������ ���� �ø� ��) -> 1 -> 3 -> 4 -> 2������ �ϸ� ����
	1. StartPCR(), StopPCR() �� ��, PCR ����!, PCR����! �޽����� �ߵ��� �ٲټ���
	2. Start�ÿ�, mPrevTargetTemp = 90.0, mTargetTemp = 50.0���� ����, Run()�Լ� �ȿ��� mPrevTemp���� �µ� ���� ��Ű��, 
		�� �Ŀ� mTargetTemp���� �µ��� �����ϵ��� ����. mTargetTemp���� �µ��� ���� �� ���, �ڵ����� Stop�ǵ��� ����.
	3. StartPCR(), StopPCR() �ÿ�, ���� ���°� ���� ���� ��� �׳� �����ϵ��� ����,
		ex> Start�� �ߴµ� �̹� ���°� STATE_RUN�̶�� �׳� return �Ϸΰ� ����.
	4. private int mElapsedTime�� �����ϰ�, 0���� �ʱ�ȭ.
		run() �Լ�����, state�� RUN�� ���, 1�� ���� ������Ŵ.
		printStatus()�Լ����� 
		(String.format( "���� : %s, �µ� : %3.1f\n, elapsedTime : %s", 
						getStateString( ), this.mTargetTemp, getElapsedTime() ))���� ����
		������� ������ ���� -> ���� :������, �µ�:78.1, elapsedTime:1m 2s
		
		run()�Լ������� mTemp += 0.01;�� �����ϼ���
		getElapsedTime()�Լ��� ������ ���� �����Ͽ� �����ϼ���.
		private String getElapsedTime()

#	������ ���丵 4����
##	Date : 2016. 02. 02(ȭ)
###	����
	1. ����

# ������ ���丵_3����
## Date : 2016. 01. 21(��)

### ����(���� ����� �� ������ ������)
	String pcr1 =	"1	50	40\n"+
			"2	kk	50\n"+
			"3	60	100\n";

	String pcr2 =	"1	25	40\n"+
			"2	40	50\n"+
			"3	60\n";

	1. MyPCR�� ������ ���� �Լ��� ���弼��.
	 public ArrayList<Protocol> makeProtocolList(String pcr)
	 ���� �Լ��� ������ ������ pcr line ���� �о pcr list�� ����� ������ �̿��ؾ� �մϴ�.	
	 (���� �б� �κ� ����)
	 �Ű����� pcr�� ���� pcr1, pcr2�� ����ϸ� �˴ϴ�.

	2. 1�� ������ �ø� ��, ������ �߻� �մϴ�.
	 ���� �߿� NumberFormatException �Ǵ� ArrayOutofIndexBound������ �߻��� ���, 
	 "�߸��� PCR�����Դϴ�"��� ���� �޽��� ��� �Ŀ� return null;�� ȣ�� �ϼ���.

	3. public void showProtocolList(ArrayList<Protocol> list);
	 ���� �Լ��� ������ ������ �������� ��� �κ��� �̿��ϸ� �˴ϴ�.
	 �� �Ű������� ���� list�� null���� üũ�ϰ�, null�ΰ�� "�߸��� PCR������ ����Ϸ� �߽��ϴ�."
	 ��� ��� �Ŀ� return;�� ȣ�����ּ���.


# ������ ���丵_2����
## Date : 2016. 01. 07(��)

### ����
	1. String ��ġ�⸦ �̿��Ͽ� Lotto������� ���� �������� ����ϼ���.
	���� ���� : 1, 2, 3, 4, 5, 6, 7

	2. ������ ���� Lotto�Լ��� ó�� 3�� ȣ���Ͽ� a1, a2, a3, a4�� ������.

	3. a1(��÷ ��ȣ)�迭��, a2(����� 1), a3(����� 2), a4(����� 3) �迭�� ���Ͽ� ���
		1�� - 7�� ������ ��
		2�� - 6�� ������ ��
		3�� - 5�� ������ ��
			...
		6�� - 2�� ������ ��
		7�� - 1�� ������ ��
		8�� - ��
		�� ������ ���
		
	ex) ���࿡ a1�� { 1, 2, 3, 4, 5, 6, 7 }�� ��, a2(����� 1)�� {1, 2, 4, 5, 6, 7, 3 }�̶�� 2���� ���� ��.
	

# ������ ���丵_1����
## Date : 2015. 12. 31(��)

### ������ ����
	1. Git ���� (�⺻���� �ҽ� �ø��� �ް�, ����ȭ)
	2. �⺻ �ڹ� ���� ����
	3. PCR �ǽ�
		3-1. Firmware�κ� �������� Java���� ���� - Thread�����Ͽ� 100ms���� ���� ����
		3-2. ���� Java���α׷� �κ� MyPCr.java�Լ� ȣ�� - ���� �����ϴ� �� ����
		
	// ���� ���� ������ 1, 2��

### Git ��ġ
	git-2.6.4 setup ����
	Checkout as-is, commit as-is ������ư ���� �ٸ� ȭ���� �׳� �״��

### ���� ��ȹ
	firmware sourceȰ���Ͽ� �µ�, ����, LED ���¸� ������ �� ��
	�⺻ �µ� 25, target�µ�, ���� target�µ� ���� ������ �� ��
	
	void initDialog() --> public void run()�̶� ������ �ǹ�

======================================================================
# Gighub ����
	## ó�� �길��� �� ó���� ������ �ø� ��
	    git config --global user.name "NAME"
	    git config --global user.email "EMAIL"
	    git init
	    touch README.md
	    git add --all
	    git commit -m "first commit"
	    git remote add origin "https://github.com/dnr1105/MyPCR_Emulator.git"
	    git push -u origin master

	## �꿡 �ö� ������ �����ͼ� ����
	    git clone https://github.com/dnr1105/MyPCR_Emulator.git
	    cd ./src/com/mypcr/
	    ls -al
	    git add Main.java
	    git commit -m "Second commit"
	    git push origin master

	## ���� ������ ������ �꿡 �ø���
	    git add --all // Ȥ�� �ڽ��� ����� ���ϸ�
	    git commit -m "emulator"
	    git push

	## �꿡 �ö� ���� ����
	    cd emulator/
	    ls -al
	    git rm MyPCR2.java
	    git commit -m "MyPCR2 byebye"
	    git push origin master

	## �Բ� ����ϴ� �� ����ȭ�Ͽ� �ø���
	    git add Main.java
	    git commit -m "Hello infinity"
	    git push origin master
	    git fetch
	    git pull origin master
	    git push origin master
======================================================================