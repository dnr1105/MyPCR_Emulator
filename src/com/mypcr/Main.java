package com.mypcr;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.mypcr.emulator.Protocol;

public class Main {
	public static void main(String[] args) throws AWTException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;

		ArrayList<Protocol> list = new ArrayList<Protocol>();
		
		Protocol p1 = new Protocol("1", 20, 20);
		
		list.add( p1 );
		
		System.out.println(list.get(0).getLable( ));
		System.out.println(list.get(0).getTemp( ));
		System.out.println(list.get(0).getTime( ));
		
		/*for(String s : al){
			System.out.println(s);
		}*/
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}