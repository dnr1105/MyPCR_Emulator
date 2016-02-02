package com.mypcr;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.mypcr.emulator.MyPCR;

public class Main
{
	public static void main( String[] args ) throws AWTException
	{
//		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
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
//		mypcr.showProtocolList( mypcr.makeProtocolList( pcr3 ) );
		
		String str = "";
		String line;
		
		try
		{
			BufferedReader fr = new BufferedReader( new FileReader( new File( "protocol.txt" ) ) );
			
			while((line = fr.readLine( )) != null)
			{
				str += line+"\n";
			}
			mypcr.showProtocolList( mypcr.makeProtocolList( str ) );
			
			fr.close( );
			
			//			br.close( );
		}
		catch( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
}