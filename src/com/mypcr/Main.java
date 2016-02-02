package com.mypcr;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.mypcr.emulator.MyPCR;
import com.mypcr.emulator.Protocol;

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
//			mypcr.showProtocolList( mypcr.makeProtocolList( str ) );
			
			MyPCR p = new MyPCR();
			ArrayList<Protocol> list = p.makeProtocolList( str );
					
			for(int i = 0; i < list.size(); i++)
			{
				Protocol protocol = list.get(i);
				
				if(protocol.getLable( ).equals( "GOTO" ))
				{
					int target = protocol.getTemp( );
					int time = protocol.getTime( );
					protocol.setTime( time-1 );
					
					if(time -1 != 0)
					{
						i = target -2;
					}
				} else
				{
					System.out.println( protocol.getLable( ) );
				}
			}
			
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