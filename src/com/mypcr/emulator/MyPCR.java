package com.mypcr.emulator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MyPCR extends Thread
{
	ArrayList< Protocol >		list			= new ArrayList< Protocol >( );
												
	private double				mTemp;
	private double				mPrevTargetTemp, mTargetTemp;
	private int					state;
	private int					mElapsedTime	= 0;
												
	private boolean				isMonitor		= false;
												
	private static final int	STATE_READY		= 0x00;
	private static final int	STATE_RUN		= 0x01;
												
	private static final double	DEEFAULT_TEMP	= 25.1;
												
	public MyPCR( )
	{
		mTemp = DEEFAULT_TEMP;
		mPrevTargetTemp = DEEFAULT_TEMP;
		mTargetTemp = DEEFAULT_TEMP;
		state = STATE_READY;
	}
	
	public boolean isMonitoring( )
	{
		return isMonitor;
	}
	
	public void setMonitor( boolean monitor )
	{
		this.isMonitor = monitor;
	}
	
	private boolean tempHeat = false;
	private int timeTemp = 1;
	public void run( )
	{
		while( true )
		{
			if( this.state == STATE_RUN )
			{
				//��..
				timeTemp++;
				if(timeTemp%10 == 0)
					mElapsedTime += 1;
			}
			else
			{
				mTemp = DEEFAULT_TEMP;
				mPrevTargetTemp = DEEFAULT_TEMP;
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
				return "�غ���";
			case STATE_RUN:
				return "������";
			default :
				return null;
		}
	}
	
	private String getElapsedTime( )
	{
		int time = mElapsedTime;
		return String.format( "%dh %dm %ds", time / 3600, ( time % 3600 ) / 60, time % 60 );
	}
	
	public void printStatus( )
	{
		System.out.print( String.format( "���� : %s, �µ� : %3.1f, elapsedTime : %s\n", getStateString( ),
				this.mTemp, getElapsedTime( ) ) );
	}
	
	public void startPCR( )
	{
		if( state == STATE_RUN )
		{
		
		}
		System.out.println( "PCR ����!" );
		state = STATE_RUN;
		this.mPrevTargetTemp = 40.0;
		this.mTargetTemp = 30.0;
		
	}
	
	public void stopPCR( )
	{
		if( state == STATE_READY )
		{
		
		}
		System.out.println( "PCR ����!" );
		state = STATE_READY;
		this.stop( );
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
	
	public void showProtocolList( ArrayList< Protocol > list )
	{
		if( list == null )
		{
			System.out.println( "�߸��� PCR������ ����Ϸ� �߽��ϴ�." );
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
