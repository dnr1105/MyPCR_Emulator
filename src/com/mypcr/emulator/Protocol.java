package com.mypcr.emulator;

public class Protocol
{
	private String	lable;
	private int		temp;
	private int		time;
	
	public Protocol( String lable, int temp, int time )
	{
		this.lable = lable;
		this.temp = temp;
		this.time = time;
	}

	public String getLable( )
	{
		return lable;
	}

	public void setLable( String lable )
	{
		this.lable = lable;
	}

	public int getTemp( )
	{
		return temp;
	}

	public void setTemp( int temp )
	{
		this.temp = temp;
	}

	public int getTime( )
	{
		return time;
	}

	public void setTime( int time )
	{
		this.time = time;
	}
	
	
}
