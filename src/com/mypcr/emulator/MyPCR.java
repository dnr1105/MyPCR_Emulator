package com.mypcr.emulator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyPCR extends Thread
{
	ArrayList< Protocol >			list			= new ArrayList< Protocol >( );
													
	private double					mTemp;
	private double					mPrevTargetTemp, mTargetTemp;
	private int						state;
	private int						mElapsedTime	= 0;
													
	private boolean					isMonitor		= false;
													
	private static final int		STATE_READY		= 0x00;
	private static final int		STATE_RUN		= 0x01;
													
	private static final double		DEEFAULT_TEMP	= 25.1;
													
	private static final double		temps[]			= { 95.5, 72.0, 85.0, 50.0, 4.0 };
	private ArrayList< Protocol >	mProtocolList;
	private float kp=0, ki=0, kd=0;
	/*
	 * 1. temps���� �µ��� �ϳ��� �о, start�ÿ� [0]��°�� ���� target�� ����, prev�µ��� DEFAULT_TEMP. 2. Thread Run���� prev���� target���� �ٸ��� target�� ���� �µ��� �ѹ� �ø��ų� ������ ���� �µ����� �޾ƿ�. 2-1. PrevTarget����
	 * Target�� �� ũ�� ����µ��� �ø���. 2-2. PrevTarget���� Target�� �� ������ �µ��� ������. 2-3. ����µ��� target�� �����ϸ� ���� target�µ��� prev�� �����ϰ� target�µ��� temps�� ���� �µ��� �޾ƿ´�. 2-3-1. index������ �߰��Ͽ� temps������
	 * �ȴ�. 3. ���� ������� �µ����� ��ȭ 4. ��
	 */
	
	public MyPCR( )
	{
		mTemp = DEEFAULT_TEMP;
		mPrevTargetTemp = DEEFAULT_TEMP;
		mTargetTemp = DEEFAULT_TEMP;
		state = STATE_READY;
		readProtocol( );
	}
	
	private void readProtocol( )
	{
		String str = "";
		String temp;

		try
		{
			BufferedReader br = new BufferedReader( new FileReader( new File( "protocol.txt" ) ) );
			while( ( temp = br.readLine( ) ) != null )
			{
				str += temp + "\n";
			}
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
		
		this.makeProtocolList( str );
		this.showProtocolList( list );
	}
	
	public boolean isMonitoring( )
	{
		return isMonitor;
	}
	
	public void setMonitor( boolean monitor )
	{
		this.isMonitor = monitor;
	}
	
	private boolean	tempHeat	= false;
	private int		timeTemp	= 1;
	private int		mIndex		= 0;
								
	public void run( )
	{
		while( true )
		{
			if( this.state == STATE_RUN )
			{
				if( ( mTemp >= ( mTargetTemp - 0.01 ) ) && ( mTemp <= ( mTargetTemp + 0.01 ) ) )
				{
					++mIndex;
					if( mIndex >= temps.length )
					{
						stopPCR( );
						continue;
					}
					
					mTemp = mTargetTemp;
					mTargetTemp = temps[mIndex];
				}
				
				PID_Control();
				
				timeTemp++;
				if( timeTemp % 10 == 0 ) mElapsedTime += 1;
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
	
	private float integralMax = (float) 2000.0;
	private double lastIntegral = 0;
	private double lastError = 0;
	
	public void PID_Control()
	{
		double currentErr = 0, proportional = 0, integral = 0;
		double derivative = 0;
		int pwmValue = 0xffff;
		double emul_value = 0.0;

		kp = 460;
		ki = (float)0.2;
		kd = 3000;

		currentErr = mTargetTemp - mTemp;
		proportional = currentErr;
		integral = currentErr + lastIntegral;

		if( integral > integralMax )
			integral = integralMax;
		else if( integral < -integralMax )
			integral = -integralMax;

		derivative = currentErr - lastError;
		pwmValue = 	(int) ( kp * proportional +  ki * integral + kd * derivative );

		if( pwmValue > 1023 )
			pwmValue = 1023;
		else if( pwmValue < 0 )
			pwmValue = 0;

		lastError = currentErr;
		lastIntegral = integral;

		if( pwmValue == 0 ) emul_value = -0.1;
		else emul_value = ( pwmValue / 1023. );
		
		mTemp += emul_value;
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
		System.out.println( String.format( "Label : %s, TargetTemp : %3.1f, Remain : %ds, State : %3.1f, "
				+ "elapsedTime : %s\n", "?", this.mTargetTemp, "?", getStateString(), this.mTemp, getElapsedTime( ) ));
	}
	
	public void startPCR( )
	{
		if( state == STATE_RUN )
		{
		
		}
		System.out.println( "PCR ����!" );
		state = STATE_RUN;
		this.mPrevTargetTemp = DEEFAULT_TEMP;
		this.mTargetTemp = temps[mIndex];
		
	}
	
	public void stopPCR( )
	{
		if( state == STATE_READY )
		{
		
		}
		System.out.println( "PCR ����!" );
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
