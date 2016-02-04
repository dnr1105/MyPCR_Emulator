package com.mypcr;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mypcr.emulator.MyPCR;

public class Main
{
	public static void main( String[] args ) throws AWTException
	{
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		final MyPCR mypcr = new MyPCR( );
		
		mypcr.start();
		while( true )
		{
			try
			{
				String cmd = in.readLine( );
				if( cmd.equalsIgnoreCase( "Start" ) )
				{
					mypcr.startPCR( );
				}
				else if( cmd.equalsIgnoreCase( "Stop" ) )
				{
					mypcr.stopPCR( );
				}
				else if( cmd.equalsIgnoreCase( "Print" ) )
				{
					mypcr.printStatus( );
				}
				else if( cmd.equals( "monitor" ) )
				{
					mypcr.setMonitor( true );
					while( true )
					{
						Thread t = new Thread( ) {
							public void run( )
							{
								while( mypcr.isMonitoring( ) )
								{
									try
									{
										Thread.sleep( 10 );
									}
									catch( Exception e )
									{
										e.printStackTrace( );
									}
									mypcr.printStatus( );
								}
							}
						};
						t.start( );
						
						in.readLine( );
						mypcr.setMonitor( false );
					}
				}
			}
			catch( IOException ioe )
			{
				ioe.printStackTrace( );
			}
		}
	}
}