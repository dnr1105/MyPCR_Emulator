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
			System.out.print("(Error, �и� 0�Դϴ�.)");
			return 0;
		}
		return (a / b);
	}

}
