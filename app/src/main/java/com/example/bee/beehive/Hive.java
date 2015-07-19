package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Hive {

	int id;
	int apiary_id;
	int number;

	public Hive(){}
	public Hive(int number, int apiary_id)
	{
		this.number = number;
		this.apiary_id = apiary_id;
	}

	public Hive(int id, int number, int apiary_id)
	{
		this.number = number;
		this.apiary_id = apiary_id;
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

	public int getApiaryID()
	{
		return apiary_id;
	}

	public void setName(int name)
	{
		this.number = name;
	}

}
