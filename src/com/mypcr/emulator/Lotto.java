package com.mypcr.emulator;

public class Lotto {
	public int[] lotto(int length) {
		int temp[] = new int[length];

		for (int i = 0; i < length; ++i) {
			temp[i] = (int) ((Math.random() * 45) + 1);

			for (int j = 0; j < i; ++j) {
				if (temp[j] == temp[i]) {
					System.out.println("Áßº¹ °ª");
					--i;
					break;
				}
			}
		}
		return temp;
	}
}
