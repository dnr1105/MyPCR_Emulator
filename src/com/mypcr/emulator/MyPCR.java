package com.mypcr.emulator;

import java.util.ArrayList;

public class MyPCR {
	ArrayList< Protocol > list = new ArrayList< Protocol >( );

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
	
	public ArrayList<Protocol> makeProtocolList(String pcr)
	{
		String[] pcrs = pcr.split( "\n" );
		Protocol p;
		
		for(int i = 0; i < pcrs.length; i++)
		{
			String[] temp = pcrs[i].split( "\t" );
			
			try
			{
				p = new Protocol( temp[0], Integer.parseInt( temp[1] ), Integer.parseInt( temp[2] ) );
			}
			catch( NumberFormatException nfe )
			{
				System.out.println( "�߸��� PCR���� �Դϴ�." );
				return null;
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				System.out.println( "�߸��� PCR���� �Դϴ�." );
				return null;
			}
			catch( NullPointerException npe )
			{
				System.out.println( "�߸��� PCR���� �Դϴ�." );
				return null;
			}
			list.add( p );
		}
		return list;
	}
	
	public void showProtocolList(ArrayList<Protocol> list)
	{
		if(list == null)
		{
			System.out.println( "�߸��� PCR������ ����Ϸ� �߽��ϴ�." );
			return ;
		}
		else
		{
			System.out.println( "================================" );
			System.out.println( "Label	Temp	Time" );
			System.out.println( "================================" );
			for(int i = 0; i < list.size( ); i++)
			{
				System.out.print( list.get( i ).getLable( )+"\t" );
				System.out.print( list.get( i ).getTemp( )+"\t" );
				System.out.print( list.get( i ).getTime( ) );
				System.out.println(  );
			}
		}
	}

}
