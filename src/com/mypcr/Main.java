package com.mypcr;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mypcr.emulator.Lotto;

public class Main {
	public static void main(String[] args) throws AWTException {
		/* 2015. 12. 31 _ 멘토링 1주차
		int test = 1;

		MyPCR mypcr = new MyPCR();

		System.out.printf("%d + %d = %d\n", 1, 2, mypcr.add(1, 2));
		System.out.printf("%d - %d = %d\n", 1, 2, mypcr.sub(1, 2));
		System.out.printf("%d * %d = %d\n", 1, 2, mypcr.mul(1, 2));
		System.out.printf("%d / %d = %d\n", 1, 2, mypcr.dev(1, 0));
		*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Lotto l = new Lotto();
		String str = null;
		int a[] = new int[7];
		
//		System.out.println((int)((Math.random()*45)+1));

		for (int i = 0; i < a.length; i++) {
			try {
//				System.out.print((i+1)+"번째 입력 : ");
//				str = br.readLine();
//				int tempInt = Integer.parseInt(str);
//				a[i] = tempInt;
				
				/*a[i] = (int)((Math.random()*45)+1);
				
				for(int j = 0; j < i; ++j)
				{
					if(a[j] == a[i]){ 
						System.out.println("중복 값");
						--i;
						break;
					}
				}*/
				
				a = l.lotto(a.length);
				// System.out.println((i + 1) + " : " + str);
//				System.out.println(tempInt);

			} catch (NumberFormatException nfe) {
				System.out.println("숫자만 입력");
				--i;
				continue;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		for(int z : a){
			System.out.println(z);
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}