package com.mypcr.emulator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MyPCR extends Thread
{
	ArrayList< Protocol >		list			= new ArrayList< Protocol >( );
												
	private double				mTemp;
	private double				mPrevtem, mTargetTemp;
	private int					state;
								
	private boolean				isMonitor	= false;
												
	private static final int	STATE_READY		= 0x00;
	private static final int	STATE_RUN		= 0x01;
												
	private static final double	DEEFAULT_TEMP	= 25.1;
												
	public MyPCR( )
	{
		mTemp = DEEFAULT_TEMP;
		mPrevtem = DEEFAULT_TEMP;
		mTargetTemp = DEEFAULT_TEMP;
		state = STATE_READY;
	}
	
	public boolean isMonitoring()
	{
		return isMonitor;
	}
	
	public void setMonitor(boolean monitor)
	{
		this.isMonitor = monitor;
	}
	
	public void run( )
	{
		while( true )
		{
			if( this.state == STATE_RUN )
			{
				mTargetTemp += 0.25;
			}
			else
			{
				mTemp = DEEFAULT_TEMP;
				mPrevtem = DEEFAULT_TEMP;
				mTargetTemp = DEEFAULT_TEMP;
			}
			
			// lotation 100ms
			try
			{
				Thread.sleep( 100 );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace( );
			}
		}
	}
	
	public String getStateString( )
	{
		switch( state )
		{
			case STATE_READY:
				return "준비중";
			case STATE_RUN:
				return "동작중";
			default :
				return null;
		}
	}
	
	public void printStatus( )
	{
		System.out.print( String.format( "상태 : %s, 온도 : %3.1f\n", getStateString( ), this.mTargetTemp ) );
	}
	
	public void startPCR( )
	{
		state = STATE_RUN;
	}
	
	public void stopPCR( )
	{
		state = STATE_READY;
	}
	
	public ArrayList< Protocol > makeProtocolList( String pcr )
	{
		if( pcr == null ) return null;
		
		String[] pcrs = pcr.split( "\n" );
		Protocol p;
		
		for( int i = 0; i < pcrs.length; i++ )
		{
			String[] temp = pcrs[i].split( "\t" );
			
			try
			{
				p = new Protocol( temp[0], Integer.parseInt( temp[1] ), Integer.parseInt( temp[2] ) );
			}
			catch( NumberFormatException nfe )
			{
				System.out.println( "잘못된 PCR파일 입니다." );
				return null;
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				System.out.println( "잘못된 PCR파일 입니다." );
				return null;
			}
			catch( NullPointerException npe )
			{
				System.out.println( "잘못된 PCR파일 입니다." );
				return null;
			}
			list.add( p );
		}
		return list;
	}
	
	public void showProtocolList( ArrayList< Protocol > list )
	{
		if( list == null )
		{
			System.out.println( "잘못된 PCR파일을 출력하려 했습니다." );
			return;
		}
		else
		{
			System.out.println( "====================" );
			System.out.println( "Label	Temp	Time" );
			System.out.println( "====================" );
			for( int i = 0; i < list.size( ); i++ )
			{
				System.out.print( list.get( i ).getLable( ) + "\t" );
				System.out.print( list.get( i ).getTemp( ) + "\t" );
				System.out.print( list.get( i ).getTime( ) );
				System.out.println( );
			}
		}
	}
	
	public String loadProtocolFromFile( String path )
	{
		String str = "";
		String line;
		
		try
		{
			BufferedReader fr = new BufferedReader( new FileReader( new File( path ) ) );
			
			while( ( line = fr.readLine( ) ) != null )
			{
				str += line + "\n";
			}
			System.out.println( str );
			
			fr.close( );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
			return null;
		}
		
		return str;
	}
}
