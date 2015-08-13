package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Hive extends dbListEntry {

	int id;
	int apiary_id;
	int name;
	int honeycomb_count;
	int breedingcomb_count;

	public Hive(){}
	public Hive(int name, int honeycomb_count, int breedingcomb_count, int apiary_id)
	{
		this.name = name;
		this.apiary_id = apiary_id;
		this.honeycomb_count = honeycomb_count;
		this.breedingcomb_count = breedingcomb_count;
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
