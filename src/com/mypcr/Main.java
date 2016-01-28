package com.mypcr;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.mypcr.emulator.MyPCR;
import com.mypcr.emulator.Protocol;

public class Main
{
	public static void main( String[] args ) throws AWTException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
		String pcr1 =	"1	50	40\n"+
						"2	kk	50\n"+
						"3	60	100\n";
		
		String pcr2 =   "1	25	40\n"+
						"2	40	50\n"+
						"3	60\n";
		
		String pcr3 = "1	45	40\n" + "2	40	50\n" + "3	60	100\n";
		
		MyPCR mypcr = new MyPCR();
//		mypcr.showProtocolList( mypcr.makeProtocolList( pcr1 ) );
//		mypcr.showProtocolList( mypcr.makeProtocolList( pcr2 ) );
		mypcr.showProtocolList( mypcr.makeProtocolList( pcr3 ) );
		
		try
		{
			br.close( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
}