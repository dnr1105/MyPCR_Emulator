package com.mypcr.emulator;

public class MyPCR {

	public MyPCR() {
	}

	public int add(int a, int b) {
		return (a + b);
	}

	public int sub(int a, int b) {
		return (a - b);
	}

	public int mul(int a, int b) {
		return (a * b);
	}

	public int dev(int a, int b) {
		if (b == 0) {
			System.out.print("(Error, 분모가 0입니다.)");
			return 0;
		}
		return (a / b);
	}

}
