package com.mypcr;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.mypcr.emulator.Protocol;

public class Main
{
	public static void main( String[] args ) throws AWTException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
		ArrayList< Protocol > list = new ArrayList< Protocol >( );
		
		String pcr = "1	25	40\n" 
					+"2	40	50\n" 
					+"3	60	100\n";
		String[] pcrs = pcr.split( "\n" );
		
		for( int i = 0; i < pcrs.length; i++ )
		{
			String[] temp = pcrs[i].split( "\t" );
			
			Protocol p = new Protocol(temp[0], Integer.parseInt( temp[1] ), Integer.parseInt( temp[2]));
			list.add(p);
		}
		
		for(int i = 0; i < list.size( ); i++)
		{
			System.out.print( list.get( i ).getLable( ) + "\t");
			System.out.print( list.get( i ).getTemp( )  + "\t");
			System.out.print( list.get( i ).getTime( ) );
			System.out.println(  );
		}
		
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