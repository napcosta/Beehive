package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Hive extends dbListEntry {

	int id;
	int apiary_id;
	int name;

	public Hive(){}
	public Hive(int name, int apiary_id)
	{
		this.name = name;
		this.apiary_id = apiary_id;
	}

	public Hive(int id, int name, int apiary_id)
	{
		this.name = name;
		this.apiary_id = apiary_id;
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

	public int getApiaryID()
	{
		return apiary_id;
	}

	public void setName(int name)
	{
		this.name = name;
	}

	public String getName()
	{
		return String.valueOf(name);
	}

}
