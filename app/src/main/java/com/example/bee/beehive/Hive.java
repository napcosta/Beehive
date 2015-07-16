package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Hive {

	int id;
	int number;

	public Hive(){}
	public Hive(int number)
	{
		this.number = number;
	}

	public Hive(int id, String name)
	{
		this.number = number;
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

	public void setID(int id)
	{
		this.id = id;
	}

	public int getNumber()
	{
		return number;
	}

	public void setName(int name)
	{
		this.number = name;
	}

}
