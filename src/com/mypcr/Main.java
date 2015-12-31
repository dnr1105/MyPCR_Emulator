package com.mypcr;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.mypcr.emulator.MyPCR;

public class Main {
	public static void main(String[] args) throws AWTException {
		int test = 1;

		MyPCR mypcr = new MyPCR();

		System.out.printf("%d + %d = %d\n", 1, 2, mypcr.add(1, 2));
		System.out.printf("%d - %d = %d\n", 1, 2, mypcr.sub(1, 2));
		System.out.printf("%d * %d = %d\n", 1, 2, mypcr.mul(1, 2));
		System.out.printf("%d / %d = %d\n", 1, 2, mypcr.dev(1, 0));
	}
}